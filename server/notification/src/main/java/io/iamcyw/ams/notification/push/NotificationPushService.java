package io.iamcyw.ams.notification.push;

import io.iamcyw.ams.domain.job.strategy.QueryNextLevelTimeIntervalWithStrategy;
import io.iamcyw.ams.domain.job.strategy.QueryPushWithStrategy;
import io.iamcyw.ams.domain.job.strategy.StrategyPush;
import io.iamcyw.ams.domain.notification.cache.NotificationHandler;
import io.iamcyw.ams.domain.notification.cache.PushAlarm;
import io.iamcyw.ams.notification.cache.persistence.AlarmPO;
import io.iamcyw.tower.commandhandling.CommandHandle;
import io.iamcyw.tower.commandhandling.gateway.CommandGateway;
import io.iamcyw.tower.messaging.predicate.PredicateHandle;
import io.iamcyw.tower.queryhandling.gateway.QueryGateway;
import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class NotificationPushService {

    private final QueryGateway queryGateway;

    private final CommandGateway commandGateway;

    public NotificationPushService(QueryGateway queryGateway, CommandGateway commandGateway) {
        this.queryGateway = queryGateway;
        this.commandGateway = commandGateway;
    }

    @ConsumeEvent(value = "io.iamcyw.ams.notification.cache.PushAlarm", blocking = true)
    @CommandHandle
    public void handle(PushAlarm pushAlarm) {
        Optional<AlarmPO> optionalAlarmPO = AlarmPO.findByIdOptional(pushAlarm.getAlarm());
        optionalAlarmPO = optionalAlarmPO.filter(alarmPO -> alarmPO.status.equals(AlarmPO.AlarmStatus.Process));

        if (optionalAlarmPO.isPresent()) {
            AlarmPO alarmPO = optionalAlarmPO.get();
            List<StrategyPush> strategyPushList = queryGateway.queries(new QueryPushWithStrategy(alarmPO.strategy));

            for (StrategyPush strategyPush : strategyPushList) {
                commandGateway.send(new NotificationHandler(strategyPush, alarmPO.getAlarmMessage()));
            }

            //todo 可以间隔循环发送？

            //检查有没有下一层需要升级
            Long levelTimeInterval = queryGateway.query(
                    new QueryNextLevelTimeIntervalWithStrategy(alarmPO.strategy, alarmPO.currentLevel));
            alarmPO.checkLevelTime = LocalDateTime.now().plusMinutes(levelTimeInterval);
        }
    }

    @PredicateHandle("noticeType")
    public boolean predicate(NotificationHandler notificationHandler, Class<?> type) {
        return notificationHandler.getPush().getClass().equals(type);
    }


}
