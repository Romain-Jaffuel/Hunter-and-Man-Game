package classique;

import java.util.EnumSet;

public interface DeplacementStrategy {
    Grid.Direction choisirDirection(EnumSet<Grid.Direction> moves, Player player, Grid grid);
}