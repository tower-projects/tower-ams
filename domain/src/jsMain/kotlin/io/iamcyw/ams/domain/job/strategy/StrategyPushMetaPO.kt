package io.iamcyw.ams.domain.job.strategy

@kotlinx.serialization.Serializable
actual class StrategyPushMetaPO {
    actual var metaKey: String = ""
    actual var metaValue: String = ""
    actual var push: StrategyPushPO? = null

}