package io.iamcyw.ams.job.receive;

import io.iamcyw.ams.domain.job.predicate.usecase.QueryAllowStrategy;
import io.iamcyw.ams.domain.job.receive.usecase.ReceiveAlarm;
import io.iamcyw.ams.domain.job.strategy.entity.AlarmSource;
import io.iamcyw.ams.domain.notification.cache.usecase.AddAlarm;
import io.iamcyw.ams.job.strategy.repository.AlarmSourceRepository;
import io.iamcyw.ams.job.strategy.repository.AlarmStrategyRepository;
import io.iamcyw.tower.commandhandling.CommandHandle;
import io.iamcyw.tower.commandhandling.gateway.CommandGateway;
import io.iamcyw.tower.queryhandling.gateway.QueryGateway;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class JobReceiveService {

    protected final QueryGateway queryGateway;

    protected final CommandGateway commandGateway;

    protected final AlarmSourceRepository alarmSourceRepository;

    protected final AlarmStrategyRepository strategyRepository;

    public JobReceiveService(QueryGateway queryGateway, CommandGateway commandGateway,
                             AlarmSourceRepository alarmSourceRepository, AlarmStrategyRepository strategyRepository) {
        this.queryGateway = queryGateway;
        this.commandGateway = commandGateway;
        this.alarmSourceRepository = alarmSourceRepository;
        this.strategyRepository = strategyRepository;
    }

    @CommandHandle
    public void handle(ReceiveAlarm receiveAlarm) {
        AlarmSource alarmSourcePO = alarmSourceRepository.findByName(receiveAlarm.source());
        List<Long> strategies = queryGateway.queries(
                new QueryAllowStrategy(alarmSourcePO.getId(), receiveAlarm.message()), Long.class);
        strategies.stream().map(strategyRepository::findById).filter(alarmStrategyPO -> {
            // todo 检查是否需要在单位时间内忽略重复警报
            if (alarmStrategyPO.repeatTimeInterval != 0L) {

            }
            return true;
        }).map(alarmStrategyPO -> new AddAlarm(alarmStrategyPO.getId(), alarmSourcePO.getId(), 0L,
                                               receiveAlarm.message())).forEach(commandGateway::send);

    }

}
