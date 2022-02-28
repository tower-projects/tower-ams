package io.iamcyw.ams.domain.job.strategy

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import javax.persistence.Entity

@Entity(name = "ALARM_STRATEGY_POLICY")
actual open class StrategyPolicyPO : PanacheEntityBase {
    companion object : PanacheCompanion<StrategyPolicyPO> {
        fun queryBySource(source: Long): List<StrategyPolicyPO> {
            return list("source_id", source)
        }
    }

    actual open var name: String = ""
    actual open var source: AlarmSourcePO? = null
    actual open var strategy: List<AlarmStrategyPO>? = null
    actual open var metadata: MutableSet<StrategyPolicyMetaPO>? = null

}