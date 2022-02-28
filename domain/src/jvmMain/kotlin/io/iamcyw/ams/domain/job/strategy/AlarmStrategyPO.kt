package io.iamcyw.ams.domain.job.strategy

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * alarm事件策略
 */
@Entity(name = "ALARM_STRATEGY")
actual open class AlarmStrategyPO : PanacheEntityBase {
    companion object : PanacheCompanion<AlarmSourcePO>;

    @field:Id
    @field:GeneratedValue
    actual open var id: Long? = null

    @get:Column(name = "name")
    actual open var name: String = ""

    @get:Column(name = "source_rrn")
    actual open var source: AlarmSourcePO? = null

    @get:Column(name = "alarm_policies")
    actual open var alarmPolicies: MutableList<StrategyPolicyPO>? = null

    @get:Column(name = "level")
    actual open var level: MutableSet<StrategyLevelPO>? = null

}