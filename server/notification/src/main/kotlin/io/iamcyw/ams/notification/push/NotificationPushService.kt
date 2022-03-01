package io.iamcyw.ams.notification.push

import io.iamcyw.ams.job.strategy.entity.StrategyLevelPO
import io.iamcyw.ams.notification.cache.NotificationHandler
import io.iamcyw.ams.notification.cache.PushAlarm
import io.iamcyw.ams.notification.cache.RetryAlarm
import io.iamcyw.ams.notification.cache.entity.AlarmPO
import io.iamcyw.ams.notification.cache.entity.AlarmStatus
import io.iamcyw.tower.commandhandling.CommandHandle
import io.iamcyw.tower.commandhandling.gateway.CommandGateway
import io.iamcyw.tower.messaging.predicate.PredicateHandle
import io.quarkus.vertx.ConsumeEvent
import java.time.LocalDateTime
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.transaction.Transactional

@ApplicationScoped
@Transactional
class NotificationPushService @Inject constructor(private val commandGateway: CommandGateway) {

    @ConsumeEvent(
        "io.iamcyw.ams.notification.cache.PushAlarm",
        blocking = true
    )
    @CommandHandle
    fun command(pushAlarm: PushAlarm) {
        val alarmPO = AlarmPO.findById(pushAlarm.alarm)
        alarmPO?.run {
            val currentLevel =
                StrategyLevelPO.findById(currentLevel) ?: strategy.defaultLevel()

            //向警报的策略下的所有push发起推送
            for (push in strategy.push) {
                commandGateway.send(
                    NotificationHandler(
                        push.type,
                        push.id!!,
                        id!!,
                        currentLevel.id!!,
                    )
                )
            }
            //发送之后改变当前警报的状态
            status = AlarmStatus.Sent

            //todo 可以间隔循环发送？

            //检查有没有下一层需要升级
            strategy.level(currentLevel.level + 1)?.let {
                // 如果level中的timeInterval为0则使用strategy中的timeInterval
                val interval = if (it.timeInterval != 0L) {
                    it.timeInterval
                } else {
                    strategy.levelTimeInterval
                }
                checkLevelTime = LocalDateTime.now().plusMinutes(interval)
            }

            persist()
        }
    }

    @CommandHandle
    fun command(retryAlarm: RetryAlarm) {
        //todo 重试


    }

    @PredicateHandle("noticeType")
    fun predicate(notificationHandler: NotificationHandler, type: String) =
        notificationHandler.pushType == type
}