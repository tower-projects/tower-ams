package io.iamcyw.ams.domain.job.strategy

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import javax.persistence.Entity

/**
 * alarm ack 策略
 */
@Entity(name = "ALARM_STRATEGY_SOLVE")
actual class StrategySolvePO : PanacheEntityBase {

    companion object : PanacheCompanion<StrategySolvePO>
}