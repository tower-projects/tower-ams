package io.iamcyw.ams.domain.job.predicate

import io.iamcyw.ams.domain.AlarmMessage

data class QueryAllowStrategy(val source: Long, val payload: AlarmMessage)