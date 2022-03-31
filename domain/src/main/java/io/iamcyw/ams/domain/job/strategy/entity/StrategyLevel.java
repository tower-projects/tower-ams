package io.iamcyw.ams.domain.job.strategy.entity;

import io.iamcyw.ams.domain.BasicEntity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "ALARM_STRATEGY_LEVEL")
public class StrategyLevel extends BasicEntity {
    /**
     * 当前级别
     * <p>
     * 最低0
     */
    @Column(name = "LEVEL")
    private int level = 0;

    /**
     * 单位:分钟
     */
    private long timeInterval = 0;

    private String name;

    @JoinColumn(name = "STRATEGY_ID", nullable = false)
    @ManyToOne
    private AlarmStrategy strategy;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<StrategyLevelTarget> target;


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public long getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(long timeInterval) {
        this.timeInterval = timeInterval;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AlarmStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(AlarmStrategy strategy) {
        this.strategy = strategy;
    }

    public Set<StrategyLevelTarget> getTarget() {
        return target;
    }

    public void setTarget(Set<StrategyLevelTarget> target) {
        this.target = target;
    }

}
