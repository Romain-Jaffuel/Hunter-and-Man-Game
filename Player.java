package classique;

import java.util.*;

public class Player {

    public Player() {
    }

    private int energy;
    private Cell position;

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public Cell getPosition() {
        return position;
    }

    public void setPosition(Cell position) {
        this.position = position;
    }
}