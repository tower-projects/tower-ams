package io.iamcyw.ams.notification.push;

import io.iamcyw.ams.domain.AlarmMessage;
import io.iamcyw.ams.domain.job.strategy.model.EmailPushMetaDO;
import io.iamcyw.ams.domain.job.strategy.model.StrategyPushDO;
import io.iamcyw.ams.domain.notification.cache.usecase.NotificationHandler;
import io.iamcyw.tower.commandhandling.CommandHandle;
import io.iamcyw.tower.messaging.predicate.MessagePredicate;
import io.quarkus.qute.Engine;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MailNotificationHandler {

    private final Engine engine;

    public MailNotificationHandler(Engine engine) {
        this.engine = engine;
    }

    @MessagePredicate(value = "noticeType", parameter = EmailPushMetaDO.type)
    @CommandHandle
    public void handle(NotificationHandler handler) {
        StrategyPushDO push = handler.push();
        AlarmMessage message = handler.payload();

        String content = engine.parse(push.template()).data("params", message.headers(), "payload", message.payload())
                               .render();

        String subject = engine.parse(push.subject()).data("params", message.headers(), "payload", message.payload())
                               .render();

        EmailPushMetaDO emailPushMetaDO = new EmailPushMetaDO(push.meta());
        //todo

    }

}
