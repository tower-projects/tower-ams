package io.iamcyw.ams.domain.job.strategy.entity;

import io.iamcyw.ams.domain.AlarmMessage;
import io.iamcyw.ams.domain.BasicEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity(name = "ALARM_STRATEGY_PREDICATE")
public class StrategyPredicate extends BasicEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private AlarmSource source;

    @ManyToOne(targetEntity = AlarmStrategy.class, fetch = FetchType.LAZY)
    private AlarmStrategy strategy;

    private PolicyContrastType type;

    private String express;

    public boolean predicatePayLoad(AlarmMessage alarmMessage) {
        return switch (type) {
            case EXPRESS -> express(express, alarmMessage);
            case PASS -> true;
        };
    }

    /**
     * 比较目标字段的表达值
     *
     * @param express      表达式
     * @param alarmMessage 实际值
     * @return
     */
    private boolean express(String express, AlarmMessage alarmMessage) {
        //todo mvel
        return false;
    }

    public AlarmStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(AlarmStrategy strategy) {
        this.strategy = strategy;
    }

    public AlarmSource getSource() {
        return source;
    }

    public void setSource(AlarmSource source) {
        this.source = source;
    }

    public PolicyContrastType getType() {
        return type;
    }

    public void setType(PolicyContrastType type) {
        this.type = type;
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express;
    }

    public enum PolicyContrastType {
        EXPRESS,
        PASS
    }

}
