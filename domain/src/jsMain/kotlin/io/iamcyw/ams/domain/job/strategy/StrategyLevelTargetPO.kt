package io.iamcyw.ams.domain.job.strategy

@kotlinx.serialization.Serializable
actual class StrategyLevelTargetPO {
    actual var strategyLevel: StrategyLevelPO? = null
    actual var type: TargetType? = null
    actual var vale: String = ""
}