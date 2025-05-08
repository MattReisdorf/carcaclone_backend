package com.mattreisdorf.carcaclone_backend.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.mattreisdorf.carcaclone_backend.dto.lobby_messages.ChangeColorMessage;
import com.mattreisdorf.carcaclone_backend.dto.lobby_messages.CreateLobbyMessage;
import com.mattreisdorf.carcaclone_backend.dto.lobby_messages.JoinLobbyMessage;
import com.mattreisdorf.carcaclone_backend.dto.lobby_messages.PlayerReadyMessage;
import com.mattreisdorf.carcaclone_backend.dto.lobby_messages.PrivateLobbyMessage;
import com.mattreisdorf.carcaclone_backend.model.Lobby;
import com.mattreisdorf.carcaclone_backend.service.LobbyManager;

@Controller
public class LobbyController {

  @Autowired
  private LobbyManager lobbyManager;

  @Autowired
  private SimpMessagingTemplate messagingTemplate;

  // Create a new lobby
  @MessageMapping("/createLobby")
  public void createLobby(CreateLobbyMessage message, Principal principal) {
    Lobby lobby = lobbyManager.createLobby(message.getPlayerId(), message.getPlayerName());
    if (lobby == null) {
      return;
    }
    messagingTemplate.convertAndSendToUser(
        principal.getName(),
        "/queue/lobbyCreated",
        lobby);
  }

  // Join an existing lobby
  @MessageMapping("/joinLobby")
  public void joinLobby(JoinLobbyMessage message) {
    Lobby lobby = lobbyManager.joinLobby(message.getLobbyId(), message.getPlayerId(), message.getPlayerName());
    if (lobby == null) {
      return;
    }
    messagingTemplate.convertAndSend(
        "/topic/lobby/" + message.getLobbyId(),
        lobby);
  }

  @MessageMapping("/ready")
  public void markReady(PlayerReadyMessage message) {
    Lobby lobby = lobbyManager.setPlayerReady(message.getPlayerId(), message.getLobbyId(), message.isPlayerReady());
    if (lobby == null) {
      return;
    }
    messagingTemplate.convertAndSend(
        "/topic/lobby/" + message.getLobbyId(),
        lobby);
  }

  @MessageMapping("/setPrivate")
  public void setPrivate(PrivateLobbyMessage message) {
    Lobby lobby = lobbyManager.setLobbyPrivate(message.getLobbyId(), message.getIsPrivateLobby());
    if (lobby == null) {
      return;
    }
    messagingTemplate.convertAndSend(
        "/topic/lobby/" + message.getLobbyId(),
        lobby);
  }

  @MessageMapping("/changeColor")
  public void changePlayerColor(ChangeColorMessage message) {
    System.out.println(message.toString());
    Lobby lobby = lobbyManager.changePlayerColor(message.getPlayerId(), message.getPlayerColor(), message.getNewPlayerColor(), message.getLobbyId());
    if (lobby == null) {
      return;
    }
    messagingTemplate.convertAndSend(
      "/topic/lobby/" + message.getLobbyId(),
      lobby
    );
  }
}
