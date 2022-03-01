package io.iamcyw.ams.domain.job.strategy

data class QueryAlarmSource(var id: Long)

class QueryAllSource()

data class AddAlarmSource(var name: String, var comment: String)

data class DeleteAlarmSource(var id: Long)

data class UpdateAlarmSource(var id: Long, var name: String, var comment: String)

data class QueryNextLevelTimeIntervalWithStrategy(val strategy: Long, val currentLevel: Long)

data class QueryPushWithStrategy(var strategy: Long)

data class StrategyPush(
    val id: Long,
    val subject: String,
    val template: String,
    val meta: StrategyPushMeta
)

sealed class StrategyPushMeta(
    protected val meta: MutableMap<String, String>
) {

    class EmailPushMeta() : StrategyPushMeta(mutableMapOf()) {

        constructor(address: String) : this() {
            meta["address"] = address
        }

        fun getAddress() = meta["address"]
        fun setAddress(address: String) {
            meta["address"] = address
        }

    }


}