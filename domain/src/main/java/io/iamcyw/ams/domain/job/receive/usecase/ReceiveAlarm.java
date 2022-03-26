package io.iamcyw.ams.domain.job.receive.usecase;

import io.iamcyw.ams.domain.AlarmMessage;

public record ReceiveAlarm(String source, AlarmMessage message) {
}
