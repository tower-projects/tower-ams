package io.iamcyw.ams.domain.job

import io.iamcyw.ams.domain.job.strategy.MessageScope
import kotlinx.serialization.json.*


fun AlarmMessage.fixed(
    scope: MessageScope, contrastKey: String,
    contrastValue: String
): Boolean {
    val actualValue = when (scope) {
        MessageScope.Headers -> this.headers[contrastKey]
        MessageScope.Payload -> {
            var jsonElement = Json.parseToJsonElement(this.payload)
            contrastKey.split(".").forEach {
                jsonElement = when (jsonElement) {
                    is JsonArray -> jsonElement.jsonArray[it.toInt()]
                    is JsonObject -> jsonElement.jsonObject.get(it) ?: JsonNull
                    else -> jsonElement
                }
            }
            (jsonElement as? JsonPrimitive)?.content ?: ""
        }
    }
    return contrastValue == actualValue
}

fun AlarmMessage.express(
    scope: MessageScope, contrastKey: String,
    contrastValue: String
): Boolean {
    //todo use express language
    val kv = when (scope) {
        MessageScope.Headers -> this.headers
        MessageScope.Payload -> {
            Json.parseToJsonElement(this.payload).toKV()
        }
    }

    return true
}

fun JsonElement.toKV(): Any? {
    return when (this) {
        is JsonObject -> {
            val jsonObj = mutableMapOf<String, Any?>()
            this.jsonObject.forEach { key, value ->
                jsonObj[key] = value.toKV()
            }
            jsonObj
        }
        JsonNull -> null
        is JsonArray -> {
            val jsonObj = mutableListOf<Any?>()
            this.jsonArray.forEach {
                jsonObj.add(it.toKV())
            }
            jsonObj
        }
        is JsonPrimitive -> {
            this.jsonPrimitive.contentOrNull
        }
    }
}

