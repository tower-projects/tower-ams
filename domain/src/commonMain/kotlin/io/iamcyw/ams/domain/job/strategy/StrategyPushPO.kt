package io.iamcyw.ams.domain.job.strategy

expect class StrategyPushPO {

    /**
     * 消息主题，可以包含表达式
     */
    var subject: String

    var type: String

    /**
     * 正文模板，可以包含表达式
     */
    var template: String

    var metaPO: MutableSet<StrategyPushMetaPO>?
}