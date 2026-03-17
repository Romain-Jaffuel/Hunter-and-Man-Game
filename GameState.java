package classique;

public interface GameState {
    void handle(Game game);
    String getDescription();
}