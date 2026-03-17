package classique;

import java.util.EnumSet;

public class StrategieExploratrice implements DeplacementStrategy {

    @Override
    public Grid.Direction choisirDirection(EnumSet<Grid.Direction> moves, Player player, Grid grid) {
        Cell current = player.getPosition();

        for (Grid.Direction dir : moves) {
            Cell next = getNextCell(current, dir, grid);
            if (next != null && !player.hasVisited(next)) {
                System.out.println("Strategie exploratrice : direction " + dir);
                return dir;
            }
        }
        // Toutes adjacentes visitées → première direction disponible
        System.out.println("Toutes les cases visitées, direction par défaut.");
        return moves.iterator().next();
    }

    private Cell getNextCell(Cell current, Grid.Direction dir, Grid grid) {
        int x = current.getX();
        int y = current.getY();
        switch (dir) {
            case N: return grid.getCell(x, y - 1);
            case S: return grid.getCell(x, y + 1);
            case E: return grid.getCell(x + 1, y);
            case W: return grid.getCell(x - 1, y);
            default: return null;
        }
    }
}