package com.mattreisdorf.carcaclone_backend.service;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.mattreisdorf.carcaclone_backend.enums.GameState;
import com.mattreisdorf.carcaclone_backend.model.Game;
import com.mattreisdorf.carcaclone_backend.model.Lobby;
import com.mattreisdorf.carcaclone_backend.model.Player;
import com.mattreisdorf.carcaclone_backend.model.Tile;

@Service
public class GameManager {

  // Hold a map of active games
  private final Map<String, Game> games = new ConcurrentHashMap<>();

  public Game createGameFromLobby(Lobby lobby) {

    List<Player> playersCopy = new ArrayList<>(lobby.getPlayers());

    Game game = new Game(playersCopy);

    games.put(game.getGameId(), game);

    lobby.setIsGameStarted(true);

    return game;
  }


  public Game getGame(String gameId) {
    return games.get(gameId);
  }


  public Game startGame(Game game) {
    // Game game = getGame(gameId);
    game.setGameState(GameState.DRAWING_TILE);
    return game;
  }

  public Tile drawTile(Game game) {
    Deque<Tile> tileBag = game.getTileBag();
    if (tileBag.isEmpty()) {
      throw new IllegalStateException("No Tiles Left");
    }
    Tile drawnTile = tileBag.pop();

    game.setGameState(GameState.PLACING_TILE);

    return drawnTile;

  }

}
