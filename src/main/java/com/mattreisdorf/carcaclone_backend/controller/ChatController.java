package com.mattreisdorf.carcaclone_backend.controller;

import java.security.Principal;

import com.mattreisdorf.carcaclone_backend.service.ProfanityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.mattreisdorf.carcaclone_backend.dto.chat_messages.ChatMessage;
import com.mattreisdorf.carcaclone_backend.dto.PlayerSessionResponse;
import com.mattreisdorf.carcaclone_backend.model.Lobby;
import com.mattreisdorf.carcaclone_backend.service.LobbyManager;
import com.mattreisdorf.carcaclone_backend.service.PlayerSessionService;

@Controller
public class ChatController {

  @Autowired
  private final ProfanityFilter profanityFilter;

  @Autowired
  private SimpMessagingTemplate messagingTemplate;

  @Autowired
  private LobbyManager lobbyManager;

  @Autowired
  private PlayerSessionService playerSessionService;

  ChatController(ProfanityFilter profanityFilter) {
    this.profanityFilter = profanityFilter;
  }

  @MessageMapping("/chat/{lobbyId}")
  public void handleChat(@DestinationVariable String lobbyId, ChatMessage message, Principal principal) {
    PlayerSessionResponse player = playerSessionService.getPlayerFromPrincipal(principal);
    ChatMessage outboundMessage = new ChatMessage();
    outboundMessage.setLobbyId(lobbyId);
    outboundMessage.setSenderId(player.playerId());
    outboundMessage.setSenderName(player.playerName());
    outboundMessage.setContent(profanityFilter.filter(message.getContent()));
    outboundMessage.setTimestamp(System.currentTimeMillis());

    Lobby lobby = lobbyManager.getLobby(lobbyId);
    if (lobby != null) {
      lobby.getPlayers().stream()
          .filter(existingPlayer -> existingPlayer.getPlayerId().equals(player.playerId()))
          .findFirst()
          .ifPresent(existingPlayer -> outboundMessage.setSenderColor(existingPlayer.getPlayerColor()));
    }

    messagingTemplate.convertAndSend("/topic/chat/" + lobbyId, outboundMessage);
  }
}
