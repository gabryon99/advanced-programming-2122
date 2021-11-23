package me.gabryon.ap_first_assignment.game.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.swing.event.EventListenerList;
import me.gabryon.ap_first_assignment.game.GameState;
import me.gabryon.ap_first_assignment.game.ui.listeners.CellStateChangeListener;
import me.gabryon.ap_first_assignment.game.ui.listeners.GameEndEvent;
import me.gabryon.ap_first_assignment.game.ui.listeners.GameEndListener;
import me.gabryon.ap_first_assignment.game.ui.listeners.GameRestartListener;

/**
 *
 * @author gabryon
 */
public class TTTBoard extends javax.swing.JFrame {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TTTBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TTTBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TTTBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TTTBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            
            var board = new TTTBoard();
            
            board.initializeControllerAndBoard();
            board.setVisible(true);
        });
    }
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cellContainer = new javax.swing.JPanel();
        cell00 = new me.gabryon.ap_first_assignment.game.ui.TTTCell();
        cell01 = new me.gabryon.ap_first_assignment.game.ui.TTTCell();
        cell02 = new me.gabryon.ap_first_assignment.game.ui.TTTCell();
        cell10 = new me.gabryon.ap_first_assignment.game.ui.TTTCell();
        cell11 = new me.gabryon.ap_first_assignment.game.ui.TTTCell();
        cell12 = new me.gabryon.ap_first_assignment.game.ui.TTTCell();
        cell20 = new me.gabryon.ap_first_assignment.game.ui.TTTCell();
        cell21 = new me.gabryon.ap_first_assignment.game.ui.TTTCell();
        cell22 = new me.gabryon.ap_first_assignment.game.ui.TTTCell();
        btnRestart = new javax.swing.JButton();
        tttController = new me.gabryon.ap_first_assignment.game.ui.TTTController();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TicTacToe");
        setResizable(false);

        cellContainer.setPreferredSize(new java.awt.Dimension(400, 400));

        javax.swing.GroupLayout cellContainerLayout = new javax.swing.GroupLayout(cellContainer);
        cellContainer.setLayout(cellContainerLayout);
        cellContainerLayout.setHorizontalGroup(
            cellContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cellContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cellContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cellContainerLayout.createSequentialGroup()
                        .addComponent(cell00, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cell01, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cell02, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cellContainerLayout.createSequentialGroup()
                        .addComponent(cell10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cell11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cell12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cellContainerLayout.createSequentialGroup()
                        .addComponent(cell20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cell21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cell22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        cellContainerLayout.setVerticalGroup(
            cellContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cellContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cellContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cell02, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cell01, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cell00, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(cellContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cell12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cell11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cell10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(cellContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cell22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cell21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cell20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnRestart.setBackground(new java.awt.Color(255, 51, 51));
        btnRestart.setForeground(new java.awt.Color(0, 0, 0));
        btnRestart.setText("Restart");
        btnRestart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestartActionPerformed(evt);
            }
        });

        tttController.setText("START GAME");
        tttController.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cellContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(tttController, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                        .addComponent(btnRestart, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cellContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRestart, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(tttController, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8))
        );

        tttController.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRestartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestartActionPerformed
        Object[] listeners = gameRestartEventListenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2)  {
            if (listeners[i] == GameRestartListener.class) {
                ((GameRestartListener) listeners[i + 1]).onGameRestart();
            }
        }
    }//GEN-LAST:event_btnRestartActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRestart;
    private me.gabryon.ap_first_assignment.game.ui.TTTCell cell00;
    private me.gabryon.ap_first_assignment.game.ui.TTTCell cell01;
    private me.gabryon.ap_first_assignment.game.ui.TTTCell cell02;
    private me.gabryon.ap_first_assignment.game.ui.TTTCell cell10;
    private me.gabryon.ap_first_assignment.game.ui.TTTCell cell11;
    private me.gabryon.ap_first_assignment.game.ui.TTTCell cell12;
    private me.gabryon.ap_first_assignment.game.ui.TTTCell cell20;
    private me.gabryon.ap_first_assignment.game.ui.TTTCell cell21;
    private me.gabryon.ap_first_assignment.game.ui.TTTCell cell22;
    private javax.swing.JPanel cellContainer;
    private me.gabryon.ap_first_assignment.game.ui.TTTController tttController;
    // End of variables declaration//GEN-END:variables

    private final EventListenerList gameEndEventListenerList = new EventListenerList();
    private final EventListenerList gameRestartEventListenerList = new EventListenerList();

    /***
     * The listener will invoke the ending game conditions when a cell state changes!
     */
    private final CellStateChangeListener cellStateChangeListener = (evt) -> {
        checkEndGame();
    };
    
    private final ArrayList<List<TTTCell>> winningConditions;
    
    /**
     * Creates new form TTTBoard
     */
    public TTTBoard() {
        initComponents();
        
        winningConditions = new ArrayList<>() {{

            add(Arrays.asList(cell00, cell01, cell02)); /* First row */
            add(Arrays.asList(cell10, cell11, cell12)); /* Second row */
            add(Arrays.asList(cell20, cell21, cell22)); /* Third row */

            add(Arrays.asList(cell00, cell10, cell20)); /* First column */
            add(Arrays.asList(cell01, cell11, cell21)); /* Second column */
            add(Arrays.asList(cell02, cell12, cell22)); /* Third column */

            add(Arrays.asList(cell00, cell11, cell22)); /* First diagonal */
            add(Arrays.asList(cell12, cell11, cell20)); /* Second diagonal */

        }};
    }

    public void addGameEndListener(GameEndListener listener) {
        gameEndEventListenerList.add(GameEndListener.class, listener);
    }
    
    public void removeGameEndListener(GameEndListener listener) {
        gameEndEventListenerList.remove(GameEndListener.class, listener);
    }
    
    public void addGameRestartListener(GameRestartListener listener) {
        gameRestartEventListenerList.add(GameRestartListener.class, listener);
    }
    
    public void removeGameRestartListener(GameRestartListener listener) {
        gameRestartEventListenerList.remove(GameRestartListener.class, listener);
    }
    
    
    private void checkEndGame() {
        
        /* 
            Using Stream API find the first winning condition that match 
            the game state.
         */
        var winningCondition = winningConditions.stream()
                .filter((cells) -> {
                    return checkLine(cells.get(0), cells.get(1), cells.get(2));
                })
                .findFirst();
        
        /* If a winning condition has been found... */
        if (winningCondition.isPresent()) {
            
            /* ...then we have a winner! */
            var unboxed = winningCondition.get();
            /* 
                The winner is contained in a cell state, so if the cell state is
                equals to PLAYER_X then 'X' won, otherwise 'O' won.
            */
            var winningPlayerState = (unboxed.get(0).getCellState().equals(TTTCell.State.PLAYER_X)) 
                    ? GameState.X_WON : GameState.O_WON;
            
            
            var gameEndEvent = new GameEndEvent(winningPlayerState, Optional.of(unboxed));
            fireGameEndEvent(gameEndEvent);
            
            return;
        }
        
        /* Otherwise, let's check if the game is in a draw state by checking all the cells */
        
        var drawCondition = winningConditions.stream().flatMap(Collection::stream).allMatch((cell) -> {
            return !cell.getCellState().equals(TTTCell.State.INITIAL);
        });
        
        /* If all the cell state are different from the INITIAL one... */
        if (drawCondition) {
            /* ... then the game ended with a DRAW! */
            var gameEndEvent = new GameEndEvent(GameState.DRAW, Optional.empty());
            fireGameEndEvent(gameEndEvent);
        }
        
    }
    
    private void fireGameEndEvent(GameEndEvent evt) {
      
        Object[] listeners = gameEndEventListenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2)  {
            if (listeners[i] == GameEndListener.class) {
                ((GameEndListener) listeners[i + 1]).onGameEnd(evt);
            }
        }
         
     }
    
    private boolean checkLine(TTTCell cell0, TTTCell cell1, TTTCell cell2) {
        
        if (cell0.getCellState().equals(TTTCell.State.INITIAL)) return false;
        if (cell1.getCellState().equals(TTTCell.State.INITIAL)) return false;
        if (cell2.getCellState().equals(TTTCell.State.INITIAL)) return false;        
        
        return (cell0.getCellState().equals(cell1.getCellState()) && cell1.getCellState().equals(cell2.getCellState()));
    }
    
    private void initializeControllerAndBoard() {
        
        /**
         * As requested by the specification, all the cells must be
         * register themselves to the VetoableChangeListener of TTTController.
         */
        var vetoChangeListenerController = tttController.getVetoCellStateChangeListener();
        cell00.addCellStateChangeListener(vetoChangeListenerController);
        cell01.addCellStateChangeListener(vetoChangeListenerController);
        cell02.addCellStateChangeListener(vetoChangeListenerController);

        cell10.addCellStateChangeListener(vetoChangeListenerController);
        cell11.addCellStateChangeListener(vetoChangeListenerController);
        cell12.addCellStateChangeListener(vetoChangeListenerController);
        
        cell20.addCellStateChangeListener(vetoChangeListenerController);
        cell21.addCellStateChangeListener(vetoChangeListenerController);
        cell22.addCellStateChangeListener(vetoChangeListenerController);
        
        
        cell00.addCellStateChangeListener(this.cellStateChangeListener);
        cell01.addCellStateChangeListener(this.cellStateChangeListener);
        cell02.addCellStateChangeListener(this.cellStateChangeListener);

        cell10.addCellStateChangeListener(this.cellStateChangeListener);
        cell11.addCellStateChangeListener(this.cellStateChangeListener);
        cell12.addCellStateChangeListener(this.cellStateChangeListener);

        cell20.addCellStateChangeListener(this.cellStateChangeListener);
        cell21.addCellStateChangeListener(this.cellStateChangeListener);
        cell22.addCellStateChangeListener(this.cellStateChangeListener);
        
        addGameEndListener(cell00); addGameEndListener(cell01); addGameEndListener(cell02);
        addGameEndListener(cell10); addGameEndListener(cell11); addGameEndListener(cell12);
        addGameEndListener(cell20); addGameEndListener(cell21); addGameEndListener(cell22);
        
        addGameEndListener(tttController);
        
        addGameRestartListener(cell00); addGameRestartListener(cell01); addGameRestartListener(cell02);
        addGameRestartListener(cell10); addGameRestartListener(cell11); addGameRestartListener(cell12);
        addGameRestartListener(cell20); addGameRestartListener(cell21); addGameRestartListener(cell22);
        
        addGameRestartListener(tttController);
    }
    
    
}