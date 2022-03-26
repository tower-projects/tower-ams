package io.iamcyw.ams.domain.notification.cache.usecase;

import io.iamcyw.ams.domain.AlarmMessage;
import io.iamcyw.ams.domain.job.strategy.model.StrategyPushDO;

public record NotificationHandler(StrategyPushDO push, AlarmMessage payload) {
}
