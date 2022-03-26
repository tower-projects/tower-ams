package io.iamcyw.ams.notification.cache.persistence;

import io.quarkus.panache.common.Page;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestTransaction
public class AlarmPOTest {

    @Test
    void testFindWaitImmediateAlarm() {
        AlarmPO.findWaitImmediateAlarm().page(Page.ofSize(50)).list();
    }

}
