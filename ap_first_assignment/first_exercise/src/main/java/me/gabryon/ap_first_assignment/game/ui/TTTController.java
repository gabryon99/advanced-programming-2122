package me.gabryon.ap_first_assignment.game.ui;

import java.beans.PropertyChangeEvent;
import me.gabryon.ap_first_assignment.game.GameState;
import me.gabryon.ap_first_assignment.game.GameLogic;

import java.beans.PropertyVetoException;
import javax.swing.JLabel;

import me.gabryon.ap_first_assignment.game.ui.listeners.GameEndEvent;
import me.gabryon.ap_first_assignment.game.ui.listeners.GameEndListener;
import me.gabryon.ap_first_assignment.game.ui.listeners.GameRestartListener;
import me.gabryon.ap_first_assignment.game.ui.listeners.VetoableCellStateChangeListener;

/**
 * The TTTController is responsible to validate the cell state changes.
 */
public class TTTController extends JLabel implements GameEndListener, GameRestartListener, VetoableCellStateChangeListener {

    private GameState gameState = GameLogic.INITIAL_GAME_STATE;

    public TTTController() {
    }

    /**
     * Update the current game state, updating the label's text.
     * @param newState 
     */
    private void updateGameState(GameState newState) {
        gameState = newState;
        setText(gameState.getDescription());
    }

    /**
     * Refresh the label's text according to the current game state.
     */
    public void refreshStateMessage() {
        setText(gameState.getDescription());
    }

    @Override
    public void onGameEnd(GameEndEvent evt) {
        /* From the event object get the final ending state, which it can be: DRAW, X_WON, O_WON */
        updateGameState(evt.getEndingState());
    }

    @Override
    public void onGameRestart() {
        /* Set the current game state to the initial one */
        updateGameState(GameLogic.INITIAL_GAME_STATE);
    }

    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
        /* Game logic goes here. */
        var cellState = (TTTCell.State) evt.getNewValue();

        /* For the first move... */
        if (gameState.equals(GameState.START_GAME)) {

            /* According to the first player who selected a X/O button decide the next turn. */
            var nextPlayerTurn = (cellState.equals(TTTCell.State.PLAYER_X)) ? GameState.PLAYER_O : GameState.PLAYER_X;
            updateGameState(nextPlayerTurn);

            return;
        }

        /* Otherwisem for the next moves... */
        var isPlayerXTurn = gameState.equals(GameState.PLAYER_X);

        if (isPlayerXTurn) {
            if (cellState.equals(TTTCell.State.PLAYER_O)) {
                throw new PropertyVetoException("It's X's turn right now!", evt);
            }
            updateGameState(GameState.PLAYER_O);
        } 
        else {
            /* Otherwise it is player O's turn! */
            if (cellState.equals(TTTCell.State.PLAYER_X)) {
                throw new PropertyVetoException("It's O's turn right now!", evt);
            }
            updateGameState(GameState.PLAYER_X);
        }
    }

}
