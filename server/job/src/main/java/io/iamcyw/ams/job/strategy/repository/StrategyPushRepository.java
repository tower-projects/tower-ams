package io.iamcyw.ams.job.strategy.repository;

import io.iamcyw.ams.domain.job.strategy.entity.StrategyPush;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StrategyPushRepository implements PanacheRepository<StrategyPush> {


}
