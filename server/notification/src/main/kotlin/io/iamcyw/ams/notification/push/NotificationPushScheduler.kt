package io.iamcyw.ams.notification.push

import io.iamcyw.ams.notification.cache.PushAlarm
import io.iamcyw.ams.notification.cache.RetryAlarm
import io.iamcyw.ams.notification.cache.entity.AlarmPO
import io.iamcyw.ams.notification.cache.entity.AlarmStatus
import io.iamcyw.tower.commandhandling.gateway.CommandGateway
import io.quarkus.panache.common.Page
import io.quarkus.scheduler.Scheduled
import io.smallrye.common.annotation.Blocking
import io.vertx.core.Vertx
import io.vertx.core.shareddata.Lock
import org.jboss.logging.Logger
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject


@ApplicationScoped
class NotificationPushScheduler @Inject constructor(
    private val vertx: Vertx,
    private val commandGateway: CommandGateway
) {
    val logger: Logger = Logger.getLogger(NotificationPushScheduler::class.qualifiedName)

    /**
     * 处理所有待发送就绪的警报
     * 默认 timeout 最长10s
     */
    @Scheduled(every = "{alarm.push.schedule:5s}", concurrentExecution = Scheduled.ConcurrentExecution.SKIP)
    @Blocking
    fun alarmPush() {
        val lock = vertx.sharedData().getLockWithTimeout("alarm.push", 2 * 1000L).result()
        if (lock != null) {
            processAlarm(lock)
        }
    }

    fun processAlarm(lock: Lock) {
        try {
            val alarms = AlarmPO.findWaitImmediateAlarm().page(Page.ofSize(50)).list()
            val sender = vertx.eventBus().sender<PushAlarm>(PushAlarm::class.qualifiedName)

            alarms.forEach { alarm ->
                alarm.status = AlarmStatus.Process
                alarm.persistAndFlush()

                sender.write(PushAlarm(alarm.id!!))
            }

        } catch (e: Exception) {
            logger.error(e.message, e)
        } finally {
            lock.release()
        }
    }

    /**
     * 针对不管因为什么原因失败导致过了超时时间alarm的status一直是Process的重试处理
     *
     */
//    @Scheduled(every = "{alarm.push.retry.schedule:60s}", concurrentExecution = Scheduled.ConcurrentExecution.SKIP)
    fun alarmPushRetry() {
        val lock = vertx.sharedData().getLockWithTimeout("alarm.push.retry", 5 * 1000L).result()
        processRetry(lock)
    }

    fun processRetry(lock: Lock) {
        try {
            val alarms = AlarmPO.findLockedAlarm().page(Page.ofSize(50)).list()

            alarms.forEach { alarm ->
                commandGateway.send(RetryAlarm(alarm.id!!))
            }
        } catch (e: Exception) {
            logger.error(e.message, e)
        } finally {
            lock.release()
        }
    }
}