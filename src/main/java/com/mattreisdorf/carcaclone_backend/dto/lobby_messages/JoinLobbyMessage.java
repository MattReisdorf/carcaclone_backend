package com.mattreisdorf.carcaclone_backend.dto.lobby_messages;

public class JoinLobbyMessage {
  private String action;
  private String lobbyId;
  private String playerId;
  private String playerName;

  public String getAction() {
    return action;
  }
  public void setAction(String action) {
    this.action = action;
  }

  public String getLobbyId() {
    return lobbyId;
  }
  public void setLobbyId(String lobbyId) {
    this.lobbyId = lobbyId;
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
      "JoinLobbyMessage: {"
        + "action: " + action 
        + ", lobbyId: " + lobbyId
        + ", playerId: " + playerId
        + ", playerName: " + playerName
        + '}'
    );
  }
}
