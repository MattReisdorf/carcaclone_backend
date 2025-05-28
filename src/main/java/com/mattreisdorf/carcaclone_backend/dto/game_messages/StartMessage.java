package com.mattreisdorf.carcaclone_backend.dto.game_messages;

public class StartMessage {
  private String action;
  private String gameId;

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public String getGameId() {
    return gameId;
  }

  public void setGameId(String gameId) {
    this.gameId = gameId;
  }

  @Override
  public String toString() {
    return ("StartMessage: { " +
        "action: " + action +
        ", gameId: " + gameId + '}');
  }
}
