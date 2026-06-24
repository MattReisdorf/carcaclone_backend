package com.mattreisdorf.carcaclone_backend.dto.game_messages;

public class DrawTileMessage {
  private String action;
  private String gameId;
  private String playerId;

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

  public String getPlayerId() {
    return playerId;
  }

  public void setPlayerId(String playerId) {
    this.playerId = playerId;
  }

  @Override
  public String toString() {
    return (
      "StartMessage: { " +
      "action: " + action + 
      ", gameId: " + gameId +
      ", playerId: " + playerId + " }"
    );
  }
}
