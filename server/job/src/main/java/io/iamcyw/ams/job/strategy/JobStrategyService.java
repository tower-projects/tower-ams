package io.iamcyw.ams.job.strategy;

import io.iamcyw.ams.domain.job.strategy.model.AlarmStrategyDO;
import io.iamcyw.ams.domain.job.strategy.model.StrategyPushDO;
import io.iamcyw.ams.domain.job.strategy.usecase.QueryPushWithStrategy;
import io.iamcyw.ams.domain.job.strategy.usecase.QueryStrategyList;
import io.iamcyw.ams.job.strategy.persistence.AlarmStrategyPO;
import io.iamcyw.ams.job.strategy.persistence.StrategyPushPO;
import io.iamcyw.tower.queryhandling.QueryHandle;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class JobStrategyService {

    @QueryHandle
    public List<StrategyPushDO> query(QueryPushWithStrategy queryPushWithStrategy) {
        return StrategyPushPO.<StrategyPushPO>stream("STRATEGY_ID", queryPushWithStrategy.strategy())
                             .map(StrategyPushPO::as).toList();
    }

    @QueryHandle
    public List<AlarmStrategyDO> query(QueryStrategyList query) {
        return AlarmStrategyPO.<AlarmStrategyPO>streamAll().map(AlarmStrategyPO::asWithSource).toList();
    }


}
