package com.mattreisdorf.carcaclone_backend.controller;

import java.security.Principal;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.mattreisdorf.carcaclone_backend.dto.lobby_messages.ChangeColorMessage;
import com.mattreisdorf.carcaclone_backend.dto.lobby_messages.CreateLobbyMessage;
import com.mattreisdorf.carcaclone_backend.dto.lobby_messages.JoinLobbyMessage;
import com.mattreisdorf.carcaclone_backend.dto.PlayerSessionResponse;
import com.mattreisdorf.carcaclone_backend.dto.lobby_messages.PlayerReadyMessage;
import com.mattreisdorf.carcaclone_backend.dto.lobby_messages.PrivateLobbyMessage;
import com.mattreisdorf.carcaclone_backend.dto.lobby_messages.StartGameMessage;
import com.mattreisdorf.carcaclone_backend.dto.lobby_messages.StartGameResponse;
import com.mattreisdorf.carcaclone_backend.model.Game;
import com.mattreisdorf.carcaclone_backend.model.Lobby;
import com.mattreisdorf.carcaclone_backend.service.GameManager;
import com.mattreisdorf.carcaclone_backend.service.LobbyManager;
import com.mattreisdorf.carcaclone_backend.service.PlayerSessionService;

@Controller
@MessageMapping("/lobby")
public class LobbyController {

  private final LobbyManager lobbyManager;
  private final GameManager gameManager;
  private final SimpMessagingTemplate messagingTemplate;
  private final PlayerSessionService playerSessionService;

  public LobbyController(
      LobbyManager lobbyManager,
      GameManager gameManager,
      SimpMessagingTemplate messagingTemplate,
      PlayerSessionService playerSessionService) {
    this.lobbyManager = lobbyManager;
    this.gameManager = gameManager;
    this.messagingTemplate = messagingTemplate;
    this.playerSessionService = playerSessionService;
  }

  // Create a new lobby
  @MessageMapping("/createLobby")
  public void createLobby(CreateLobbyMessage message, Principal principal) {
    PlayerSessionResponse player = playerSessionService.getPlayerFromPrincipal(principal);
    Lobby lobby = lobbyManager.createLobby(player.playerId(), player.playerName());
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
  public void joinLobby(JoinLobbyMessage message, Principal principal) {
    PlayerSessionResponse player = playerSessionService.getPlayerFromPrincipal(principal);
    Lobby lobby = lobbyManager.joinLobby(message.getLobbyId(), player.playerId(), player.playerName());
    if (lobby == null) {
      return;
    }
    messagingTemplate.convertAndSend(
        "/topic/lobby/" + message.getLobbyId(),
        lobby);
  }

  @MessageMapping("/ready")
  public void markReady(PlayerReadyMessage message, Principal principal) {
    PlayerSessionResponse player = playerSessionService.getPlayerFromPrincipal(principal);
    Lobby lobby = lobbyManager.setPlayerReady(player.playerId(), message.getLobbyId(), message.isPlayerReady());
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
  public void changePlayerColor(ChangeColorMessage message, Principal principal) {
    PlayerSessionResponse player = playerSessionService.getPlayerFromPrincipal(principal);
    Lobby lobby = lobbyManager.changePlayerColor(player.playerId(), message.getPlayerColor(),
        message.getNewPlayerColor(), message.getLobbyId());
    if (lobby == null) {
      return;
    }
    messagingTemplate.convertAndSend(
        "/topic/lobby/" + message.getLobbyId(),
        lobby);
  }

  @MessageMapping("/startGame")
  public void startGame(StartGameMessage message) {
    Lobby lobby = lobbyManager.getLobby(message.getLobbyId());
    if (lobby == null)
      return;
    Game game = gameManager.createGameFromLobby(lobby);
    messagingTemplate.convertAndSend(
        "/topic/lobby/" + message.getLobbyId() + "/start",
        new StartGameResponse(message.getLobbyId(), game.getGameId()));

  }
}
