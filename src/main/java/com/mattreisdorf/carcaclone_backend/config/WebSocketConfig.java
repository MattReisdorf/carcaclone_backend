package com.mattreisdorf.carcaclone_backend.config;

import java.security.Principal;
import java.util.Map;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  @Override
  public void configureMessageBroker(@SuppressWarnings("null") MessageBrokerRegistry registry) {
    registry.setUserDestinationPrefix("/user");
    registry.enableSimpleBroker("/topic", "/queue");
    registry.setApplicationDestinationPrefixes("/app");
  }

  @Override
  public void registerStompEndpoints(@SuppressWarnings("null") StompEndpointRegistry registry) {
    registry
        .addEndpoint("/ws")
        .setAllowedOrigins("http://localhost:5173") // or use allowedOriginPatterns
        .setHandshakeHandler(new DefaultHandshakeHandler() {
          @Override
          protected Principal determineUser(@SuppressWarnings("null") ServerHttpRequest request,
              @SuppressWarnings("null") WebSocketHandler wsHandler,
              @SuppressWarnings("null") Map<String, Object> attributes) {
            if (request instanceof ServletServerHttpRequest servletReq) {
              String playerId = servletReq.getServletRequest().getParameter("playerId");
              if (playerId != null && !playerId.isBlank()) {
                return new StompPrincipal(playerId);
              }
            }
            return super.determineUser(request, wsHandler, attributes);
          }
        })
        .withSockJS();
  }
}
