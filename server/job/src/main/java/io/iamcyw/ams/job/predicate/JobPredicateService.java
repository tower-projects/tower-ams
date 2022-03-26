package io.iamcyw.ams.job.predicate;

import io.iamcyw.ams.domain.job.predicate.usecase.QueryAllowStrategy;
import io.iamcyw.ams.job.strategy.persistence.StrategyPredicatePO;
import io.iamcyw.tower.commandhandling.gateway.CommandGateway;
import io.iamcyw.tower.queryhandling.QueryHandle;
import io.iamcyw.tower.queryhandling.gateway.QueryGateway;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class JobPredicateService {

    protected final QueryGateway queryGateway;

    protected final CommandGateway commandGateway;

    public JobPredicateService(QueryGateway queryGateway, CommandGateway commandGateway) {
        this.queryGateway = queryGateway;
        this.commandGateway = commandGateway;
    }


    @QueryHandle
    public List<Long> query(QueryAllowStrategy queryAllowStrategy) {
        List<StrategyPredicatePO> policyList = StrategyPredicatePO.queryBySource(queryAllowStrategy.source());

        return policyList.stream().filter(strategyPredicatePO -> strategyPredicatePO.predicatePayLoad(
                                 queryAllowStrategy.payload())).flatMap(
                                 strategyPredicatePO -> strategyPredicatePO.strategy.stream().map(alarmStrategyPO -> alarmStrategyPO.id))
                         .distinct().collect(Collectors.toList());
    }

}
