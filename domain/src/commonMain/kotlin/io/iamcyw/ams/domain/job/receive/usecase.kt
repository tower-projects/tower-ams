package io.iamcyw.ams.domain.job.receive

import io.iamcyw.ams.domain.AlarmMessage

data class ReceiveAlarm(val source: String, val message: AlarmMessage)