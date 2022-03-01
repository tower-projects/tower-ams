package io.iamcyw.ams.job.strategy.persistence;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "ALARM_STRATEGY_PUSH")
public class StrategyPushPO extends PanacheEntity {

    public String subject;

    public String type;

    public String template;

    @OneToMany(fetch = FetchType.LAZY)
    public Set<StrategyPushMetaPO> metaPO;


}
