package io.iamcyw.ams.job;

import org.jboss.logging.Messages;
import org.jboss.logging.annotations.Message;
import org.jboss.logging.annotations.MessageBundle;

@MessageBundle(projectCode = "AMS-JOB-")
public interface AlarmJobServerMessages {

    AlarmJobServerMessages msg = Messages.getBundle(AlarmJobServerMessages.class);

    // Source
    @Message(id = 1, value = "source [%s] is not exist")
    IllegalStateException sourceNotExist(long source);

    @Message(id = 2, value = "source name[%s] is not exist")
    IllegalStateException sourceNameNotExist(String source);

    // Strategy
    @Message(id = 3,value = "source name[%s] is exist")
    IllegalStateException sourceNameExist(String source);


}
