package io.iamcyw.ams.job.strategy.persistence;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

/**
 * alarm ack 策略
 */
@Entity(name = "ALARM_STRATEGY_SOLVE")
public class StrategySolvePO extends PanacheEntity {

}
