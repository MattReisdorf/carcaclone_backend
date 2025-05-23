package com.mattreisdorf.carcaclone_backend.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mattreisdorf.carcaclone_backend.model.Lobby;
import com.mattreisdorf.carcaclone_backend.service.LobbyManager;

@RestController
@RequestMapping("/api/lobbies")
public class LobbyRestController {
  
  @Autowired
  private LobbyManager lobbyManager;

  // Endpoint for listing all active public lobbies
  @GetMapping
  public Collection<Lobby> getActiveLobbies() {
    Collection<Lobby> lobbies = lobbyManager.getAllLobbies();
    return (lobbies == null) ? new ArrayList<>() : lobbies;
  }

  // Endpoint for ensuring lobby existence when attempting to join lobby
  @GetMapping("/{lobbyId}")
  public ResponseEntity<Lobby> getLobby(@PathVariable String lobbyId) {
    Lobby lobby = lobbyManager.getLobby(lobbyId);
    return (lobby == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(lobby);
  }
}
