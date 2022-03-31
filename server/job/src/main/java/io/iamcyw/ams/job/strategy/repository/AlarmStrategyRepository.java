package io.iamcyw.ams.job.strategy.repository;

import io.iamcyw.ams.domain.job.strategy.entity.AlarmStrategy;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

/**
 * alarm事件策略
 */
@ApplicationScoped
public class AlarmStrategyRepository implements PanacheRepository<AlarmStrategy> {

}
