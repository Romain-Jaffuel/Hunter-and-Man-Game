package classique;

import java.util.EnumSet;
import java.util.Scanner;

public class StrategieInitiale implements DeplacementStrategy {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public Grid.Direction choisirDirection(EnumSet<Grid.Direction> moves, Player player, Grid grid) {
        Grid.Direction chosen = null;
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
        return chosen;
    }
}