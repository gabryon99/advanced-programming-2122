package me.gabryon.ap_first_assignment.game;

public enum GameState {
    
    START_GAME("Start Game"),
    PLAYER_X("Next move: X"),
    PLAYER_O("Next move: O"),
    DRAW("Draw!"),
    X_WON("The winner is: X"),
    O_WON("The winner is: O");
    
    private final String description;
    
    private GameState(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
    
}
