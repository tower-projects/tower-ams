package io.iamcyw.ams;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.iamcyw.tower.messaging.Message;
import io.iamcyw.tower.messaging.handle.interceptor.InterceptorChain;
import io.iamcyw.tower.messaging.handle.interceptor.MessageInterceptor;
import io.iamcyw.tower.messaging.spi.ClassloadingService;
import io.iamcyw.tower.schema.model.InputType;
import io.iamcyw.tower.schema.model.Schema;

import javax.enterprise.context.ApplicationScoped;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@ApplicationScoped
public class PayloadSerialFilter implements MessageInterceptor {

    private final Map<String, InputType> inputTypeMap;

    private final ClassloadingService classloadingService = ClassloadingService.load();

    private final ObjectMapper objectMapper;

    public PayloadSerialFilter(Schema schema, ObjectMapper objectMapper) {
        inputTypeMap = schema.getInputs();
        this.objectMapper = objectMapper;
    }

    @Override
    public <R> CompletableFuture<R> filter(Message<R> message, InterceptorChain chain) {
        if (message.getPayload() instanceof String) {
            String command = message.getIdentifier().getCommand();
            InputType inputType = inputTypeMap.get(command);

            if (inputType == null) {
                throw AmsServerMessages.msg.unknownCommand(command);
            }

            try {
                Object commandObj = objectMapper.readValue(String.valueOf(message.getPayload()),
                                                           classloadingService.loadClass(inputType.getClassName()));

                return chain.filter(message.updatePayload(commandObj));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

        }
        return chain.filter(message);
    }

}
