package io.iamcyw.ams.domain.job.strategy.entity;

import io.iamcyw.ams.domain.BasicEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * alarm事件策略
 */
@Entity(name = "ALARM_STRATEGY")
public class AlarmStrategy extends BasicEntity {

    private String name;

    private String comments;

    @ManyToOne(fetch = FetchType.LAZY)
    private AlarmSource source;

    @Column(name = "alarm_policies")
    @ManyToMany(targetEntity = StrategyPredicate.class, fetch = FetchType.LAZY)
    @JoinTable(name = "ALARM_STRATEGY_POLICY_LINK",
               inverseJoinColumns = {@JoinColumn(name = "POLICY_ID", referencedColumnName = "ID")},
               joinColumns = {@JoinColumn(name = "STRATEGY_ID", referencedColumnName = "ID")})
    private List<StrategyPredicate> alarmPolicies;

    @Column(name = "level")
    @JoinColumn(name = "STRATEGY_ID", nullable = false)
    @OneToMany(fetch = FetchType.LAZY)
    private Set<StrategyLevel> level;

    /**
     * 单位:分钟
     * <p>
     * 如果具体级别的间隔时间没有设置才使用该值
     */
    private Long levelTimeInterval = 0L;

    /**
     * 单位:秒
     * <p>
     * 默认警报可重复
     * <p>
     * 指定距离上次警报间隔多长才能再次发送
     */
    private Long repeatTimeInterval = 0L;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "STRATEGY_ID")
    private Set<StrategyPush> push;

    @ManyToOne(fetch = FetchType.LAZY)
    private StrategySolve solve;

    public static AlarmStrategy createStrategy(long source, String name, String comments) {
        AlarmSource alarmSource = new AlarmSource();
        alarmSource.setId(source);

        AlarmStrategy alarmStrategy = new AlarmStrategy();
        alarmStrategy.source = alarmSource;
        alarmStrategy.name = name;
        alarmStrategy.comments = comments;

        return alarmStrategy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AlarmSource getSource() {
        return source;
    }

    public void setSource(AlarmSource source) {
        this.source = source;
    }

    public List<StrategyPredicate> getAlarmPolicies() {
        return alarmPolicies;
    }

    public void setAlarmPolicies(List<StrategyPredicate> alarmPolicies) {
        this.alarmPolicies = alarmPolicies;
    }

    public Set<StrategyLevel> getLevel() {
        return level;
    }

    public void setLevel(Set<StrategyLevel> level) {
        this.level = level;
    }

    public Long getLevelTimeInterval() {
        return levelTimeInterval;
    }

    public void setLevelTimeInterval(Long levelTimeInterval) {
        this.levelTimeInterval = levelTimeInterval;
    }

    public Long getRepeatTimeInterval() {
        return repeatTimeInterval;
    }

    public void setRepeatTimeInterval(Long repeatTimeInterval) {
        this.repeatTimeInterval = repeatTimeInterval;
    }

    public Set<StrategyPush> getPush() {
        return push;
    }

    public void setPush(Set<StrategyPush> push) {
        this.push = push;
    }

    public StrategySolve getSolve() {
        return solve;
    }

    public void setSolve(StrategySolve solve) {
        this.solve = solve;
    }

}
