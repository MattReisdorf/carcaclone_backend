package com.mattreisdorf.carcaclone_backend.dto.lobby_messages;

public class StartGameMessage {
  private String action;
  // private String gameId;
  private String lobbyId;

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

  @Override
  public String toString() {
    return ("StartGameMessage: { " +
        "action: " + action +
        ", lobbyId: " + lobbyId + '}');
  }

}
