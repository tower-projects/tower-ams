package io.iamcyw.ams.domain.job.strategy

expect class StrategyLevelPO {

    /**
     * 当前级别
     *
     * 最低0
     */
    var level: Int?

    /**
     * 单位:分钟
     */
    var timeInterval: Long?

    var name: String

    var strategy: AlarmStrategyPO?

    var target: MutableSet<StrategyLevelTargetPO>?
}