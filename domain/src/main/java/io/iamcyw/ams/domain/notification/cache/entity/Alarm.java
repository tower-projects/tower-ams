package io.iamcyw.ams.domain.notification.cache.entity;

import io.iamcyw.ams.domain.AlarmMessage;
import io.iamcyw.ams.domain.BasicEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "ALARM")
public class Alarm extends BasicEntity {

    @Column(name = "status")
    AlarmStatus status;

    @Column(name = "createTime")
    LocalDateTime createTime;

    @Column(name = "source")
    Long source;

    @Column(name = "strategy")
    long strategy;

    Long currentLevel = 0L;

    LocalDateTime checkLevelTime;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    String payload;

    @Column(name = "notificationTime")
    LocalDateTime notificationTime;

    @OneToMany
    Set<AlarmMeta> alarmMeta;

    public AlarmStatus getStatus() {
        return status;
    }

    public void setStatus(AlarmStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Long getSource() {
        return source;
    }

    public void setSource(Long source) {
        this.source = source;
    }

    public long getStrategy() {
        return strategy;
    }

    public void setStrategy(long strategy) {
        this.strategy = strategy;
    }

    public Long getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Long currentLevel) {
        this.currentLevel = currentLevel;
    }

    public LocalDateTime getCheckLevelTime() {
        return checkLevelTime;
    }

    public void setCheckLevelTime(LocalDateTime checkLevelTime) {
        this.checkLevelTime = checkLevelTime;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public LocalDateTime getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(LocalDateTime notificationTime) {
        this.notificationTime = notificationTime;
    }

    public Set<AlarmMeta> getAlarmMeta() {
        return alarmMeta;
    }

    public void setAlarmMeta(Set<AlarmMeta> alarmMeta) {
        this.alarmMeta = alarmMeta;
    }

    public AlarmMessage getAlarmMessage() {
        return new AlarmMessage(AlarmMeta.struct(alarmMeta), payload);
    }

    public enum AlarmStatus {
        /**
         * 需要发送
         */
        WAIT,

        /**
         * 正在被处理
         */
        PROCESS,

        /**
         * 发送完成
         */
        SENT
    }

}
