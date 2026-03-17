package classique;

public class Item implements ElementInterface {
    private int energyDelta;
    private boolean isBonus;

    public int getEnergyDelta() {
        return energyDelta;
    }

    public void setEnergyDelta(int energyDelta) {
        this.energyDelta = energyDelta;
    }

    public boolean isBonus() {
        return isBonus;
    }

    public void setBonus(boolean bonus) {
        isBonus = bonus;
    }
    @Override
    public int getEnergie() {
        return energyDelta; // relie getEnergie() à l'existant
    }


}