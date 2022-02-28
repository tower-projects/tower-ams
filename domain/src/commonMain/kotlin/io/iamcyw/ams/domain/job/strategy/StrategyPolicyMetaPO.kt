package io.iamcyw.ams.domain.job.strategy

expect open class StrategyPolicyMetaPO {

    var scope: MessageScope?

    var contrastKey: String

    var contrastValue: String

    var contrastType: PolicyContrastType?


}

enum class MessageScope {
    Headers, Payload
}

enum class PolicyContrastType {
    Fixed, Express, Pass
}

