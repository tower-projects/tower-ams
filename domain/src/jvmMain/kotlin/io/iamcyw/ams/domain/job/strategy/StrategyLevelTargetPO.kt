package io.iamcyw.ams.domain.job.strategy

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase

actual class StrategyLevelTargetPO :PanacheEntityBase{

    companion object: PanacheCompanion<StrategyLevelTargetPO>;

    actual var strategyLevel: StrategyLevelPO? = null
    actual var type: TargetType? = null
    actual var vale: String = ""

}