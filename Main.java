package classique;

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
    }
}
