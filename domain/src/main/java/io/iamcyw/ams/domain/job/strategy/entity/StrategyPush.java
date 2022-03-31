package io.iamcyw.ams.domain.job.strategy.entity;

import io.iamcyw.ams.domain.BasicEntity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "ALARM_STRATEGY_PUSH")
public class StrategyPush extends BasicEntity {

    private String subject;

    private String type;

    private String template;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<StrategyPushMeta> meta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STRATEGY_ID")
    private AlarmStrategy strategy;


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Set<StrategyPushMeta> getMeta() {
        return meta;
    }

    public void setMeta(Set<StrategyPushMeta> meta) {
        this.meta = meta;
    }

    public AlarmStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(AlarmStrategy strategy) {
        this.strategy = strategy;
    }

}
