package com.mattreisdorf.carcaclone_backend.model;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.mattreisdorf.carcaclone_backend.enums.GameState;

public class Game {
  private final String gameId;
  private List<Player> players;
  private int currentPlayerIndex;
  private GameState gameState;

  public Game(List<Player> playersCopy) {
    this.gameId = UUID.randomUUID().toString();
    this.players = playersCopy;
    this.currentPlayerIndex = new Random().nextInt(playersCopy.size()); // Should pick random starting player
    this.gameState = GameState.NOT_STARTED;
  }

  public String getCurrentPlayerId() {
    if (players == null || players.isEmpty()) {
      return null;
    }
    return players.get(currentPlayerIndex).getPlayerId();
  }

  public String getGameId() {
    return gameId;
  }

  public List<Player> getPlayers() {
    return players;
  }

  public void setPlayers(List<Player> players) {
    this.players = players;
  }

  public int getCurrentPlayerIndex() {
    return currentPlayerIndex;
  }

  public void setCurrentPlayerIndex(int currentPlayerIndex) {
    this.currentPlayerIndex = currentPlayerIndex;
  }

  public GameState getGameState() {
    return gameState;
  }

  public void setGameState(GameState gameState) {
    this.gameState = gameState;
  }

  @Override
  public String toString() {
    return ("Game: {" +
        "gameId: " + gameId +
        ", players: " + players +
        ", currentPlayerIndex: " + currentPlayerIndex +
        ", gameState: " + gameState + "}");
  }

}
