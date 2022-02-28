package io.iamcyw.ams.domain.job.strategy

expect class StrategyPushMetaPO {

     var metaKey: String

     var metaValue: String

     var push: StrategyPushPO?
}