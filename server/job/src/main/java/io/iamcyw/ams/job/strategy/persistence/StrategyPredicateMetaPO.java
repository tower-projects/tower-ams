package io.iamcyw.ams.job.strategy.persistence;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;

@Entity(name = "ALARM_STRATEGY_PREDICATE_META")
public class StrategyPredicateMetaPO extends PanacheEntity {

    public MessageScope scope;

    public String contrastKey;

    public String contrastValue;

    public PolicyContrastType contrastType;

    @ManyToOne(fetch = FetchType.LAZY)
    public StrategyPredicatePO policy;

    public enum MessageScope {
        Headers, Payload
    }

    public enum PolicyContrastType {
        Fixed, Express, Pass
    }

}





