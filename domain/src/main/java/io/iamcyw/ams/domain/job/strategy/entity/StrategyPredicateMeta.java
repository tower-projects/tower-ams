package io.iamcyw.ams.domain.job.strategy.entity;

import io.iamcyw.ams.domain.BasicEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity(name = "ALARM_STRATEGY_PREDICATE_META")
public class StrategyPredicateMeta extends BasicEntity {

    MessageScope scope;

    String contrastKey;

    String contrastValue;

    PolicyContrastType contrastType;

    @ManyToOne(fetch = FetchType.LAZY)
    StrategyPredicate policy;

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

    public enum MessageScope {
        HEADERS,
        PAYLOAD
    }

    public enum PolicyContrastType {
        FIXED,
        EXPRESS,
        PASS
    }

}





