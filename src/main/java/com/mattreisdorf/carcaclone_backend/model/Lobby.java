package com.mattreisdorf.carcaclone_backend.model;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;


public class Lobby {
  private final String lobbyId;
  private List<Player> players;
  private boolean isGameStarted;
  private boolean isPrivateLobby;
  

  // Lobby Constructor
  public Lobby() {
    this.lobbyId = UUID.randomUUID().toString();
    this.players = new CopyOnWriteArrayList<>();
    this.isGameStarted = false;
    this.isPrivateLobby = false;
  }


  // Add Player to Lobby
  public synchronized void addPlayer(Player player) {
    // Don't add player if game is started or lobby is full
    if (isGameStarted || players.size() >= 5) {
      return;
    }

    String avaiableColor = getAvailableColor();
    if (avaiableColor == null) {
      return;
    }
    player.setPlayerColor(avaiableColor);
    players.add(player);
  }

  // Colors currently available for use in game
  private static final List<String> ALLOWED_COLORS = Arrays.asList("red", "blue", "green", "yellow", "black");

  // Check if color is taken by another player
  // Case Insensitive
  private boolean isColorTaken(String color) {
    return players.stream().anyMatch(player -> player.getPlayerColor().equalsIgnoreCase(color));
  }
  // Returns random available color from allowed list
  private String getAvailableColor() {
    List<String> avaiableColors = ALLOWED_COLORS.stream().filter(color -> !isColorTaken(color)).collect(Collectors.toList());
    if (avaiableColors.isEmpty()) {
      return null;
    }
    Random random = new Random();
    int randomIndex = random.nextInt(avaiableColors.size());
    return avaiableColors.get(randomIndex);
  }

  // Getters and Setters
  public String getLobbyId() {
    return lobbyId;
  }
  public List<Player> getPlayers() {
    return players;
  }
  public boolean isGameStarted() {
    return isGameStarted;
  }
  public boolean isPrivateLobby() {
    return isPrivateLobby;
  }
  public void setIsGameStarted(boolean isGameStarted) {
    this.isGameStarted = isGameStarted;
  }
  public void setIsPrivateLobby(boolean isPrivateLobby) {
    this.isPrivateLobby = isPrivateLobby;
  }

  @Override
  public String toString() {
    return (
      "Lobby {" +
      "lobbyId = " + lobbyId + '\'' +
      ", players = " + players + '\'' +
      ", isGameStarted = " + isGameStarted + '\'' +
      ", isPrivateLobby = " + isPrivateLobby + '\'' + '}'
    );
  }
}
