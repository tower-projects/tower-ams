package io.iamcyw.ams.domain.job.strategy

/**
 * alarm事件策略
 */
expect open class AlarmStrategyPO {
    open var id: Long?
    open var name: String
    open var source: AlarmSourcePO?
    open var alarmPolicies: MutableList<StrategyPolicyPO>?
    open var level: MutableSet<StrategyLevelPO>?
}