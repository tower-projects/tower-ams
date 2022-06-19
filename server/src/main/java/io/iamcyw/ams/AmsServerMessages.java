package io.iamcyw.ams;

import org.jboss.logging.Messages;
import org.jboss.logging.annotations.Message;
import org.jboss.logging.annotations.MessageBundle;

@MessageBundle(projectCode = "AMS-SERVER-")
public interface AmsServerMessages {

    AmsServerMessages msg = Messages.getBundle(AmsServerMessages.class);

    @Message(id = 1, value = "Unknown command [%s]")
    IllegalArgumentException unknownCommand(String command);

}
