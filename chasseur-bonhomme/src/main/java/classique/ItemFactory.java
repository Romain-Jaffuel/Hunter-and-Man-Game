package classique;

import java.util.Random;

public class ItemFactory {

    public static ElementInterface createItem(boolean isBonus) {
        Random rand = new Random();

        if (isBonus) {
            // Choisit aléatoirement entre Item bonus et Fruits
            int choice = rand.nextInt(2);
            if (choice == 0) {
                Item item = new Item();
                item.setEnergyDelta(rand.nextInt(30) + 1);
                item.setBonus(true);
                return item;
            } else {
                return new AdaptateurFruits(new Fruits(rand.nextInt(30) + 1));
            }
        } else {
            // Choisit aléatoirement entre Item malus, Feu et Predateur
            int choice = rand.nextInt(3);
            switch (choice) {
                case 0:
                    Item item = new Item();
                    item.setEnergyDelta(-(rand.nextInt(20) + 1));
                    item.setBonus(false);
                    return item;
                case 1:
                    return new AdaptateurFeu(new Feu(rand.nextInt(20) + 1));
                default:
                    return new AdaptateurPredateur(new Predateur(rand.nextInt(20) + 1));
            }
        }
    }
}