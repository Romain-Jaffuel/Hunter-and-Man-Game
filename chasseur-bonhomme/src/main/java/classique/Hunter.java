package classique;

import java.util.EnumSet;

public class Hunter extends Player {

    public Hunter() {
        super();
    }

    public Grid.Direction chooseMove(Grid grid) {
        Cell current = getPosition();
        Cell target = grid.getTarget();

        if (current == null || target == null) {
            return null;
        }

        int dx = target.getX() - current.getX();
        int dy = target.getY() - current.getY();

        EnumSet<Grid.Direction> moves = grid.availableMoves(this);

        if (Math.abs(dx) >= Math.abs(dy)) {
            if (dx > 0 && moves.contains(Grid.Direction.E)) {
                return Grid.Direction.E;
            }
            if (dx < 0 && moves.contains(Grid.Direction.W)) {
                return Grid.Direction.W;
            }
            if (dy > 0 && moves.contains(Grid.Direction.S)) {
                return Grid.Direction.S;
            }
            if (dy < 0 && moves.contains(Grid.Direction.N)) {
                return Grid.Direction.N;
            }
        } else {
            if (dy > 0 && moves.contains(Grid.Direction.S)) {
                return Grid.Direction.S;
            }
            if (dy < 0 && moves.contains(Grid.Direction.N)) {
                return Grid.Direction.N;
            }
            if (dx > 0 && moves.contains(Grid.Direction.E)) {
                return Grid.Direction.E;
            }
            if (dx < 0 && moves.contains(Grid.Direction.W)) {
                return Grid.Direction.W;
            }
        }

        return null;
    }

    public boolean canEliminate(Player player) {
        Cell hunterCell = getPosition();
        Cell playerCell = player.getPosition();

        if (hunterCell == null || playerCell == null) {
            return false;
        }

        return hunterCell.isAdjacentTo(playerCell);
    }

    public void eliminate(Player player) {
        if (canEliminate(player)) {
            player.setEnergy(0);
        }
    }
}