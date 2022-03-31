package io.iamcyw.ams.domain.job.strategy.entity;

import io.iamcyw.ams.domain.BasicEntity;

import javax.persistence.*;

@Entity(name = "ALARM_STRATEGY_PREDICATE_META")
public class StrategyPredicateMeta extends BasicEntity {


    public MessageScope scope;

    public String contrastKey;

    public String contrastValue;

    public PolicyContrastType contrastType;

    @ManyToOne(fetch = FetchType.LAZY)
    public StrategyPredicate policy;

    public enum MessageScope {
        Headers,
        Payload
    }

    public enum PolicyContrastType {
        Fixed,
        Express,
        Pass
    }

    public MessageScope getScope() {
        return scope;
    }

    public void setScope(MessageScope scope) {
        this.scope = scope;
    }

    public String getContrastKey() {
        return contrastKey;
    }

    public void setContrastKey(String contrastKey) {
        this.contrastKey = contrastKey;
    }

    public String getContrastValue() {
        return contrastValue;
    }

    public void setContrastValue(String contrastValue) {
        this.contrastValue = contrastValue;
    }

    public PolicyContrastType getContrastType() {
        return contrastType;
    }

    public void setContrastType(PolicyContrastType contrastType) {
        this.contrastType = contrastType;
    }

    public StrategyPredicate getPolicy() {
        return policy;
    }

    public void setPolicy(StrategyPredicate policy) {
        this.policy = policy;
    }

}





