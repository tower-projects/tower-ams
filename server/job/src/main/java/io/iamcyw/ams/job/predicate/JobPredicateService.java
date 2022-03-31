package io.iamcyw.ams.job.predicate;

import io.iamcyw.ams.domain.job.predicate.usecase.QueryAllowStrategy;
import io.iamcyw.ams.domain.job.strategy.entity.StrategyPredicate;
import io.iamcyw.ams.job.strategy.repository.StrategyPredicateRepository;
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

    protected final StrategyPredicateRepository predicateRepository;

    public JobPredicateService(QueryGateway queryGateway, CommandGateway commandGateway,
                               StrategyPredicateRepository predicateRepository) {
        this.queryGateway = queryGateway;
        this.commandGateway = commandGateway;
        this.predicateRepository = predicateRepository;
    }


    @QueryHandle
    public List<Long> query(QueryAllowStrategy queryAllowStrategy) {
        List<StrategyPredicate> policyList = predicateRepository.queryBySource(queryAllowStrategy.source());

        return policyList.stream().filter(policy -> policy.predicatePayLoad(queryAllowStrategy.payload())).flatMap(
                                 strategy -> strategy.strategy.stream().map(alarmStrategyPO -> alarmStrategyPO.getId())).distinct()
                         .collect(Collectors.toList());
    }

}
