package me.gabryon.ap_first_assignment.game.ui;

import me.gabryon.ap_first_assignment.game.GameState;
import me.gabryon.ap_first_assignment.game.GameLogic;

import java.beans.PropertyVetoException;
import javax.swing.JLabel;

import me.gabryon.ap_first_assignment.game.ui.listeners.GameEndEvent;
import me.gabryon.ap_first_assignment.game.ui.listeners.GameEndListener;
import me.gabryon.ap_first_assignment.game.ui.listeners.GameRestartListener;
import me.gabryon.ap_first_assignment.game.ui.listeners.VetoableCellStateChangeListener;


/**
 * The TTTController is responsible to handle the game state.
 * @author gabryon
 */
public class TTTController extends JLabel implements GameEndListener, GameRestartListener {
    
    private GameState gameState = GameLogic.INITIAL_GAME_STATE;
    private final VetoableCellStateChangeListener vetoCellStateChangeListener = (evt) -> {
        
            /* Game logic goes here. */
            var cellState = (TTTCell.State) evt.getNewValue();

            /* For the first move... */
            if (gameState.equals(GameState.START_GAME)) {
                
                var nextPlayerTurn = (cellState.equals(TTTCell.State.PLAYER_X)) ? GameState.PLAYER_O : GameState.PLAYER_X;
                updateGameState(nextPlayerTurn);
                
                return;
            }     
            
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

    };

    private void updateGameState(GameState newState) {
        gameState = newState;
        setText(gameState.getDescription());
    }
    
    public void refreshStateMessage() {
        setText(gameState.getDescription());
    }
    
    public VetoableCellStateChangeListener getVetoCellStateChangeListener() {
        return vetoCellStateChangeListener;
    }
    
    public TTTController() {
    }

    @Override
    public void onGameEnd(GameEndEvent evt) {
        updateGameState(evt.getEndingState());
    }
    
    @Override
    public void onGameRestart() {
        updateGameState(GameLogic.INITIAL_GAME_STATE);
    }
    
}
