package com.jeu.chasseur_bonhomme;

import java.util.List;

public class GameStateDTO {
    public int turn;
    public int playerEnergy;
    public int hunterEnergy;
    public boolean gameOver;
    public String message;
    public boolean victory;
    public List<CellDTO> cells; // grille 8x8

    public static class CellDTO {
        public int x;
        public int y;
        public String content; // "P", "H", "T", "I", "."
        public String itemType;
    }
}