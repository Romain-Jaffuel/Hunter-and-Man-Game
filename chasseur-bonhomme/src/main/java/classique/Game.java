package classique;

import java.util.*;
import com.jeu.chasseur_bonhomme.GameStateDTO;

public class Game implements Observer {
    private int turn = 0;
    private boolean gameOver = false;
    private GameState state = new RunningState(); // ← état initial
    private Grid g = Grid.getInstance();
    private Player player = new Player();
    private Hunter hunter = new Hunter();

    public int getTurn() { return turn; }
    public void setTurn(int turn) { this.turn = turn; }
    public void setGameOver(boolean gameOver) { this.gameOver = gameOver; }
    
    public void setState(GameState state) {
        this.state = state;
        System.out.println("[STATE] " + state.getDescription());
        state.handle(this); // ← applique l'état
    }
    
    public void checkGameOver() {
        if (player.getEnergy() <= 0)
            setState(new GameOverState("Player out of energy"));
        else if (player.getPosition() == g.getTarget())
            setState(new VictoryState("Player reached the target"));
        else if (hunter.getEnergy() <= 0)
            setState(new VictoryState("Hunter out of energy"));
        else if (hunter.getPosition() == g.getTarget())
            setState(new GameOverState("Hunter reached the target"));
        else if (turn > 100)
            setState(new GameOverState("Turn exceeds 100"));
    }

    public void initGame() {
        player.setPosition(g.getCell(0, 0));
        g.getCell(0, 0).setOccupiedBy(player);

        hunter.setPosition(g.getCell(g.getWidth() - 1, g.getHeight() - 1));
        g.getCell(g.getWidth() - 1, g.getHeight() - 1).setOccupiedBy(hunter);

        g.setTarget();
        g.setItems();

        player.addObserver(this); // ← Game s'abonne à Player
        hunter.addObserver(this); // ← Game s'abonne à Hunter

        System.out.println(g);
    }

    @Override
    public void update(GameEvent event) {
    	System.out.println("[OBSERVER] Événement reçu : " + event);
        switch (event) {
            case ENERGY_CHANGED:
                checkGameOver();
                break;
            case PLAYER_ELIMINATED:
                gameOver = true;
                System.out.println("Game Over !\nThe hunter eliminated the player.");
                break;
            case TARGET_REACHED:
                gameOver = true;
                System.out.println("Victory !\nA player reached the target.");
                break;
            case ITEM_COLLECTED:
                System.out.println("[Observer] Item collected.");
                break;
        }
    }

    public void displayStatus() {
        System.out.println("Turn : " + turn + "\nHunter's energy : " + hunter.getEnergy() + "\nPlayer's energy : " + player.getEnergy());
        EnumSet<Grid.Direction> moves = g.availableMoves(player);
        System.out.println("Available moves: " + moves);
    }

    private void applyItemEffect(Player player) {
        Cell currentCell = player.getPosition();
        if (currentCell.hasItem()) {
            ElementInterface item = currentCell.getItem();
            player.setEnergy(player.getEnergy() + item.getEnergie());
            boolean isBonus = item.getEnergie() > 0;
            currentCell.removeItem();
            if (!isBonus) {
                g.setItem(false);
            }
        }
    }

    public void playerTurn() {
        EnumSet<Grid.Direction> moves = g.availableMoves(player);
        Grid.Direction chosen = player.getStrategy().choisirDirection(moves, player, g);
        g.move(player, chosen);
        applyItemEffect(player);
        player.setEnergy(player.getEnergy() - 1);
        System.out.println(g);
    }

    public void hunterTurn() {
        if (hunter.canEliminate(player)) {
            hunter.eliminate(player);
            notifyElimination();
            return;
        }

        Grid.Direction direction = hunter.chooseMove(g);
        if (direction != null) {
            g.move(hunter, direction);
            hunter.setEnergy(hunter.getEnergy() - 1);
        }

        applyItemEffect(hunter);
        System.out.println(g);
    }

    private void notifyElimination() {
        gameOver = true;
        System.out.println("Game Over !\nThe hunter eliminated the player.");
    }
    
    
 // Nouvelle méthode pour le web (sans Scanner)
    public void playerTurnWeb(Grid.Direction direction) {
        EnumSet<Grid.Direction> moves = g.availableMoves(player);
        if (moves.contains(direction)) {
            g.move(player, direction);
            applyItemEffect(player);
            player.setEnergy(player.getEnergy() - 1);
        }
    }

    // Convertit l'état du jeu en DTO pour le frontend
    public GameStateDTO toDTO() {
        GameStateDTO dto = new GameStateDTO();
        dto.turn = this.turn;
        dto.playerEnergy = player.getEnergy();
        dto.hunterEnergy = hunter.getEnergy();
        dto.gameOver = this.gameOver;
        dto.victory = (player.getPosition() == g.getTarget() || hunter.getEnergy() <= 0);
        dto.cells = new java.util.ArrayList<>();

        // Position du joueur pour calculer le fog
        int px = player.getPosition().getX();
        int py = player.getPosition().getY();

        for (int y = 0; y < g.getHeight(); y++) {
            for (int x = 0; x < g.getWidth(); x++) {
                GameStateDTO.CellDTO cell = new GameStateDTO.CellDTO();
                cell.x = x;
                cell.y = y;

                // Visible si case actuelle ou adjacente au joueur (distance Manhattan ≤ 1)
                int dist = Math.abs(x - px) + Math.abs(y - py);
                cell.visibleByPlayer = (dist <= 3);

                classique.Cell c = g.getCell(x, y);

                if (c.getOccupiedBy() instanceof Hunter) cell.content = "H";
                else if (c.getOccupiedBy() != null)      cell.content = "P";
                else if (c == g.getTarget() && cell.visibleByPlayer) cell.content = "T";
                else if (c.hasItem()) {
                    cell.content = "I";
                    ElementInterface item = c.getItem();
                    if (item instanceof AdaptateurFeu)            cell.itemType = "FEU";
                    else if (item instanceof AdaptateurFruits)    cell.itemType = "FRUITS";
                    else if (item instanceof AdaptateurPredateur) cell.itemType = "PREDATEUR";
                    else if (item.getEnergie() > 0)               cell.itemType = "BONUS";
                    else                                          cell.itemType = "MALUS";
                }
                else cell.content = ".";

                dto.cells.add(cell);
            }
        }
        return dto;
    }

    public void run() {
        initGame();
        while (!gameOver) {
            displayStatus();
            playerTurn();
            if (!gameOver) hunterTurn();
            this.turn++;
        }
    }
    
}