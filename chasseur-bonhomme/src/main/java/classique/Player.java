package classique;

import java.util.ArrayList;
import java.util.List;

public class Player implements GameConstants, Observable {
    private int energy;
    private Cell position;
    private DeplacementStrategy strategy;
    private List<Cell> visitedCells = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>(); // ← liste des abonnés

    public Player() {
        energy = 100;
        position = null;
        strategy = new StrategieInitiale();
    }
    
    // --- Observable ---
    @Override
    public void addObserver(Observer o) { observers.add(o); }

    @Override
    public void removeObserver(Observer o) { observers.remove(o); }

    @Override
    public void notifyObservers(GameEvent event) {
        for (Observer o : observers) {
            o.update(event);
        }
    }
    // --- Getters/Setters avec notifications ---
    public int getEnergy() { return energy; }

    public void setEnergy(int energy) {
        this.energy = energy;
        updateStrategy();
        notifyObservers(GameEvent.ENERGY_CHANGED); // ← notifie automatiquement
    }

    public Cell getPosition() {
        return position;
    }
    public void setPosition(Cell position) {
        this.position = position;
        if (position != null && !visitedCells.contains(position)) {
            visitedCells.add(position);
        }
    }
    
    public DeplacementStrategy getStrategy() { return strategy; }
    public void setStrategy(DeplacementStrategy strategy) { this.strategy = strategy; }
    public boolean hasVisited(Cell cell) { return visitedCells.contains(cell); }

 // Changement automatique selon highValue (vient de GameConstants)
    private void updateStrategy() {
        if (energy > GameConstants.HIGH_VALUE) {
            strategy = new StrategieExploratrice();
        } else {
            strategy = new StrategieInitiale();
        }
    }

    

}