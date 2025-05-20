package com.mattreisdorf.carcaclone_backend.dto.lobby_messages;

public class StartGameResponse {
  private String lobbyId;
  private String gameId;

  public StartGameResponse(String lobbyId, String gameId) {
    this.lobbyId = lobbyId;
    this.gameId = gameId;
  }

  public String getGameId() {
    return gameId;
  }

  public void setGameId(String gameId) {
    this.gameId = gameId;
  }

  public String getLobbyId() {
    return lobbyId;
  }

  public void setLobbyId(String lobbyId) {
    this.lobbyId = lobbyId;
  }

  @Override
  public String toString() {
    return ("StartGameMessage {" +
        "gameId = '" + gameId + '\'' +
        ", lobbyId = '" + lobbyId + '\'' + '}');
  }
}
