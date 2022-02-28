package io.iamcyw.ams.domain.job.strategy

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import javax.persistence.Entity

@Entity(name = "ALARM_STRATEGY_POLICY_META")
actual open class StrategyPolicyMetaPO : PanacheEntityBase {
    companion object : PanacheCompanion<StrategyPolicyMetaPO>;

    actual var scope: MessageScope? = null
    actual var contrastKey: String = ""
    actual var contrastValue: String = ""
    actual var contrastType: PolicyContrastType? = null

}