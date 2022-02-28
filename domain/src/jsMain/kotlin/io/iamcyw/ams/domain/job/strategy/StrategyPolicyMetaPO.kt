package io.iamcyw.ams.domain.job.strategy

@kotlinx.serialization.Serializable
actual open class StrategyPolicyMetaPO {
    actual var scope: MessageScope? = null
    actual var contrastKey: String = ""
    actual var contrastValue: String = ""
    actual var contrastType: PolicyContrastType? = null

}