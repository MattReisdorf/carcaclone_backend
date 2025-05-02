package com.mattreisdorf.carcaclone_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
  
  @Override
  public void configureMessageBroker(@SuppressWarnings("null") MessageBrokerRegistry config) {
    // Broker to route messages back to clients
    config.enableSimpleBroker("/topic");

    // Destination prefix for messages coming from client to controllers
    config.setApplicationDestinationPrefixes("/app");
  }


  @Override
  public void registerStompEndpoints(@SuppressWarnings("null") StompEndpointRegistry registry) {
    // Defines websocket endpoints that client will use
    registry.addEndpoint("/ws")
      .setAllowedOriginPatterns("*") // Allowing all endpoints for now -> TODO: Change to client url later
      .withSockJS(); // SockJS fallback
  }
}
