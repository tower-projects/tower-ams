package io.iamcyw.ams.domain.job.strategy

expect class StrategyLevelTargetPO {

    var strategyLevel: StrategyLevelPO?

    var type: TargetType?

    var vale: String

}

enum class TargetType {
    User, UserGroup, Address
}