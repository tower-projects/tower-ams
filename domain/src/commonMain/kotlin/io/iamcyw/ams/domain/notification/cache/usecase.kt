package io.iamcyw.ams.domain.notification.cache

import io.iamcyw.ams.domain.AlarmMessage
import io.iamcyw.ams.domain.job.strategy.StrategyPush

data class AddAlarm(val strategy: Long, val source: Long, val level: Long, val payload: AlarmMessage)

/**
 * 处理alarm通知
 */
data class PushAlarm(val alarm: Long)

/**
 * 重试alarm通知
 */
data class RetryAlarm(val alarm: Long)

/**
 * 实际通知的处理器
 */
data class NotificationHandler(
        val push: StrategyPush,
        val payload: AlarmMessage
)