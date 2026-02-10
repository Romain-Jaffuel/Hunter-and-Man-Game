package classique;

import java.util.EnumSet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        Grid g = new Grid();

        Player player = new Player();
        player.setPosition(g.getCell(0,0));
        g.getCell(0,0).setOccupiedBy(player);

        Hunter hunter = new Hunter();
        hunter.setPosition(g.getCell(g.getWidth() - 1, g.getHeight() - 1));
        g.getCell(g.getWidth() - 1, g.getHeight() - 1).setOccupiedBy(hunter);
        System.out.println(g);

        System.out.println("Hunter's energy : "+hunter.getEnergy()+"\nPlayer's energy : "+player.getEnergy());

        EnumSet<Grid.Direction> moves = g.availableMoves(player);
        System.out.println("Available moves: " + moves);

        Grid.Direction chosen = null;
        Scanner d = new Scanner(System.in);

        while (chosen == null) {
            System.out.print("Choose direction: ");
            String choice = d.nextLine().trim().toUpperCase();

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
}
