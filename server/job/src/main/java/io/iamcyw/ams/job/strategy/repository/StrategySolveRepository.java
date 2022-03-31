package io.iamcyw.ams.job.strategy.repository;

import io.iamcyw.ams.domain.job.strategy.entity.StrategySolve;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

/**
 * alarm ack 策略
 */
@ApplicationScoped
public class StrategySolveRepository implements PanacheRepository<StrategySolve> {

}
