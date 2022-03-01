package io.iamcyw.ams.job.predicate;

import io.iamcyw.ams.domain.job.predicate.QueryAllowStrategy;
import io.iamcyw.ams.job.strategy.persistence.StrategyPolicyPO;
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
        List<StrategyPolicyPO> policyList = StrategyPolicyPO.queryBySource(queryAllowStrategy.getSource());

        return policyList.stream()
                         .filter(strategyPolicyPO -> strategyPolicyPO.predicatePayLoad(queryAllowStrategy.getPayload()))
                         .flatMap(strategyPolicyPO -> strategyPolicyPO.strategy.stream()
                                                                               .map(alarmStrategyPO -> alarmStrategyPO.id))
                         .distinct()
                         .collect(Collectors.toList());
    }

}
