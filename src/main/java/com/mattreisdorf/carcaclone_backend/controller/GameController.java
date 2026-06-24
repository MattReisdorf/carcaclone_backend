package com.mattreisdorf.carcaclone_backend.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.mattreisdorf.carcaclone_backend.dto.game_messages.DrawTileMessage;
import com.mattreisdorf.carcaclone_backend.dto.game_messages.GenericMessage;
import com.mattreisdorf.carcaclone_backend.dto.game_messages.PlaceTileMessage;
import com.mattreisdorf.carcaclone_backend.dto.game_messages.StartMessage;
import com.mattreisdorf.carcaclone_backend.model.Game;
import com.mattreisdorf.carcaclone_backend.model.Tile;
import com.mattreisdorf.carcaclone_backend.service.GameManager;

@Controller
@MessageMapping("/game")
public class GameController {
  
  @Autowired
  private GameManager gameManager;

  @Autowired
  private SimpMessagingTemplate messagingTemplate;

  @MessageMapping("/{gameId}/start") 
  public void startGame(@DestinationVariable String gameId, StartMessage message) {
    System.out.println(message.toString());
    Game game = gameManager.getGame(gameId);
    if (game == null) {
      return;
    }
    game = gameManager.startGame(game);

    messagingTemplate.convertAndSend(
      "/topic/game/" + gameId,
      game

    );
  }

  @MessageMapping("/{gameId}/drawTile")
  public void handleDrawTile(@DestinationVariable String gameId, DrawTileMessage message, Principal principal) {
    System.out.println(message.toString());
    System.out.println(principal.getName());
    Game game = gameManager.getGame(gameId);
    Tile drawnTile = gameManager.drawTile(game);

    messagingTemplate.convertAndSendToUser(
      principal.getName(),
      "/queue/tileDrawn",
      drawnTile
    );
    messagingTemplate.convertAndSend(
      "/topic/game/" + gameId,
      game
    );
  }

  // PLACE TILE SIMULATION -> ADVANCE TO NEXT PLAYER UNTIL BOARD LOGIC IS BUILT OUT
  @MessageMapping("/{gameId}/placeTile")
  public void handlePlaceTile(@DestinationVariable String gameId, PlaceTileMessage message, Principal principal) {

    System.out.println(message.toString());

    Game game = gameManager.getGame(gameId);
    if (game == null) return;

    int orientation = message.getOrientation();
    int x = message.getX();
    int y = message.getY();
    Tile drawnTile = message.getDrawnTile();

    Game updated = gameManager.placeTile(game, drawnTile, x, y, orientation);

    messagingTemplate.convertAndSend(
      "/topic/game/" + gameId,
      updated
      
    );


    // System.out.println(message.toString());

    // Tile drawnTile = message.getDrawnTile();

    // Game game = gameManager.getGame(gameId);

    // // Game updatedGame = gameManager.placeTile(game);
    // gameManager.placeTile(game, drawnTile);

    // // System.out.println(updatedGame.toString());

    // messagingTemplate.convertAndSend(
    //   "/topic/game/" + gameId,
    //   game
    // );
  }
  
}
