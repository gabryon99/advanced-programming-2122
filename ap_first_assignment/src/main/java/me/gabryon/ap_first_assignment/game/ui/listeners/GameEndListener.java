package me.gabryon.ap_first_assignment.game.ui.listeners;

import java.util.EventListener;

/**
 *
 * @author gabryon
 */
public interface GameEndListener extends EventListener {
    void onGameEnd(GameEndEvent evt);
}
