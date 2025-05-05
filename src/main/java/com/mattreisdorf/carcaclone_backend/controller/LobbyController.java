package com.mattreisdorf.carcaclone_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.mattreisdorf.carcaclone_backend.dto.lobby_messages.CreateLobbyMessage;
import com.mattreisdorf.carcaclone_backend.model.Lobby;
import com.mattreisdorf.carcaclone_backend.service.LobbyManager;

@Controller
public class LobbyController {

  @Autowired
  private LobbyManager lobbyManager;
  
  @MessageMapping("/createLobby")
  @SendTo("/topic/lobby")
  public Lobby createLobby(CreateLobbyMessage message) {
    Lobby lobby = lobbyManager.createLobby(message.getPlayerId(), message.getPlayerName());
    return lobby;
  }
}
