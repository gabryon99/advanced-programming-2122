package me.gabryon.ap_first_assignment.game.ui.listeners;

import java.util.EventListener;

/**
 *  Event listener invoked when the game is restarted.
 */
public interface GameRestartListener extends EventListener {
    /**
     * When the game restart action has been invoked perform the method.
     */
    void onGameRestart();
}
