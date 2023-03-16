package com.ggardet.websocket.core

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfiguration: WebSocketMessageBrokerConfigurer {
    override fun configureMessageBroker(messageBrokerRegistry: MessageBrokerRegistry) {
        messageBrokerRegistry
            .setApplicationDestinationPrefixes("/app")
            .enableSimpleBroker("/topic")
    }

    override fun registerStompEndpoints(stompEndpointRegistry: StompEndpointRegistry) {
        stompEndpointRegistry
            .addEndpoint("/broadcast")
            .setAllowedOriginPatterns("*")
    }
}
