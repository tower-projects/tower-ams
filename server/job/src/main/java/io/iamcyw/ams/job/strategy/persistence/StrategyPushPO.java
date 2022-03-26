package io.iamcyw.ams.job.strategy.persistence;

import io.iamcyw.ams.domain.job.strategy.model.StrategyPushDO;
import io.iamcyw.ams.domain.job.strategy.model.StrategyPushMetaDO;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Entity(name = "ALARM_STRATEGY_PUSH")
public class StrategyPushPO extends PanacheEntity {

    public String subject;

    public String type;

    public String template;

    @OneToMany(fetch = FetchType.LAZY)
    public Set<StrategyPushMetaPO> meta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STRATEGY_ID")
    public AlarmStrategyPO strategy;

    public StrategyPushDO as() {
        return new StrategyPushDO(id, subject, template, asMeta());
    }

    public StrategyPushMetaDO asMeta() {
        Map<String, String> metas = meta.stream().collect(
                Collectors.toMap(metaPO -> metaPO.metaKey, metaPO -> metaPO.metaValue));
        return new StrategyPushMetaDO(type, metas);
    }

}
