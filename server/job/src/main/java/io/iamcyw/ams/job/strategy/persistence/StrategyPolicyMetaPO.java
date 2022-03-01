package io.iamcyw.ams.job.strategy.persistence;

import javax.persistence.*;

@Entity(name = "ALARM_STRATEGY_POLICY_META")
public class StrategyPolicyMetaPO {
    @Id
    @GeneratedValue
    public Long id;

    public MessageScope scope;

    public String contrastKey;

    public String contrastValue;

    public PolicyContrastType contrastType;

    @ManyToOne(fetch = FetchType.LAZY)
    public StrategyPolicyPO policy;

    public enum MessageScope {
        Headers, Payload
    }

    public enum PolicyContrastType {
        Fixed, Express, Pass
    }

}





