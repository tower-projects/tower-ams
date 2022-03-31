package io.iamcyw.ams.job.strategy.repository;

import io.iamcyw.ams.domain.job.strategy.entity.StrategyLevel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class StrategyLevelRepository implements PanacheRepository<StrategyLevel> {

    public Optional<StrategyLevel> findWithStrategyAndLevel(int level, long strategy) {
        return find("LEVEL = ?1 and STRATEGY_ID = ?2", level, strategy).singleResultOptional();
    }


}
