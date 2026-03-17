package classique;

public class GameOverState implements GameState {
    private String reason;
    public GameOverState(String reason) { this.reason = reason; }

    @Override
    public void handle(Game game) {
        game.setGameOver(true);
    }
    @Override
    public String getDescription() { return "Game Over : " + reason; }
}