package com.mattreisdorf.carcaclone_backend.dto.lobby_messages;

public class CreateLobbyMessage {
  private String action;
  private String playerId;
  private String playerName;

  public String getAction() {
    return action;
  }
  public void setAction(String action) {
    this.action = action;
  }

  public String getPlayerId() {
    return playerId;
  }
  public void setPlayerId(String playerId) {
    this.playerId = playerId;
  }

  public String getPlayerName() {
    return playerName;
  }
  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  @Override
  public String toString() {
    return (
      "CreateLobbyMessage: {"
        + "action: " + action 
        + ", playerId: " + playerId
        + ", playerName: " + playerName
        + '}'
    );
  }
}
