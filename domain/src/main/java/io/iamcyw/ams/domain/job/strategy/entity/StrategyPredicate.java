package io.iamcyw.ams.domain.job.strategy.entity;

import io.iamcyw.ams.domain.AlarmMessage;
import io.iamcyw.ams.domain.BasicEntity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity(name = "ALARM_STRATEGY_PREDICATE")
public class StrategyPredicate extends BasicEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private AlarmSource source;

    @ManyToOne(targetEntity = AlarmStrategy.class, fetch = FetchType.LAZY)
    private AlarmStrategy strategy;

    @Deprecated
    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "predicate_id")
    private Set<StrategyPredicateMeta> metadata;

    public boolean predicatePayLoad(AlarmMessage alarmMessage) {
        return metadata.stream().allMatch(meta -> switch (meta.contrastType) {
            case FIXED -> fixed(meta.scope, meta.contrastKey, meta.contrastValue, alarmMessage);
            case EXPRESS -> express(meta.scope, meta.contrastKey, meta.contrastValue, alarmMessage);
            case PASS -> true;
        });
    }

    /**
     * todo 比较目标字段的表达值
     *
     * @param scope
     * @param contrastKey
     * @param contrastValue
     * @param alarmMessage
     * @return
     */
    private boolean express(StrategyPredicateMeta.MessageScope scope, String contrastKey, String contrastValue,
                            AlarmMessage alarmMessage) {
        return false;
    }

    private boolean fixed(StrategyPredicateMeta.MessageScope scope, String contrastKey, String contrastValue,
                          AlarmMessage alarmMessage) {
        String actualValue = switch (scope) {
            case HEADERS -> alarmMessage.headers().get(contrastKey);
            // todo json
            case PAYLOAD -> "";
        };
        return Objects.equals(actualValue, contrastValue);
    }

    public AlarmStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(AlarmStrategy strategy) {
        this.strategy = strategy;
    }

    @Deprecated
    public String getName() {
        return name;
    }

    @Deprecated
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
