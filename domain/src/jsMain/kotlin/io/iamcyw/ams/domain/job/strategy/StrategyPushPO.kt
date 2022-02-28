package io.iamcyw.ams.domain.job.strategy

@kotlinx.serialization.Serializable
actual class StrategyPushPO {
    /**
     * 消息主题，可以包含表达式
     */
    actual var subject: String = ""
    actual var type: String = ""

    /**
     * 正文模板，可以包含表达式
     */
    actual var template: String = ""
    actual var metaPO: MutableSet<StrategyPushMetaPO>? = null

}