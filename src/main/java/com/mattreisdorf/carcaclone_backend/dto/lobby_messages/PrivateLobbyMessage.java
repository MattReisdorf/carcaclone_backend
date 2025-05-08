package com.mattreisdorf.carcaclone_backend.dto.lobby_messages;


public class PrivateLobbyMessage {
  private String action;
  private String lobbyId;
  private boolean isPrivateLobby;

  public String getAction() {
    return action;
  }
  public String getLobbyId() {
    return lobbyId;
  }
  public boolean getIsPrivateLobby() {
    return isPrivateLobby;
  }
  public void setIsPrivateLobby(boolean isPrivateLobby) {
    this.isPrivateLobby = isPrivateLobby;
  }

  @Override
  public String toString() {
    return (
      "PrivateLobbyMessage {" +
      "action = '" + action + '\'' +
      ", lobbyId = '" + lobbyId + '\'' +
      ", isPrivateLobby = '" + isPrivateLobby + "\'}"
    );
  }
}
