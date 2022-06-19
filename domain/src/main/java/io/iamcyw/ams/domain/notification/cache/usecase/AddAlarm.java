package io.iamcyw.ams.domain.notification.cache.usecase;

// public record AddAlarm(Long strategy, Long source, Long level, AlarmMessage payload) {
//     @JsonbCreator
//     public AddAlarm {
//     }
//
// }

import io.iamcyw.ams.domain.AlarmMessage;

public class AddAlarm {

    Long strategy;

    Long source;

    Long level;

    AlarmMessage payload;

    public AddAlarm() {

    }

    public AddAlarm(Long strategy, Long source, long level, AlarmMessage message) {
        this.strategy = strategy;
        this.source = source;
        this.level = level;
        this.payload = message;
    }

    public Long getStrategy() {
        return strategy;
    }

    public void setStrategy(Long strategy) {
        this.strategy = strategy;
    }

    public Long getSource() {
        return source;
    }

    public void setSource(Long source) {
        this.source = source;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public AlarmMessage getPayload() {
        return payload;
    }

    public void setPayload(AlarmMessage payload) {
        this.payload = payload;
    }

}
