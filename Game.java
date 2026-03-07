package classique;

import java.util.*;

public class Game {
    private int turn = 0;
    private boolean gameOver = false;
    private Grid g = new Grid();
    private Player player = new Player();
    private Hunter hunter = new Hunter();
    private final Scanner scanner = new Scanner(System.in);

    public int getTurn() { return turn; }
    public void setTurn(int turn) { this.turn = turn; }

    public void initGame() {
        player.setPosition(g.getCell(0, 0));
        g.getCell(0, 0).setOccupiedBy(player);

        hunter.setPosition(g.getCell(g.getWidth() - 1, g.getHeight() - 1));
        g.getCell(g.getWidth() - 1, g.getHeight() - 1).setOccupiedBy(hunter);

        g.setTarget();
        System.out.println(g);
    }

    public void displayStatus() {
        System.out.println("Turn : "+turn+"\nHunter's energy : "+hunter.getEnergy()+"\nPlayer's energy : "+player.getEnergy());
        EnumSet<Grid.Direction> moves = g.availableMoves(player);
        System.out.println("Available moves: " + moves);
    }

    public void playerTurn() {
        Grid.Direction chosen = null;
        EnumSet<Grid.Direction> moves = g.availableMoves(player);

        while (chosen == null) {
            System.out.print("Choose direction: ");
            String choice = scanner.nextLine().trim().toUpperCase();

            try {
                Grid.Direction tmp = Grid.Direction.valueOf(choice);
                if (moves.contains(tmp)) {
                    chosen = tmp;
                } else {
                    System.out.println("Direction not allowed from this position.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Use N, S, E or W.");
            }
        }

        g.move(player, chosen);
        System.out.println(g);
    }

    public void hunterTurn() {

    }

    public void checkGameOver() {
        if (player.getEnergy() <= 0) {
            gameOver = true;
        }
        if (player.getPosition() == g.getTarget()) {
            gameOver = true;
        }
        if (turn > 100) {
            gameOver = true;
        }
    }

    public void run() {
        initGame();
        while (!gameOver) {
            displayStatus();
            playerTurn(); // + application bonus/malus
            hunterTurn(); // + application bonus/malus
            checkGameOver(); //check fin de jeu, energie/chasseur
            this.turn++;
        }
        System.out.println("Game Over");
    }
}