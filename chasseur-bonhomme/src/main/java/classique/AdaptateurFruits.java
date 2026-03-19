package classique;

public class AdaptateurFruits implements ElementInterface {
    private Fruits fruits;
    public AdaptateurFruits(Fruits fruits) { this.fruits = fruits; }

    @Override
    public int getEnergie() {
        return fruits.gainEnergie(); // adapte gainEnergie() → getEnergie()
    }
}