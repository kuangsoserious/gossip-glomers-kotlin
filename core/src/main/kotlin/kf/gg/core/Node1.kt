package kf.gg.core

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.*
import java.util.*

abstract class Node1<T> {
    abstract fun parseRequest(req: Request): T
    abstract fun handleMessage(msg: T): JsonElement

    fun start() {
        val scanner = Scanner(System.`in`)
        while (true) {
            try {
                val line = scanner.nextLine()
                val req = PARSER.decodeFromString<Request>(line)

                val reqType = req.body.jsonObject["type"]?.jsonPrimitive?.content
                val resp = when (reqType) {
                    "init" -> init(req)
                    else -> handleMessage(parseRequest(req))
                }

                reply(resp)
            } catch (e: Throwable) {
                System.err.println("Node1 error: $e")
            }
        }
    }

    private fun init(req: Request): JsonElement {
        val msg = InitRequest.fromRequest(req)

        val resp = buildJsonObject {
            put("src", req.dest)
            put("dest", req.src)
            putJsonObject("body") {
                put("type", "init_ok")
                put("in_reply_to", msg.body.msgId)
            }
        }

        return resp
    }

    private fun reply(resp: JsonElement) {
        System.err.println("reply: $resp")
        System.out.println(PARSER.encodeToString(resp))
    }

    companion object {
        @OptIn(ExperimentalSerializationApi::class)
        val PARSER = Json { explicitNulls = false }
    }
}