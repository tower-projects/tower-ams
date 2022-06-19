package io.iamcyw.ams.job.predicate;

import io.iamcyw.ams.domain.job.predicate.usecase.QueryAllowStrategy;
import io.iamcyw.ams.domain.job.strategy.entity.StrategyPredicate;
import io.iamcyw.ams.job.strategy.repository.StrategyPredicateRepository;
import io.iamcyw.tower.messaging.QueryHandle;
import io.iamcyw.tower.messaging.UseCase;
import io.iamcyw.tower.messaging.gateway.MessageGateway;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@UseCase
public class JobPredicateService {

    protected final MessageGateway messageGateway;

    protected final StrategyPredicateRepository predicateRepository;

    public JobPredicateService(MessageGateway messageGateway, StrategyPredicateRepository predicateRepository) {
        this.messageGateway = messageGateway;
        this.predicateRepository = predicateRepository;
    }

    @QueryHandle
    public Set<Long> query(QueryAllowStrategy queryAllowStrategy) {
        List<StrategyPredicate> policyList = predicateRepository.queryBySource(queryAllowStrategy.source());

        return policyList.stream().parallel().filter(policy -> policy.predicatePayLoad(queryAllowStrategy.payload()))
                         .map(strategy -> strategy.getStrategy().getId()).collect(Collectors.toUnmodifiableSet());
    }

}
