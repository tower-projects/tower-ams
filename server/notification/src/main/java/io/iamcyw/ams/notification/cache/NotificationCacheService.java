package io.iamcyw.ams.notification.cache;

import io.iamcyw.ams.domain.notification.cache.usecase.AddAlarm;
import io.iamcyw.ams.notification.cache.repository.AlarmRepository;
import io.iamcyw.tower.messaging.CommandHandle;
import io.iamcyw.tower.messaging.UseCase;
import io.smallrye.common.annotation.Blocking;

import javax.transaction.Transactional;

@UseCase
@Transactional
public class NotificationCacheService {

    private final AlarmRepository alarmRepository;

    public NotificationCacheService(AlarmRepository alarmRepository) {
        this.alarmRepository = alarmRepository;
    }

    @CommandHandle
    public void handle(AddAlarm addAlarm) {
        alarmRepository.addAlarm(addAlarm);
    }

}
