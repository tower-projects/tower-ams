package io.iamcyw.ams.job.receive;

import io.iamcyw.ams.domain.job.predicate.usecase.QueryAllowStrategy;
import io.iamcyw.ams.domain.job.receive.usecase.ReceiveAlarm;
import io.iamcyw.ams.domain.job.strategy.entity.AlarmSource;
import io.iamcyw.ams.domain.job.strategy.usecase.GetStrategyByName;
import io.iamcyw.ams.domain.notification.cache.usecase.AddAlarm;
import io.iamcyw.ams.job.AlarmJobServerMessages;
import io.iamcyw.ams.job.strategy.repository.AlarmStrategyRepository;
import io.iamcyw.tower.messaging.CommandHandle;
import io.iamcyw.tower.messaging.UseCase;
import io.iamcyw.tower.messaging.gateway.MessageGateway;

import javax.transaction.Transactional;
import java.util.List;

@UseCase
@Transactional
public class JobReceiveService {

    protected final MessageGateway messageGateway;


    protected final AlarmStrategyRepository strategyRepository;

    public JobReceiveService(MessageGateway messageGateway, AlarmStrategyRepository strategyRepository) {
        this.messageGateway = messageGateway;
        this.strategyRepository = strategyRepository;
    }

    @CommandHandle
    public void handle(ReceiveAlarm receiveAlarm) {
        AlarmSource alarmSourcePO = messageGateway.query(new GetStrategyByName(receiveAlarm.source()),
                                                         AlarmSource.class);

        if (alarmSourcePO == null) {
            throw AlarmJobServerMessages.msg.sourceNameNotExist(receiveAlarm.source());
        }

        List<Long> strategies = messageGateway.queries(
                new QueryAllowStrategy(alarmSourcePO.getId(), receiveAlarm.message()), Long.class);
        strategies.stream().map(strategyRepository::findById).filter(alarmStrategyPO -> {
            // todo 检查是否需要在单位时间内忽略重复警报
            if (alarmStrategyPO.getRepeatTimeInterval() != 0L) {

            }
            return true;
        }).map(alarmStrategyPO -> new AddAlarm(alarmStrategyPO.getId(), alarmSourcePO.getId(), 0L,
                                               receiveAlarm.message())).forEach(messageGateway::send);

    }

}
