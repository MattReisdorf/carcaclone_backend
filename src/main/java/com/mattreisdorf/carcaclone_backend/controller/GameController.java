package com.mattreisdorf.carcaclone_backend.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

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

  @MessageMapping("/start") 
  public void startGame(StartMessage message) {
    System.out.println(message.toString());
    Game game = gameManager.getGame(message.getGameId());
    if (game == null) {
      return;
    }
    game = gameManager.startGame(game);

    messagingTemplate.convertAndSend(
      "/topic/game/" + message.getGameId(),
      game

    );
  }

  // @MessageMapping("/{gameId}/drawTile")
  // public void handleDrawTile(@DestinationVariable String gameId, Principal principal) {
  //   Game game = gameManager.getGame(gameId);
  //   Tile drawnTile = gameManager.drawTile(game);

  //   messagingTemplate.convertAndSendToUser(
  //     principal.getName(),
  //     "/queue/tileDrawn",
  //     drawnTile
  //   );
  //   messagingTemplate.convertAndSend(
  //     "/topic/game/" + gameId + "/tileDrawn",
  //     game
  //   );
  // }
  
}
