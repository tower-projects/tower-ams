package io.iamcyw.ams.job.strategy.repository;

import io.iamcyw.ams.domain.job.strategy.entity.StrategyPushMeta;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StrategyPushMetaRepository implements PanacheRepository<StrategyPushMeta> {


}
