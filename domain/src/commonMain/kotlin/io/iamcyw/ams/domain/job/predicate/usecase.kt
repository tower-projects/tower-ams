package io.iamcyw.ams.domain.job.predicate

import io.iamcyw.ams.domain.job.AlarmMessage

/**
 * 在当前报警源下，匹配payload的Predicate
 *
 * @return 所有匹配的Strategy ID
 */
@kotlinx.serialization.Serializable
data class QueryAllowStrategy(val source: Long, val payload: AlarmMessage)

/**
 * 根据type用对应的匹配方法匹配payload
 *
 * 匹配成功返回Predicate ID
 *
 * @return predicate id
 */
@kotlinx.serialization.Serializable
data class PredicatePayLoad(
    val message: AlarmMessage,
    val policy: Long,
)