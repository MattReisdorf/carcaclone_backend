package com.mattreisdorf.carcaclone_backend.config;

import java.security.Principal;

public class StompPrincipal implements Principal {
  private final String playerId;
  private final String playerName;

  public StompPrincipal(String playerId, String playerName) {
    this.playerId = playerId;
    this.playerName = playerName;
  }

  @Override
  public String getName() {
    return playerId;
  }

  public String getPlayerId() {
    return playerId;
  }

  public String getPlayerName() {
    return playerName;
  }
}
