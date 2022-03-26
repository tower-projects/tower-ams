package io.iamcyw.ams.domain.job.strategy.model;

import java.util.HashMap;
import java.util.Objects;

public class EmailPushMetaDO {
    public final static String type = "mail";

    private final StrategyPushMetaDO strategyPushMetaDO;

    public EmailPushMetaDO() {
        strategyPushMetaDO = new StrategyPushMetaDO(type, new HashMap<>(10));
    }

    public EmailPushMetaDO(StrategyPushMetaDO strategyPushMetaDO) {
        if (Objects.equals(strategyPushMetaDO.type(), type)) {
            this.strategyPushMetaDO = strategyPushMetaDO;
        } else {
            throw new IllegalArgumentException("StrategyPushMeta type not match");
        }
    }

    public String getAddress() {
        return strategyPushMetaDO.meta().get("address");
    }

    public void setAddress(String address) {
        strategyPushMetaDO.meta().put("address", address);
    }

}
