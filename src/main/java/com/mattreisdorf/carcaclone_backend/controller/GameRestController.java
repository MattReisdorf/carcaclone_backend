package com.mattreisdorf.carcaclone_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mattreisdorf.carcaclone_backend.model.Game;
import com.mattreisdorf.carcaclone_backend.service.GameManager;

@RestController
@RequestMapping("/api/games")
public class GameRestController {
  
  @Autowired
  private GameManager gameManager;

  @GetMapping("/{gameId}")
  public ResponseEntity<Game> getGame(@PathVariable String gameId) {
    Game game = gameManager.getGame(gameId);
    if (game == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(game);
  }
}
