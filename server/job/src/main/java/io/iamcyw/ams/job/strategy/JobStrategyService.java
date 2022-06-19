package io.iamcyw.ams.job.strategy;

import io.iamcyw.ams.domain.job.source.usecase.IsSourceExist;
import io.iamcyw.ams.domain.job.strategy.entity.AlarmStrategy;
import io.iamcyw.ams.domain.job.strategy.entity.StrategyPush;
import io.iamcyw.ams.domain.job.strategy.usecase.*;
import io.iamcyw.ams.job.AlarmJobServerMessages;
import io.iamcyw.ams.job.strategy.repository.AlarmStrategyRepository;
import io.iamcyw.ams.job.strategy.repository.StrategyPushRepository;
import io.iamcyw.tower.common.Pages;
import io.iamcyw.tower.messaging.CommandHandle;
import io.iamcyw.tower.messaging.QueryHandle;
import io.iamcyw.tower.messaging.UseCase;
import io.iamcyw.tower.messaging.gateway.MessageGateway;

import javax.transaction.Transactional;
import java.util.List;

@UseCase
@Transactional
public class JobStrategyService {

    private final StrategyPushRepository strategyPushRepository;

    private final AlarmStrategyRepository alarmStrategyRepository;

    private final MessageGateway messageGateway;

    public JobStrategyService(StrategyPushRepository strategyPushRepository,
                              AlarmStrategyRepository alarmStrategyRepository, MessageGateway messageGateway) {
        this.strategyPushRepository = strategyPushRepository;
        this.alarmStrategyRepository = alarmStrategyRepository;
        this.messageGateway = messageGateway;
    }

    @QueryHandle
    public List<StrategyPush> queryPushWithStrategy(QueryPushWithStrategy queryPushWithStrategy) {
        return strategyPushRepository.stream("STRATEGY_ID", queryPushWithStrategy.strategy()).toList();
    }

    @CommandHandle
    public void createStrategy(CreateStrategy command) {
        boolean isExist = messageGateway.query(new IsSourceExist(command.source()), Boolean.class);
        if (!isExist) {
            throw AlarmJobServerMessages.msg.sourceNotExist(command.source());
        }
        alarmStrategyRepository.persist(AlarmStrategy.createStrategy(command.source(), command.name(), command.desc()));
    }

    @QueryHandle
    public AlarmStrategy getStrategyByID(GetStrategyByID query) {
        return alarmStrategyRepository.findById(query.id());
    }

    @QueryHandle
    public List<AlarmStrategy> queryStrategyList(QueryStrategyList query) {
        return alarmStrategyRepository.findAll().page(Pages.as(query.pagination())).stream().toList();
    }

    @QueryHandle
    public Long getStrategyTotal(GetStrategyTotal query) {
        return alarmStrategyRepository.findAll().count();
    }

}
