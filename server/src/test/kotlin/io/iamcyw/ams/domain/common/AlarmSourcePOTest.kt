package io.iamcyw.ams.domain.common

import io.iamcyw.ams.job.strategy.persistence.AlarmSourcePO
import io.quarkus.test.TestTransaction
import io.quarkus.test.junit.QuarkusTest
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test

@QuarkusTest
@TestTransaction
class AlarmSourcePOTest {

    @Test
    fun testDb() {
        val alarmSourcePO = AlarmSourcePO()
        alarmSourcePO.name = "source-name"
        alarmSourcePO.persist()

        val alarmSourcePO1 = AlarmSourcePO.findByName("source-name")
        println(alarmSourcePO1.id)
        println(alarmSourcePO1.name)

        val alarmSourcePO2 = AlarmSourcePO()
        alarmSourcePO2.name = "source-name2"
        alarmSourcePO2.persist()

        println(Json.encodeToString(alarmSourcePO2))
    }

    @Test
    fun testSer() {
//        println(Json.encodeToString(AlarmSourcePO(1L, "name")))
    }
}