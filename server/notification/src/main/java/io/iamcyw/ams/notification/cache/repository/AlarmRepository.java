package io.iamcyw.ams.notification.cache.repository;

import io.iamcyw.ams.domain.notification.cache.entity.Alarm;
import io.iamcyw.ams.domain.notification.cache.entity.AlarmMeta;
import io.iamcyw.ams.domain.notification.cache.usecase.AddAlarm;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class AlarmRepository implements PanacheRepository<Alarm> {


    /**
     * 可以立即发送的警报
     * <p>
     * 假设发送需要的最大上限时间是5s，可以立即发送的则包括未来5s类的
     *
     * @return
     */
    public PanacheQuery<Alarm> findWaitImmediateAlarm() {
        return find("status = ?1 and notificationTime <= ?2", Sort.descending("createTime"), Alarm.AlarmStatus.WAIT,
                    LocalDateTime.now().plusSeconds(5));
    }

    public Alarm addAlarm(AddAlarm addAlarm) {
        Alarm alarmPO = new Alarm();
        alarmPO.setStrategy(addAlarm.getStrategy());
        alarmPO.setStatus(Alarm.AlarmStatus.WAIT);
        alarmPO.setSource(addAlarm.getSource());
        alarmPO.setAlarmMeta(AlarmMeta.struct(addAlarm.getPayload().headers()));
        alarmPO.setPayload(addAlarm.getPayload().payload());
        alarmPO.setNotificationTime(LocalDateTime.now());
        alarmPO.setCurrentLevel(addAlarm.getLevel());
        persist(alarmPO);
        return alarmPO;
    }

    /**
     * 过了1分钟仍然被锁定的任务
     * <p>
     * 意味着失败
     */

    public List<Alarm> findLockedAlarm() {
        return find("status = ?1 and notificationTime <= ?2", Sort.descending("createTime"), Alarm.AlarmStatus.PROCESS,
                    LocalDateTime.now().plusSeconds(-60)).list();
    }

}
