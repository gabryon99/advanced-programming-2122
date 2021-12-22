package me.gabryon.ap_first_assignment.game.ui.listeners;

import java.util.EventListener;

/**
 *  Event listener invoked when the game comes to an end (win condition or draw).
 */
public interface GameEndListener extends EventListener {
    /***
     * Alert that the game ended. The event object contains
     * the winning condition (if the board has a win state) and the ending game state.
     * @param evt 
     */
    void onGameEnd(GameEndEvent evt);
}
