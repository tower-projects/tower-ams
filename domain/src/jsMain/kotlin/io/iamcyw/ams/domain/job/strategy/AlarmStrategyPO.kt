package io.iamcyw.ams.domain.job.strategy

/**
 * alarm事件策略
 */
@kotlinx.serialization.Serializable
actual open class AlarmStrategyPO {
    actual open var id: Long? = null
    actual open var name: String = ""
    actual open var source: AlarmSourcePO? = null
    actual open var alarmPolicies: MutableList<StrategyPolicyPO>? = null
    actual open var level: MutableSet<StrategyLevelPO>? = null
}