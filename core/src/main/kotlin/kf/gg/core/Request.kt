package kf.gg.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*

@Serializable
data class Request(val id: Int, val src: String, val dest: String, val body: JsonElement)


////////////////////////////////////////////////////////////
// init
////////////////////////////////////////////////////////////

@Serializable
data class InitRequest(val src: String, val dest: String, val body: InitBody) {
    companion object {
        fun fromRequest(req: Request): InitRequest {
            val type = req.body.jsonObject["type"]?.jsonPrimitive?.content
            val msgId = req.body.jsonObject["msg_id"]?.jsonPrimitive?.int
            val nodeId = req.body.jsonObject["node_id"]?.jsonPrimitive?.content
            val nodeIds = req.body.jsonObject["node_ids"]?.jsonArray?.map { it.jsonPrimitive.content }

            return InitRequest(
                src = req.src,
                dest = req.dest,
                body = InitBody(
                    type = type!!,
                    msgId = msgId!!,
                    nodeId = nodeId!!,
                    nodeIds = nodeIds!!
                )
            )
        }
    }
}

@Serializable
data class InitBody(
    val type: String,
    @SerialName("msg_id") val msgId: Int,
    @SerialName("node_id") val nodeId: String,
    @SerialName("node_ids") val nodeIds: List<String>,
)

////////////////////////////////////////////////////////////
// echo
////////////////////////////////////////////////////////////

@Serializable
data class EchoRequest(val src: String, val dest: String, val body: EchoBody) {
    companion object {
        fun fromRequest(req: Request): EchoRequest {
            val type = req.body.jsonObject["type"]?.jsonPrimitive?.content
            val msgId = req.body.jsonObject["msg_id"]?.jsonPrimitive?.int
            val echo = req.body.jsonObject["echo"]?.jsonPrimitive?.content

            return EchoRequest(
                src = req.src,
                dest = req.dest,
                body = EchoBody(
                    type = type!!,
                    msgId = msgId!!,
                    echo = echo!!
                )
            )
        }
    }
}

@Serializable
data class EchoBody(
    val type: String,
    @SerialName("msg_id") val msgId: Int,
    val echo: String,
)