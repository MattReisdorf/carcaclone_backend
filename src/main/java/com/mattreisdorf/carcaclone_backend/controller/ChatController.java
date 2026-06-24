package com.mattreisdorf.carcaclone_backend.controller;

import com.mattreisdorf.carcaclone_backend.service.ProfanityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.mattreisdorf.carcaclone_backend.dto.chat_messages.ChatMessage;

@Controller
public class ChatController {

  @Autowired
  private final ProfanityFilter profanityFilter;

  @Autowired
  private SimpMessagingTemplate messagingTemplate;

  ChatController(ProfanityFilter profanityFilter) {
    this.profanityFilter = profanityFilter;
  }

  @MessageMapping("/chat/{lobbyId}")
  public void handleChat(@DestinationVariable String lobbyId, ChatMessage message) {
    message.setContent(profanityFilter.filter(message.getContent()));
    messagingTemplate.convertAndSend("/topic/chat/" + lobbyId, message);

    // messagingTemplate.convertAndSend(
    // "/topic/chat/" + lobbyId,
    // message
    // );
  }
}
