package io.iamcyw.ams.job.receive

import io.iamcyw.ams.domain.job.predicate.QueryAllowStrategy
import io.iamcyw.ams.domain.job.receive.ReceiveAlarm
import io.iamcyw.ams.domain.job.strategy.AlarmSourcePO
import io.iamcyw.ams.domain.notification.cache.AddAlarm
import io.iamcyw.tower.commandhandling.CommandHandle
import io.iamcyw.tower.commandhandling.gateway.CommandGateway
import io.iamcyw.tower.queryhandling.gateway.QueryGateway
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.transaction.Transactional

@ApplicationScoped
@Transactional
class JobReceiveService @Inject constructor(
    private val commandGateway: CommandGateway,
    private val queryGateway: QueryGateway
) {

    @CommandHandle
    fun receive(receiveAlarm: ReceiveAlarm) {
        val alarmSourcePO = AlarmSourcePO.findByName(receiveAlarm.source)
        val strategies = queryGateway.query<List<Long>>(QueryAllowStrategy(alarmSourcePO.id!!, receiveAlarm.message))
        strategies.map {
            AddAlarm(it, alarmSourcePO.id!!, receiveAlarm.message)
        }.forEach {
            commandGateway.send(it)
        }
    }
}