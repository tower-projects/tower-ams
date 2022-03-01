package io.iamcyw.ams.job.strategy.persistence;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * alarm事件策略
 */
@Entity(name = "ALARM_STRATEGY")
public class AlarmStrategyPO extends PanacheEntity {

    public String name;

    @Column(name = "source")
    @ManyToOne(fetch = FetchType.LAZY)
    public AlarmSourcePO source;

    @Column(name = "alarm_policies")
    @ManyToMany(targetEntity = StrategyPolicyPO.class, fetch = FetchType.LAZY)
    @JoinTable(name = "ALARM_STRATEGY_POLICY_LINK",
            inverseJoinColumns = {@JoinColumn(name = "POLICY_ID", referencedColumnName = "ID")},
            joinColumns = {@JoinColumn(name = "STRATEGY_ID", referencedColumnName = "ID")})
    public List<StrategyPolicyPO> alarmPolicies;

    @Column(name = "level")
    @OneToMany(fetch = FetchType.LAZY)
    public Set<StrategyLevelPO> level;

    /**
     * 单位:分钟
     * <p>
     * 如果具体级别的间隔时间没有设置才使用该值
     */
    public Long levelTimeInterval = 0L;

    /**
     * 单位:秒
     * <p>
     * 默认警报可重复
     * <p>
     * 指定距离上次警报间隔多长才能再次发送
     */
    public Long repeatTimeInterval = 0L;

    @OneToMany(fetch = FetchType.LAZY)
    public Set<StrategyPushPO> push;

    @ManyToOne(fetch = FetchType.LAZY)
    public StrategySolvePO solve;


}
