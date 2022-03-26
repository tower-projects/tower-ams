package io.iamcyw.ams.job.strategy.persistence;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.Optional;
import java.util.Set;

@Entity(name = "ALARM_STRATEGY_LEVEL")
public class StrategyLevelPO extends PanacheEntity {

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
    private AlarmStrategyPO strategy;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<StrategyLevelTargetPO> target;

    public static Optional<StrategyLevelPO> findWithStrategyAndLevel(int level, long strategy) {
        return find("LEVEL = ?1 and STRATEGY_ID = ?2", level, strategy).singleResultOptional();
    }


}
