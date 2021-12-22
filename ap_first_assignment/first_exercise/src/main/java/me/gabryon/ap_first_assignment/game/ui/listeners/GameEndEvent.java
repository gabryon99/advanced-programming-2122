package me.gabryon.ap_first_assignment.game.ui.listeners;

import java.util.List;
import java.util.Optional;
import me.gabryon.ap_first_assignment.game.GameState;
import me.gabryon.ap_first_assignment.game.ui.TTTCell;

/**
 *
 * @author gabryon
 */
public class GameEndEvent {
    
    private final GameState endingState;
    private final Optional<List<TTTCell>> cells;
    
    public GameEndEvent(GameState endingState, Optional<List<TTTCell>> cells) {
        this.endingState = endingState;
        this.cells = cells;
    }
    
    public GameState getEndingState() {
        return endingState;
    }
    
    public Optional<List<TTTCell>> getCells() {
        return cells;
    }
    
}
