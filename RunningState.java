package classique;

public class RunningState implements GameState {
    @Override
    public void handle(Game game) {
        // le jeu continue normalement
    }
    @Override
    public String getDescription() { return "Game is running"; }
}