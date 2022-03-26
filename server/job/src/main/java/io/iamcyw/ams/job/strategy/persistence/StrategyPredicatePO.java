package io.iamcyw.ams.job.strategy.persistence;

import io.iamcyw.ams.domain.AlarmMessage;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity(name = "ALARM_STRATEGY_PREDICATE")
public class StrategyPredicatePO extends PanacheEntity {

    @ManyToMany(targetEntity = AlarmStrategyPO.class, fetch = FetchType.LAZY, mappedBy = "alarmPolicies")
    public List<AlarmStrategyPO> strategy;

    public String name;

    @OneToMany(fetch = FetchType.LAZY)
    public Set<StrategyPredicateMetaPO> metadata;

    @ManyToOne(fetch = FetchType.LAZY)
    public AlarmSourcePO source;

    public static List<StrategyPredicatePO> queryBySource(long source) {
        return list("source_id", source);
    }

    public boolean predicatePayLoad(AlarmMessage alarmMessage) {
        return metadata.stream().allMatch(meta -> switch (meta.contrastType) {
            case Fixed -> fixed(meta.scope, meta.contrastKey, meta.contrastValue, alarmMessage);
            case Express -> express(meta.scope, meta.contrastKey, meta.contrastValue, alarmMessage);
            case Pass -> true;
        });
    }

    private boolean express(StrategyPredicateMetaPO.MessageScope scope, String contrastKey, String contrastValue,
                            AlarmMessage alarmMessage) {
        return false;
    }

    private boolean fixed(StrategyPredicateMetaPO.MessageScope scope, String contrastKey, String contrastValue,
                          AlarmMessage alarmMessage) {
        String actualValue = switch (scope) {
            case Headers -> alarmMessage.headers().get(contrastKey);
            // todo json
            case Payload -> "";
        };
        return Objects.equals(actualValue, contrastValue);
    }

}
