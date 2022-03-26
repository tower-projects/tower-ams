package io.iamcyw.ams.notification.cache.persistence;

import io.iamcyw.ams.domain.AlarmMessage;
import io.iamcyw.ams.domain.notification.cache.usecase.AddAlarm;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity(name = "ALARM")
public class AlarmPO extends PanacheEntity {

    @Column(name = "status")
    public AlarmStatus status;

    @Column(name = "createTime")
    @CreationTimestamp
    public LocalDateTime createTime;

    @Column(name = "source")
    public Long source;

    @Column(name = "strategy")
    public long strategy;

    public Long currentLevel = 0L;

    public LocalDateTime checkLevelTime;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    public String payload;

    @Column(name = "notificationTime")
    public LocalDateTime notificationTime;

    @OneToMany
    public Set<AlarmMetaPO> alarmMeta;

    /**
     * 可以立即发送的警报
     * <p>
     * 假设发送需要的最大上限时间是5s，可以立即发送的则包括未来5s类的
     *
     * @return
     */
    public static PanacheQuery<AlarmPO> findWaitImmediateAlarm() {
        return find("status = ?1 and notificationTime <= ?2", Sort.descending("createTime"), AlarmStatus.Wait,
                    LocalDateTime.now().plusSeconds(5));
    }

    public static AlarmPO addAlarm(AddAlarm addAlarm) {
        AlarmPO alarmPO = new AlarmPO();
        alarmPO.strategy = addAlarm.strategy();
        alarmPO.status = AlarmPO.AlarmStatus.Wait;
        alarmPO.source = addAlarm.source();
        alarmPO.alarmMeta = AlarmMetaPO.struct(addAlarm.payload().headers());
        alarmPO.payload = addAlarm.payload().payload();
        alarmPO.notificationTime = LocalDateTime.now();
        alarmPO.currentLevel = addAlarm.level();
        alarmPO.persist();
        return alarmPO;
    }

    /**
     * 过了1分钟仍然被锁定的任务
     * <p>
     * 意味着失败
     */

    public static List<AlarmPO> findLockedAlarm() {
        return find("status = ?1 and notificationTime <= ?2", Sort.descending("createTime"), AlarmStatus.Process,
                    LocalDateTime.now().plusSeconds(-60)).list();
    }

    public AlarmMessage getAlarmMessage() {
        return new AlarmMessage(AlarmMetaPO.struct(alarmMeta), payload);
    }

    public enum AlarmStatus {
        /**
         * 需要发送
         */
        Wait,

        /**
         * 正在被处理
         */
        Process,

        /**
         * 发送完成
         */
        Sent
    }

}
