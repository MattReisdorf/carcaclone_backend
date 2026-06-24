package com.mattreisdorf.carcaclone_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mattreisdorf.carcaclone_backend.dto.PlayerSessionResponse;
import com.mattreisdorf.carcaclone_backend.service.PlayerSessionService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/session")
public class SessionController {

  @Autowired
  private PlayerSessionService playerSessionService;

  @GetMapping("/me")
  public PlayerSessionResponse getOrCreateSession(HttpSession session) {
    return playerSessionService.getOrCreatePlayerSession(session);
  }
}
