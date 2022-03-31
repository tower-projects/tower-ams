package io.iamcyw.ams.domain.job.strategy.entity;

import io.iamcyw.ams.domain.AlarmMessage;
import io.iamcyw.ams.domain.BasicEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity(name = "ALARM_STRATEGY_PREDICATE")
public class StrategyPredicate extends BasicEntity {

    @ManyToMany(targetEntity = AlarmStrategy.class, fetch = FetchType.LAZY, mappedBy = "alarmPolicies")
    public List<AlarmStrategy> strategy;

    public String name;

    @OneToMany(fetch = FetchType.LAZY)
    public Set<StrategyPredicateMeta> metadata;

    @ManyToOne(fetch = FetchType.LAZY)
    public AlarmSource source;

    public boolean predicatePayLoad(AlarmMessage alarmMessage) {
        return metadata.stream().allMatch(meta -> switch (meta.contrastType) {
            case Fixed -> fixed(meta.scope, meta.contrastKey, meta.contrastValue, alarmMessage);
            case Express -> express(meta.scope, meta.contrastKey, meta.contrastValue, alarmMessage);
            case Pass -> true;
        });
    }

    private boolean express(StrategyPredicateMeta.MessageScope scope, String contrastKey, String contrastValue,
                            AlarmMessage alarmMessage) {
        return false;
    }

    private boolean fixed(StrategyPredicateMeta.MessageScope scope, String contrastKey, String contrastValue,
                          AlarmMessage alarmMessage) {
        String actualValue = switch (scope) {
            case Headers -> alarmMessage.headers().get(contrastKey);
            // todo json
            case Payload -> "";
        };
        return Objects.equals(actualValue, contrastValue);
    }

    public List<AlarmStrategy> getStrategy() {
        return strategy;
    }

    public void setStrategy(List<AlarmStrategy> strategy) {
        this.strategy = strategy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<StrategyPredicateMeta> getMetadata() {
        return metadata;
    }

    public void setMetadata(Set<StrategyPredicateMeta> metadata) {
        this.metadata = metadata;
    }

    public AlarmSource getSource() {
        return source;
    }

    public void setSource(AlarmSource source) {
        this.source = source;
    }

}
