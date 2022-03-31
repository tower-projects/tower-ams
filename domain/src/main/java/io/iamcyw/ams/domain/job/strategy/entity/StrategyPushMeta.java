package io.iamcyw.ams.domain.job.strategy.entity;

import io.iamcyw.ams.domain.BasicEntity;

import javax.persistence.*;

@Entity(name = "ALARM_STRATEGY_PUSH_META")
public class StrategyPushMeta extends BasicEntity {

    @Column(name = "STRATEGY_PUSH_META_KEY")
    public String metaKey;

    @Column(name = "STRATEGY_PUSH_META_VALUE")
    public String metaValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PUSH_ID")
    public StrategyPush push;

    public String getMetaKey() {
        return metaKey;
    }

    public void setMetaKey(String metaKey) {
        this.metaKey = metaKey;
    }

    public String getMetaValue() {
        return metaValue;
    }

    public void setMetaValue(String metaValue) {
        this.metaValue = metaValue;
    }

    public StrategyPush getPush() {
        return push;
    }

    public void setPush(StrategyPush push) {
        this.push = push;
    }

}
