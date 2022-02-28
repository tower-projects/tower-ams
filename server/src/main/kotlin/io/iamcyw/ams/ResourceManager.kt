package io.iamcyw.ams

import io.iamcyw.tower.commandhandling.gateway.CommandGateway
import io.iamcyw.tower.quarkus.runtime.DomainNameMappings
import io.iamcyw.tower.queryhandling.gateway.QueryGateway
import io.quarkus.vertx.web.Route
import io.quarkus.vertx.web.RoutingExchange
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import javax.enterprise.context.RequestScoped
import javax.inject.Inject

@RequestScoped
class ResourceManager @Inject constructor(
    private val commandGateway: CommandGateway,
    private val queryGateway: QueryGateway,
    private val registry: DomainNameMappings
) {

    @Route(path = "command/send")
    fun send(ex: RoutingExchange) {
        val request = ex.context().bodyAsJson.mapTo(JsonRpcRequest::class.java)
        try {
            val payload = code(request.params[0], registry.getDomain(request.method))
            commandGateway.send(payload)
            ex.ok().end()
        } catch (e: Exception) {
            ex.serverError().send(exception(e, request))
        }
    }

    @Route(path = "command/request")
    fun request(ex: RoutingExchange) {
        val request = ex.context().bodyAsJson.mapTo(JsonRpcRequest::class.java)
        try {
            val payload = code(request.params[0], registry.getDomain(request.method))
            val result = commandGateway.request<Any>(payload)
            ex.ok().send(success(result, request))
        } catch (e: Exception) {
            ex.serverError().send(exception(e, request))
        }
    }

    @Route(path = "query/*")
    fun query(ex: RoutingExchange) {
        val request = ex.context().bodyAsJson.mapTo(JsonRpcRequest::class.java)
        try {
            val payload = code(request.params[0], registry.getDomain(request.method))
            val result = queryGateway.query<Any>(payload)
            ex.ok().send(success(result, request))
        } catch (e: Exception) {
            ex.serverError().send(exception(e, request))
        }
    }

    private fun exception(ex: Exception, request: JsonRpcRequest): String {
        val response = JsonRpcResponse(request.id, exceptionType = ex::class.simpleName, error = ex.message)
        return Json.encodeToString(response)
    }

    private fun success(payload: Any?, request: JsonRpcRequest): String {
        val response = JsonRpcResponse(request.id, Json.encodeToString(payload))
        return Json.encodeToString(response)
    }

    @OptIn(InternalSerializationApi::class)
    fun <T : Any> code(msg: String, clazz: Class<T>) = Json.decodeFromString(clazz.kotlin.serializer(), msg)
}