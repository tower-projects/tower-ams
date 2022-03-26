package io.iamcyw.ams;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.iamcyw.tower.commandhandling.gateway.CommandGateway;
import io.iamcyw.tower.quarkus.runtime.DomainNameMappings;
import io.iamcyw.tower.queryhandling.gateway.QueryGateway;
import io.quarkus.vertx.web.Route;
import io.quarkus.vertx.web.RoutingExchange;
import io.smallrye.common.annotation.Blocking;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ResourceManager {
    private final CommandGateway commandGateway;

    private final QueryGateway queryGateway;

    private final DomainNameMappings registry;

    private final ObjectMapper objectMapper;

    public ResourceManager(CommandGateway commandGateway, QueryGateway queryGateway, DomainNameMappings registry,
                           ObjectMapper objectMapper) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
        this.registry = registry;
        this.objectMapper = objectMapper;
    }

    @Route(path = "command/send")
    @Blocking
    public void send(RoutingExchange ex) {
        JsonRpcRequest request = ex.context().getBodyAsJson().mapTo(JsonRpcRequest.class);
        try {
            if (request.params().size() == 1) {
                commandGateway.send(codec(request.params().get(0), getDomain(request.method())));
            }
            ex.ok().end();
        } catch (Exception e) {
            ex.serverError().send(exception(e, request));
        }
    }

    @Route(path = "command/request")
    @Blocking
    public void request(RoutingExchange ex) {
        JsonRpcRequest request = ex.context().getBodyAsJson().mapTo(JsonRpcRequest.class);
        try {
            Object result = commandGateway.request(codec(request.params().get(0), getDomain(request.method())));
            ex.ok().end(success(result, request));
        } catch (Exception e) {
            ex.serverError().send(exception(e, request));
        }
    }

    @Route(path = "query/request")
    @Blocking
    public void query(RoutingExchange ex) {
        JsonRpcRequest request = ex.context().getBodyAsJson().mapTo(JsonRpcRequest.class);
        try {
            Object result = queryGateway.query(codec(request.params().get(0), getDomain(request.method())));
            ex.ok().end(success(result, request));
        } catch (Exception e) {
            ex.serverError().send(exception(e, request));
        }
    }

    @Route(path = "query/list")
    @Blocking
    public void queries(RoutingExchange ex) {
        JsonRpcRequest request = ex.context().getBodyAsJson().mapTo(JsonRpcRequest.class);
        try {
            Object result = queryGateway.queries(codec(request.params().get(0), getDomain(request.method())));
            ex.ok().end(success(result, request));
        } catch (Exception e) {
            ex.serverError().send(exception(e, request));
        }
    }

    public String success(Object payload, JsonRpcRequest jsonRpcRequest) {
        try {
            JsonRpcResponse jsonRpcResponse = new JsonRpcResponse(jsonRpcRequest.id(),
                                                                  objectMapper.writeValueAsString(payload));
            return objectMapper.writeValueAsString(jsonRpcResponse);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public String exception(Exception exception, JsonRpcRequest jsonRpcRequest) {
        try {
            JsonRpcResponse jsonRpcResponse = new JsonRpcResponse(jsonRpcRequest.id(), exception.getMessage(),
                                                                  exception.getClass().getCanonicalName());
            return objectMapper.writeValueAsString(jsonRpcResponse);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    private Class<?> getDomain(String simpleName) {
        return registry.domainMaps.keySet().stream().filter(key -> key.endsWith(simpleName)).findFirst()
                                  .map(key -> registry.domainMaps.get(key))
                                  .orElseThrow(() -> new IllegalArgumentException("domain not found handler"));
    }


    private <T> Object codec(String msg, Class<T> clazz) {
        try {
            return objectMapper.readValue(msg, clazz);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public record JsonRpcRequest(int id, String method, List<String> params, String jsonrpc) {
        public JsonRpcRequest(int id, String method, List<String> params) {
            this(id, method, params, "2.0");
        }

    }

    public record JsonRpcResponse(int id, String result, String error, String exceptionType, String jsonrpc) {
        public JsonRpcResponse(int id, String result) {
            this(id, result, null, null, "2.0");
        }

        public JsonRpcResponse(int id, String error, String exceptionType) {
            this(id, null, error, exceptionType, "2.0");
        }

    }

}


