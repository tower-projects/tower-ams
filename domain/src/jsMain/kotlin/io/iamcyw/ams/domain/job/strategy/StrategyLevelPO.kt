package io.iamcyw.ams.domain.job.strategy

@kotlinx.serialization.Serializable
actual class StrategyLevelPO {
    /**
     * 当前级别
     *
     * 最低0
     */
    actual var level: Int? = null

    /**
     * 单位:分钟
     */
    actual var timeInterval: Long? = null
    actual var name: String = ""
    actual var strategy: AlarmStrategyPO? = null
    actual var target: MutableSet<StrategyLevelTargetPO>? = null

}