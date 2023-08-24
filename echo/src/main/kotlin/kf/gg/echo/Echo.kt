package kf.gg.echo


import kf.gg.core.EchoBody
import kf.gg.core.EchoRequest
import kf.gg.core.Node1
import kf.gg.core.Request
import kotlinx.serialization.json.*

class Echo() : Node1<EchoRequest>() {
    override fun parseRequest(req: Request): EchoRequest {
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

    override fun handleMessage(msg: EchoRequest): JsonElement =
        buildJsonObject {
            put("src", msg.dest)
            put("dest", msg.src)
            putJsonObject("body") {
                put("type", "echo_ok")
                put("msg_id", msg.body.msgId)
                put("in_reply_to", msg.body.msgId)
                put("echo", msg.body.echo)
            }
        }
}

fun main() {
    Echo().start()
}