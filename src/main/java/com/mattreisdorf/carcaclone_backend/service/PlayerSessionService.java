package com.mattreisdorf.carcaclone_backend.service;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

import com.mattreisdorf.carcaclone_backend.config.StompPrincipal;
import com.mattreisdorf.carcaclone_backend.dto.PlayerSessionResponse;

import jakarta.servlet.http.HttpSession;

@Service
public class PlayerSessionService {
  public static final String PLAYER_ID_ATTRIBUTE = "playerId";
  public static final String PLAYER_NAME_ATTRIBUTE = "playerName";

  private static final String[] NAME_PREFIXES = {
      "Amber", "Autumn", "Bright", "Cedar", "Crimson", "Golden", "Hidden", "Ivory",
      "Jade", "Lucky", "Merry", "Misty", "Nimble", "Quiet", "Rapid", "Silver"
  };

  private static final String[] NAME_SUFFIXES = {
      "Badger", "Brook", "Falcon", "Fern", "Fox", "Harbor", "Hollow", "Maple",
      "Meadow", "Otter", "Pine", "River", "Sparrow", "Stone", "Vale", "Willow"
  };

  public PlayerSessionResponse getOrCreatePlayerSession(HttpSession session) {
    String playerId = (String) session.getAttribute(PLAYER_ID_ATTRIBUTE);
    String playerName = (String) session.getAttribute(PLAYER_NAME_ATTRIBUTE);

    if (playerId == null || playerName == null) {
      playerId = UUID.randomUUID().toString();
      playerName = generatePlayerName();
      session.setAttribute(PLAYER_ID_ATTRIBUTE, playerId);
      session.setAttribute(PLAYER_NAME_ATTRIBUTE, playerName);
    }

    return new PlayerSessionResponse(playerId, playerName);
  }

  public PlayerSessionResponse getPlayerFromPrincipal(Principal principal) {
    if (principal instanceof StompPrincipal stompPrincipal) {
      return new PlayerSessionResponse(
          stompPrincipal.getPlayerId(),
          stompPrincipal.getPlayerName());
    }
    throw new IllegalStateException("Authenticated player session not found.");
  }

  public PlayerSessionResponse getPlayerFromAttributes(Map<String, Object> attributes) {
    String playerId = (String) attributes.get(PLAYER_ID_ATTRIBUTE);
    String playerName = (String) attributes.get(PLAYER_NAME_ATTRIBUTE);
    if (playerId == null || playerName == null) {
      throw new IllegalStateException("WebSocket session is missing player identity.");
    }
    return new PlayerSessionResponse(playerId, playerName);
  }

  private String generatePlayerName() {
    ThreadLocalRandom random = ThreadLocalRandom.current();
    String prefix = NAME_PREFIXES[random.nextInt(NAME_PREFIXES.length)];
    String suffix = NAME_SUFFIXES[random.nextInt(NAME_SUFFIXES.length)];
    int number = random.nextInt(100, 1000);
    return prefix + suffix + number;
  }
}
