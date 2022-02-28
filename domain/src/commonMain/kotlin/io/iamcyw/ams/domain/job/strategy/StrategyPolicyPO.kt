package io.iamcyw.ams.domain.job.strategy

expect open class StrategyPolicyPO {
    open var name: String

    open var source: AlarmSourcePO?

    open var strategy: List<AlarmStrategyPO>?

    open var metadata: MutableSet<StrategyPolicyMetaPO>?
}