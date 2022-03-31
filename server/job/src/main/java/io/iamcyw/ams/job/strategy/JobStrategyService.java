package io.iamcyw.ams.job.strategy;

import io.iamcyw.ams.domain.job.strategy.entity.AlarmStrategy;
import io.iamcyw.ams.domain.job.strategy.entity.StrategyPush;
import io.iamcyw.ams.domain.job.strategy.usecase.GetStrategyByID;
import io.iamcyw.ams.domain.job.strategy.usecase.GetStrategyTotal;
import io.iamcyw.ams.domain.job.strategy.usecase.QueryPushWithStrategy;
import io.iamcyw.ams.domain.job.strategy.usecase.QueryStrategyList;
import io.iamcyw.ams.job.strategy.repository.AlarmStrategyRepository;
import io.iamcyw.ams.job.strategy.repository.StrategyPushRepository;
import io.iamcyw.tower.common.Pages;
import io.iamcyw.tower.queryhandling.QueryHandle;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional
@GraphQLApi
public class JobStrategyService {

    private final StrategyPushRepository strategyPushRepository;

    private final AlarmStrategyRepository alarmStrategyRepository;

    public JobStrategyService(StrategyPushRepository strategyPushRepository,
                              AlarmStrategyRepository alarmStrategyRepository) {
        this.strategyPushRepository = strategyPushRepository;
        this.alarmStrategyRepository = alarmStrategyRepository;
    }

    @QueryHandle
    public List<StrategyPush> queryPushWithStrategy(QueryPushWithStrategy queryPushWithStrategy) {
        return strategyPushRepository.stream("STRATEGY_ID", queryPushWithStrategy.strategy()).toList();
    }

    @QueryHandle
    @Query
    public AlarmStrategy getStrategyByID(GetStrategyByID query) {
        return alarmStrategyRepository.findById(query.id());
    }

    @QueryHandle
    @Query
    public List<AlarmStrategy> queryStrategyList(QueryStrategyList query) {
        return alarmStrategyRepository.findAll().page(Pages.as(query.pagination())).stream().toList();
    }

    @QueryHandle
    public Long getStrategyTotal(GetStrategyTotal query) {
        return alarmStrategyRepository.findAll().count();
    }

}
