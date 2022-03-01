package io.iamcyw.ams.job.strategy.persistence;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;

@Entity(name = "ALARM_STRATEGY_LEVEL_TARGET")
public class StrategyLevelTargetPO extends PanacheEntity {
    @Id
    @GeneratedValue
    public Long id;

    @ManyToOne
    @JoinColumn(name = "LEVEL_ID", nullable = false)
    private StrategyLevelPO strategyLevel;

    private TargetType targetType;

    private String value;

    enum TargetType {
        User, UserGroup, Address
    }

}
