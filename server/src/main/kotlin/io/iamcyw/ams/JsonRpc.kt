package io.iamcyw.ams

data class JsonRpcRequest(
    val id: Int,
    val method: String,
    val params: List<String>,
    val jsonrpc: String = "2.0"
)

data class JsonRpcResponse(
    val id: Int,
    val result: String? = null,
    val error: String? = null,
    val exceptionType: String? = null,
    val jsonrpc: String = "2.0"
)