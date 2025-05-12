package com.mattreisdorf.carcaclone_backend.dto.chat_messages;

public class ChatMessage {
  private String lobbyId;
  private String senderId;
  private String senderName;
  private String senderColor;
  private String content;
  private long timestamp = System.currentTimeMillis();

  // Getters
  public String getLobbyId() {
    return lobbyId;
  }

  public String getSenderId() {
    return senderId;
  }

  public String getSenderName() {
    return senderName;
  }

  public String getSenderColor() {
    return senderColor;
  }

  public String getContent() {
    return content;
  }

  public long getTimestamp() {
    return timestamp;
  }

  // Setters
  public void setLobbyId(String lobbyId) {
    this.lobbyId = lobbyId;
  }

  public void setSenderId(String senderId) {
    this.senderId = senderId;
  }

  public void setSenderName(String senderName) {
    this.senderName = senderName;
  }

  public void setSenderColor(String senderColor) {
    this.senderColor = senderColor;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }
}
