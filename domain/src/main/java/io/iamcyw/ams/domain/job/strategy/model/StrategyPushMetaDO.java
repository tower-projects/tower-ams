package io.iamcyw.ams.domain.job.strategy.model;

import java.util.Map;

public record StrategyPushMetaDO(String type, Map<String, String> meta) {
}
