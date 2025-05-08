package com.mattreisdorf.carcaclone_backend.service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mattreisdorf.carcaclone_backend.model.Lobby;
import com.mattreisdorf.carcaclone_backend.model.Player;

@Service
public class LobbyManager {

  // Hold a map of active lobbies
  private final Map<String, Lobby> lobbies = new ConcurrentHashMap<>();
  
  // Create a new lobby
  public Lobby createLobby(String playerId, String playerName) {
    // Create new Player
    Player host = new Player(playerId, playerName);
    // Set Player as host
    host.setHost(true);

    // Create a new lobby
    Lobby lobby = new Lobby();
    // Add host as first player in lobby
    lobby.addPlayer(host);
    // Add lobby to Map of active lobbies
    lobbies.put(lobby.getLobbyId(), lobby);
    return lobby;
  }

  public Lobby getLobby(String lobbyId) {
    // Get and return lobby from lobbies map
    return lobbies.get(lobbyId);
  }

  // Get all active public lobbies
  public Collection<Lobby> getAllLobbies() {
    return lobbies.values()
      // Stream and filter lobbies when lobby is not set to private or already started
      .stream()
      .filter(lobby -> !lobby.isPrivateLobby() && !lobby.isGameStarted())
      // Collect those lobbies into a new list that is returned
      .collect(Collectors.toList());
  }

  // Add player to active public lobby
  public Lobby joinLobby(String lobbyId, String playerId, String playerName) {
    Lobby lobby = getLobby(lobbyId);
    if (lobby != null) {
      boolean alreadyJoined = false;
      if (playerId != null & playerName != null) {
        alreadyJoined = lobby.getPlayers().stream().anyMatch(player -> player.getPlayerId().equals(playerId));
      }

      if (!alreadyJoined) {
        Player player = new Player(playerId, playerName);
        lobby.addPlayer(player);
      }
    }
    return lobby;
  }

  // Set a player in a lobby as ready
  public Lobby setPlayerReady(String playerId, String lobbyId, boolean isPlayerReady) {
    Lobby lobby = getLobby(lobbyId);
    lobby.getPlayers().stream().filter(player -> player.getPlayerId().equalsIgnoreCase(playerId)).findFirst().ifPresent(player -> player.setPlayerReady(isPlayerReady));
    return lobby;
  }

  // Change private lobby status
  public Lobby setLobbyPrivate(String lobbyId, boolean isLobbyPrivate) {
    Lobby lobby = getLobby(lobbyId);
    lobby.setIsPrivateLobby(isLobbyPrivate);
    return lobby;
  }
}
