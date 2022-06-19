package io.iamcyw.ams.domain.job.strategy.entity;

import io.iamcyw.ams.domain.BasicEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name = "ALARM_STRATEGY_LEVEL_TARGET")
public class StrategyLevelTarget extends BasicEntity {

    @ManyToOne
    private StrategyLevel strategyLevel;

    private TargetType targetType;

    private String value;

    public StrategyLevel getStrategyLevel() {
        return strategyLevel;
    }

    public void setStrategyLevel(StrategyLevel strategyLevel) {
        this.strategyLevel = strategyLevel;
    }

    public TargetType getTargetType() {
        return targetType;
    }

    public void setTargetType(TargetType targetType) {
        this.targetType = targetType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    enum TargetType {
        USER,
        USER_GROUP,
        ADDRESS
    }

}
