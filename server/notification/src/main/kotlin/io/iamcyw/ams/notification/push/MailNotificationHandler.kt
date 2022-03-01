package io.iamcyw.ams.notification.push

import io.iamcyw.ams.job.receive.toKV
import io.iamcyw.ams.job.strategy.entity.StrategyPushPO
import io.iamcyw.ams.notification.cache.NotificationHandler
import io.iamcyw.ams.notification.cache.entity.AlarmPO
import io.iamcyw.ams.notification.cache.entity.toKV
import io.iamcyw.ams.user.AddressPO
import io.iamcyw.ams.user.emailStr
import io.iamcyw.tower.commandhandling.CommandHandle
import io.iamcyw.tower.messaging.predicate.MessagePredicate
import io.quarkus.qute.Engine
import org.apache.camel.EndpointInject
import org.apache.camel.ProducerTemplate
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class MailNotificationHandler(
    @EndpointInject("notice.push.mail") val producerTemplate: ProducerTemplate,
    val engine: Engine
) {


    @MessagePredicate("noticeType", ["mail"])
    @CommandHandle
    fun command(notificationHandler: NotificationHandler) {
        val pushPO = StrategyPushPO.findById(notificationHandler.push)
            ?: throw IllegalStateException("the push(${notificationHandler.push}) no found")

        val alarmPO = AlarmPO.findById(notificationHandler.alarm)
            ?: throw IllegalStateException("the alarm(${notificationHandler.alarm}) no found")

        val msgHeaders = alarmPO.alarmMeta.toKV()
        val msgPayload = alarmPO.payloadJson().toKV()

        //整合payload和template得到最终的正文
        val template = engine.parse(pushPO.template).data("params", msgHeaders, "payload", msgPayload)
        val content = template.render()

        //todo 在StrategyLevel中找到所有可用的address
        val address = listOf<AddressPO>()

        //整合payload和subject得到最终的主题
        val subject = engine.parse(pushPO.subject).data("params", msgHeaders, "payload", msgPayload)

        val headers = mutableMapOf<String, Any>()
        headers["to"] = address.emailStr()
        headers["Subject"] = subject

        if (pushPO.metaPO.isNullOrEmpty()) {
            pushPO.metaPO.forEach {
                headers[it.metaKey] = it.metaValue
            }
        }

        producerTemplate.sendBodyAndHeaders(content, headers)

    }


}