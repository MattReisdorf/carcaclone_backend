package com.mattreisdorf.carcaclone_backend.model;


public class Player {
  private final String playerId;
  private String playerName;
  private String playerColor;
  private boolean isHost;
  private boolean isPlayerReady;

  // Constructor
  public Player(String playerId, String playerName) {
    this.playerId = playerId;
    this.playerName = playerName;
    this.playerColor = "";
    this.isHost = false;
    this.isPlayerReady = false;
  }

  // Getters and Setters
  public String getPlayerId() {
    return playerId;
  }  
  public String getPlayerName() {
    return playerName;
  }
  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  public String getPlayerColor() {
    return playerColor;
  }
  public void setPlayerColor(String playerColor) {
    this.playerColor = playerColor;
  }

  public boolean isHost() {
    return isHost;
  }
  public void setHost(boolean isHost) {
    this.isHost = isHost;
  }

  public boolean isPlayerReady() {
    return isPlayerReady;
  }
  public void setPlayerReady(boolean isReady) {
    this.isPlayerReady = isReady;
  }

  @Override
  public String toString() {
    return (
      "Player {" +
      "playerId = '" + playerId + '\'' +
      ", playerName = '" + playerName + '\'' +
      ", playerColor = '" + playerColor + '\'' +
      ", isHost = '" + isHost + '\'' +
      ", isPlayerReady = '" + isPlayerReady + '}'
    );
  }
}
