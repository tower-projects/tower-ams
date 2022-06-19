package io.iamcyw.ams.job.strategy.persistence;

import io.iamcyw.ams.domain.AlarmMessage;
import io.iamcyw.ams.domain.job.receive.usecase.ReceiveAlarm;
import io.iamcyw.tower.messaging.gateway.MessageGateway;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Map;

@QuarkusTest
@TestTransaction
class StrategyLevelTest {

    @Inject
    MessageGateway messageGateway;

    @Test
    void testReceiveAlarm() {
        messageGateway.send(new ReceiveAlarm("source", new AlarmMessage(Map.of(), "")));
    }

}
