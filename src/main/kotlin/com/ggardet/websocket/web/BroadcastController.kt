package com.ggardet.websocket.web

import com.ggardet.websocket.model.Message
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping


@Controller
class BroadcastController {
    @GetMapping("/stomp-broadcast")
    fun getWebsocketBroadcast() = "index"

    @MessageMapping("/broadcast")
    @SendTo("/topic/messages")
    fun send(message: Message): Message = Message(message.from, message.text, "ALL")
}
