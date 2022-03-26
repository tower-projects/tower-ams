package io.iamcyw.ams.domain;

import java.util.Map;

public record AlarmMessage(Map<String, String> headers, String payload) {
}
