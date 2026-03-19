package classique;

public class VictoryState implements GameState {
    private String reason;
    public VictoryState(String reason) { this.reason = reason; }

    @Override
    public void handle(Game game) {
        game.setGameOver(true);
    }
    @Override
    public String getDescription() { return "Victory : " + reason; }
}