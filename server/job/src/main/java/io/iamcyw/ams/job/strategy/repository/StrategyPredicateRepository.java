package io.iamcyw.ams.job.strategy.repository;

import io.iamcyw.ams.domain.job.strategy.entity.StrategyPredicate;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class StrategyPredicateRepository implements PanacheRepository<StrategyPredicate> {

    public List<StrategyPredicate> queryBySource(long source) {
        return list("source_id", source);
    }

}
