package io.iamcyw.ams.domain.job.strategy

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import javax.persistence.Entity

@Entity(name = "ALARM_STRATEGY_PUSH")
actual class StrategyPushPO : PanacheEntityBase {

    companion object : PanacheCompanion<StrategyPushPO>

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