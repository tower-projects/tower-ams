package io.iamcyw.ams;

import io.iamcyw.tower.StringPool;
import io.iamcyw.tower.messaging.gateway.MessageGateway;
import io.iamcyw.tower.schema.model.OperationType;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import org.apache.commons.lang3.ObjectUtils;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("/message")
public class ResourceEndpoint {
    private final MessageGateway messageGateway;

    public ResourceEndpoint(MessageGateway messageGateway) {
        this.messageGateway = messageGateway;
    }

    @ServerExceptionMapper
    public RestResponse<Map<String, String>> mapException(Throwable throwable) {
        if (ObjectUtils.isNotEmpty(throwable.getMessage())) {
            String[] messages = throwable.getMessage().split(StringPool.COLON, 2);
            if (messages.length == 2) {
                String msgId = messages[0];
                if (msgId != null) {
                    return RestResponse.status(RestResponse.Status.EXPECTATION_FAILED,
                                               Map.of("errorCode", msgId, "error", messages[1]));
                }
            }
        }
        return RestResponse.status(RestResponse.Status.EXPECTATION_FAILED,
                                   Map.of("errorCode", "UNKNOWN-ERROR", "error", throwable.getMessage()));
    }

    @GET
    @Path("/{command}/query")
    @Blocking
    public Uni<Response> query(String command, String payload) {
        return Uni.createFrom().completionStage(messageGateway.advance(payload, command, OperationType.QUERY));
    }

    @GET
    @Path("/{command}/queries")
    @Blocking
    public Uni<Object> queries(String command, String payload) {
        return Uni.createFrom().completionStage(messageGateway.advance(payload, command, OperationType.QUERY));
    }

    @POST
    @Path("/{command}")
    public Uni<Void> post(String command, String payload) {
        return Uni.createFrom().completionStage(messageGateway.advance(payload, command, OperationType.COMMAND));
    }

}
