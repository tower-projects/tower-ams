package io.iamcyw.ams.notification.push;

import io.iamcyw.ams.domain.AlarmMessage;
import io.iamcyw.ams.domain.job.strategy.model.EmailPushMetaDO;
import io.iamcyw.ams.domain.job.strategy.model.StrategyPushDO;
import io.iamcyw.ams.domain.notification.cache.usecase.NotificationHandler;
import io.iamcyw.tower.messaging.CommandHandle;
import io.iamcyw.tower.messaging.Parameter;
import io.iamcyw.tower.messaging.UseCase;
import io.quarkus.qute.Engine;


@UseCase
public class MailNotificationHandler {

    private final Engine engine;

    public MailNotificationHandler(Engine engine) {
        this.engine = engine;
    }

    @Parameter(value = "noticeType", parameter = EmailPushMetaDO.TYPE)
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
