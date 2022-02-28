package io.iamcyw.ams.domain.job.receive

import io.iamcyw.ams.domain.job.AlarmMessage

@kotlinx.serialization.Serializable
data class ReceiveAlarm(
    /**
     * 警报源ID
     */
    val source: String,
    /**
     * 警报内容
     */
    val message: AlarmMessage,
)