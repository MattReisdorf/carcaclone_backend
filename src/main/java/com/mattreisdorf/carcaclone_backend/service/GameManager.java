package com.mattreisdorf.carcaclone_backend.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mattreisdorf.carcaclone_backend.enums.EdgeDirection;
import com.mattreisdorf.carcaclone_backend.enums.GameState;
import com.mattreisdorf.carcaclone_backend.model.Board;
import com.mattreisdorf.carcaclone_backend.model.Coordinate;
import com.mattreisdorf.carcaclone_backend.model.Game;
import com.mattreisdorf.carcaclone_backend.model.Lobby;
import com.mattreisdorf.carcaclone_backend.model.Player;
import com.mattreisdorf.carcaclone_backend.model.Tile;
import com.mattreisdorf.carcaclone_backend.model.TileSegment;

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

    System.out.println(drawnTile.toString());

    game.setGameState(GameState.PLACING_TILE);

    return drawnTile;

  }

  // PLACE TILE SIMULATION
  public Game placeTile(Game game, Tile drawnTile, int x, int y, int orientation) {

    Tile rotatedTile = rotateTile(drawnTile, orientation);

    Coordinate coordinate = new Coordinate(x, y);
    
    Board board = game.getBoard();

    board.placeTile(coordinate, rotatedTile, orientation);

    int next = (game.getCurrentPlayerIndex() + 1) % game.getPlayers().size();
    game.setCurrentPlayerIndex(next);
    game.setGameState(GameState.DRAWING_TILE);
    
    return game;

    // Board board = game.getBoard();
    // board.placeTile(null, drawnTile);

    // int next = (game.getCurrentPlayerIndex() + 1) % game.getPlayers().size();
    // game.setCurrentPlayerIndex(next);
    // // game.set;
    // game.setGameState(GameState.DRAWING_TILE);
    // return game;
  }


  private Tile rotateTile(Tile tile, int orientation) {
  // how many 90° steps (0–3)
  int steps = ((orientation / 90) % 4 + 4) % 4;
  EdgeDirection[] dirs = EdgeDirection.values();

  return new Tile(
    tile.getTileCode(),
    tile.getSegments().stream()
      .map(seg -> new TileSegment(
         seg.getTileFeature(),
         seg.getConnects().stream()
             .map(d -> {
               // find index in the enum array, shift, wrap
               int idx = Arrays.asList(dirs).indexOf(d);
               return dirs[(idx + steps) % dirs.length];
             })
             .collect(Collectors.toSet()),
         seg.hasShield()
      ))
      .collect(Collectors.toList())
  );
}

  // private Tile rotateTile(Tile tile, int orientation) {
  //   int steps = ((orientation / 90) % 4 + 4) % 4;
  //   EdgeDirection[] edgeDirections = EdgeDirection.values();

  //   return new Tile(
  //     tile.getTileCode(),
  //     tile.getSegments().stream()
  //       .map(segment -> new TileSegment(
  //         segment.getTileFeature(),
  //         segment.getConnects().stream()
  //           .map(direction -> {
  //             int index = Arrays.asList(edgeDirections).indexOf(direction);
  //             return edgeDirections[(index + steps) % edgeDirections.length];
  //           })
  //           .collect(Collectors.toSet()),
  //         segment.hasShield()
  //       ))
  //       .collect(Collectors.toList())
  //   );
  // }

}
