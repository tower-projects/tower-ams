package io.iamcyw.ams.domain.job.strategy

@kotlinx.serialization.Serializable
actual open class StrategyPolicyPO {
    actual open var name: String = ""
    actual open var source: AlarmSourcePO? = null
    actual open var strategy: List<AlarmStrategyPO>? = null
    actual open var metadata: MutableSet<StrategyPolicyMetaPO>? = null

}