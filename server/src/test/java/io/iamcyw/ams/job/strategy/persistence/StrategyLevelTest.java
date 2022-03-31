package io.iamcyw.ams.job.strategy.persistence;

import io.iamcyw.ams.job.strategy.repository.StrategyLevelRepository;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@QuarkusTest
@TestTransaction
public class StrategyLevelTest {

    @Inject
    StrategyLevelRepository strategyLevelRepository;

    @Test
    void findWithStrategyAndLevel() {
        strategyLevelRepository.findWithStrategyAndLevel(1, 1);
    }

}
