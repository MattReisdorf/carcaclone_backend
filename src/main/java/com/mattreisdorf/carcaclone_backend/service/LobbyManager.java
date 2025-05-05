package com.mattreisdorf.carcaclone_backend.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.mattreisdorf.carcaclone_backend.model.Lobby;
import com.mattreisdorf.carcaclone_backend.model.Player;

@Service
public class LobbyManager {

  // Hold a map of active lobbies
  private final Map<String, Lobby> lobbies = new ConcurrentHashMap<>();
  
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
}
