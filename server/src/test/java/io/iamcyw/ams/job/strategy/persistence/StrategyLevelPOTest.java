package io.iamcyw.ams.job.strategy.persistence;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestTransaction
public class StrategyLevelPOTest {

    @Test
    void findWithStrategyAndLevel() {
        StrategyLevelPO.findWithStrategyAndLevel(1, 1);
    }

}
