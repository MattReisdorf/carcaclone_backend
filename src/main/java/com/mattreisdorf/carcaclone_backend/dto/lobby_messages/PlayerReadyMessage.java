package com.mattreisdorf.carcaclone_backend.dto.lobby_messages;


public class PlayerReadyMessage {
  private String action;
  private String lobbyId;
  private String playerId;
  private String playerName;
  private String playerColor;
  private boolean isPlayerReady;

    public String getAction() {
    return action;
  }
  public String getLobbyId() {
    return lobbyId;
  }
  public String getPlayerId() {
    return playerId;
  }
  public String getPlayerName() {
    return playerName;
  }
  public String getPlayerColor() {
    return playerColor;
  }
  public boolean isPlayerReady() {
    return isPlayerReady;
  }
  public void setIsPlayerReady(boolean isPlayerReady) {
    this.isPlayerReady = isPlayerReady;
  }

  @Override
  public String toString() {
    return (
      "PlayerReadyMessage {" +
      "action = '" + action + '\'' +
      ", lobbyId = '" + lobbyId + '\'' +
      ", playerId = '" + playerId + '\'' +
      ", playerName = '" + playerName + '\'' +
      ", playerColor = '" + playerColor + '\'' +
      ", isReady = '" + isPlayerReady + '\'' +
      '}'
    );
  }
}
