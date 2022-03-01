package io.iamcyw.ams.notification.push;

import io.iamcyw.ams.domain.notification.cache.PushAlarm;
import io.iamcyw.ams.notification.cache.persistence.AlarmPO;
import io.quarkus.panache.common.Page;
import io.quarkus.scheduler.Scheduled;
import io.smallrye.common.annotation.Blocking;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.MessageProducer;
import io.vertx.core.shareddata.Lock;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NotificationPushScheduler {
    private static Logger logger = Logger.getLogger(NotificationPushScheduler.class);

    protected final Vertx vertx;

    protected final MessageProducer<PushAlarm> alarmProducer;

    public NotificationPushScheduler(Vertx vertx) {
        this.vertx = vertx;
        this.alarmProducer = vertx.eventBus().sender(PushAlarm.class.getSimpleName());
    }

    /**
     * 处理所有待发送就绪的警报
     * 默认 timeout 最长10s
     */
    @Scheduled(every = "{alarm.push.schedule:5s}", concurrentExecution = Scheduled.ConcurrentExecution.SKIP)
    @Blocking
    public void alarmPush() {
        Future<Lock> lockFuture = vertx.sharedData().getLockWithTimeout("alarm.push", 2 * 1000L);
        lockFuture.onSuccess(this::processAlarm);
    }

    private void processAlarm(Lock lock) {
        try {
            AlarmPO.findWaitImmediateAlarm().page(Page.ofSize(50)).stream().forEach(alarm -> {
                alarm.status = AlarmPO.AlarmStatus.Process;
                alarm.persistAndFlush();

                alarmProducer.write(new PushAlarm(alarm.id));
            });

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            lock.release();
        }
    }

}
