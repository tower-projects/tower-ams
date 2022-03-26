package io.iamcyw.ams.domain.notification.cache.usecase;

import io.iamcyw.ams.domain.AlarmMessage;

public record AddAlarm(Long strategy, Long source, Long level, AlarmMessage payload) {
}
