package io.iamcyw.ams.notification.cache;

import io.iamcyw.ams.domain.notification.cache.AddAlarm;
import io.iamcyw.ams.notification.cache.persistence.AlarmPO;
import io.iamcyw.tower.commandhandling.CommandHandle;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class NotificationCacheService {

    @CommandHandle
    public void handle(AddAlarm addAlarm) {
        AlarmPO.addAlarm(addAlarm);
    }

}
