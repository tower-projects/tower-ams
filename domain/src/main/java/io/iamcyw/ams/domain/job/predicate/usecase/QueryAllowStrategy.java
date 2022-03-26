package io.iamcyw.ams.domain.job.predicate.usecase;

import io.iamcyw.ams.domain.AlarmMessage;

public record QueryAllowStrategy(Long source, AlarmMessage payload) {
}
