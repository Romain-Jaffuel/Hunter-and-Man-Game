package com.jeu.chasseur_bonhomme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/start")
    public String start() {
        gameService.restart();
        return "Game started";
    }

    @PostMapping("/move/{direction}")
    public String move(@PathVariable String direction) {
        return gameService.movePlayer(direction);
    }

    @GetMapping("/state")
    public GameStateDTO state() {
        return gameService.getState();
    }
}