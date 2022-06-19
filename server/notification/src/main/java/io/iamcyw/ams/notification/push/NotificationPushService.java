package io.iamcyw.ams.notification.push;

import io.iamcyw.ams.domain.job.strategy.model.StrategyPushDO;
import io.iamcyw.ams.domain.job.strategy.usecase.QueryNextLevelTimeIntervalWithStrategy;
import io.iamcyw.ams.domain.job.strategy.usecase.QueryPushWithStrategy;
import io.iamcyw.ams.domain.notification.cache.entity.Alarm;
import io.iamcyw.ams.domain.notification.cache.usecase.NotificationHandler;
import io.iamcyw.ams.domain.notification.cache.usecase.PushAlarm;
import io.iamcyw.ams.notification.cache.repository.AlarmRepository;
import io.iamcyw.tower.messaging.CommandHandle;
import io.iamcyw.tower.messaging.Parameter;
import io.iamcyw.tower.messaging.Predicate;
import io.iamcyw.tower.messaging.UseCase;
import io.iamcyw.tower.messaging.gateway.MessageGateway;
import io.quarkus.vertx.ConsumeEvent;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@UseCase
@Transactional
public class NotificationPushService {

    protected final MessageGateway messageGateway;

    protected final AlarmRepository alarmRepository;

    public NotificationPushService(MessageGateway messageGateway, AlarmRepository alarmRepository) {
        this.messageGateway = messageGateway;
        this.alarmRepository = alarmRepository;
    }

    @ConsumeEvent(value = "io.iamcyw.ams.notification.cache.PushAlarm", blocking = true)
    @CommandHandle
    public void handle(PushAlarm pushAlarm) {
        Optional<Alarm> optionalAlarmPO = alarmRepository.findByIdOptional(pushAlarm.alarm());

        optionalAlarmPO = optionalAlarmPO.filter(alarmPO -> alarmPO.getStatus().equals(Alarm.AlarmStatus.PROCESS));

        if (optionalAlarmPO.isPresent()) {
            Alarm alarmPO = optionalAlarmPO.get();
            List<StrategyPushDO> strategyPushList = messageGateway.queries(
                    new QueryPushWithStrategy(alarmPO.getStrategy()), StrategyPushDO.class);

            for (StrategyPushDO strategyPush : strategyPushList) {
                messageGateway.send(new NotificationHandler(strategyPush, alarmPO.getAlarmMessage()));
            }

            //todo 可以间隔循环发送？

            //检查有没有下一层需要升级
            Long levelTimeInterval = messageGateway.query(
                    new QueryNextLevelTimeIntervalWithStrategy(alarmPO.getStrategy(), alarmPO.getCurrentLevel()),
                    Long.class);
            alarmPO.setCheckLevelTime(LocalDateTime.now().plusMinutes(levelTimeInterval));
        }
    }

    @Predicate()
    public boolean noticeType(NotificationHandler notificationHandler, @Parameter("type") String type) {
        return notificationHandler.push().meta().type().equals(type);
    }


}
