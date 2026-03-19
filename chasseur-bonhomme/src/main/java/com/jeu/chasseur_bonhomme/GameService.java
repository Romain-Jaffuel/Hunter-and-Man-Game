package com.jeu.chasseur_bonhomme;

import classique.*;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private Game game;

    public GameService() {
        game = new Game();
        game.initGame();
    }

    public void restart() {
        Grid.resetInstance(); // ← vide l'ancienne grille
        game = new Game();
        game.initGame();
    }

    public String movePlayer(String direction) {
        try {
            Grid.Direction dir = Grid.Direction.valueOf(direction.toUpperCase());
            game.playerTurnWeb(dir);
            game.hunterTurn();
            game.checkGameOver();
            game.setTurn(game.getTurn() + 1);
        } catch (IllegalArgumentException e) {
            return "Invalid direction";
        }
        return "OK";
    }

    public GameStateDTO getState() {
        return game.toDTO();
    }
}