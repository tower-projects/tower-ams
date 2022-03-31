package io.iamcyw.ams.job.strategy;

import io.iamcyw.ams.domain.job.strategy.model.AlarmStrategyDO;
import io.iamcyw.ams.domain.job.strategy.model.StrategyPushDO;
import io.iamcyw.ams.domain.job.strategy.usecase.GetStrategyByID;
import io.iamcyw.ams.domain.job.strategy.usecase.GetStrategyTotal;
import io.iamcyw.ams.domain.job.strategy.usecase.QueryPushWithStrategy;
import io.iamcyw.ams.domain.job.strategy.usecase.QueryStrategyList;
import io.iamcyw.ams.job.strategy.persistence.AlarmStrategyPO;
import io.iamcyw.ams.job.strategy.persistence.StrategyPushPO;
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

    @QueryHandle
    public List<StrategyPushDO> queryPushWithStrategy(QueryPushWithStrategy queryPushWithStrategy) {
        return StrategyPushPO.<StrategyPushPO>stream("STRATEGY_ID", queryPushWithStrategy.strategy())
                             .map(StrategyPushPO::as).toList();
    }

    @QueryHandle
    @Query
    public AlarmStrategyDO getStrategyByID(GetStrategyByID query) {
        return AlarmStrategyPO.<AlarmStrategyPO>findById(query.id()).asWithSource();
    }

    @QueryHandle
    @Query
    public List<AlarmStrategyDO> queryStrategyList(QueryStrategyList query) {
        return AlarmStrategyPO.findAll().page(Pages.as(query.pagination())).<AlarmStrategyPO>stream()
                              .map(AlarmStrategyPO::asWithSource).toList();
    }

    @QueryHandle
    public Long getStrategyTotal(GetStrategyTotal query) {
        return AlarmStrategyPO.findAll().count();
    }

}
