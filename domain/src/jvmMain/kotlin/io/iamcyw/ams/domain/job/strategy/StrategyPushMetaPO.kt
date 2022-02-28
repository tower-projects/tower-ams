package io.iamcyw.ams.domain.job.strategy

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity(name = "ALARM_STRATEGY_PUSH_META")
actual class StrategyPushMetaPO : PanacheEntityBase {

    companion object : PanacheCompanion<StrategyPushMetaPO>

    actual var metaKey: String = ""

    actual var metaValue: String = ""

    @get:ManyToOne(fetch = FetchType.LAZY)
    @get:JoinColumn(name = "PUSH_ID")
    actual var push: StrategyPushPO? = null

}