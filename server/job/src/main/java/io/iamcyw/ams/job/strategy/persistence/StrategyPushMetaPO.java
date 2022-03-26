package io.iamcyw.ams.job.strategy.persistence;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;

@Entity(name = "ALARM_STRATEGY_PUSH_META")
public class StrategyPushMetaPO extends PanacheEntity {

    @Column(name = "STRATEGY_PUSH_META_KEY")
    public String metaKey;

    @Column(name = "STRATEGY_PUSH_META_VALUE")
    public String metaValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PUSH_ID")
    public StrategyPushPO push;

}
