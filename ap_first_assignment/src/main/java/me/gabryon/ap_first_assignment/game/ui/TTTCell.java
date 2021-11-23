package me.gabryon.ap_first_assignment.game.ui;

import me.gabryon.ap_first_assignment.game.ui.listeners.VetoableCellStateChangeListener;
import me.gabryon.ap_first_assignment.game.ui.listeners.CellStateChangeListener;

import java.awt.Color;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeSupport;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import me.gabryon.ap_first_assignment.game.ui.listeners.GameEndEvent;
import me.gabryon.ap_first_assignment.game.ui.listeners.GameEndListener;
import me.gabryon.ap_first_assignment.game.ui.listeners.GameRestartListener;

/**
 * Cell containing two buttons (X/O) representing the tiles in TicTacToe game.
 */
public class TTTCell extends javax.swing.JPanel implements GameEndListener, GameRestartListener {

    /**
     * *
     * Represent the Cell state: X pressed, O pressed, Nothing pressed, Win
     * state. Each state has its own color to be set when the cell state change.
     */
    public static enum State {

        /* Default JPanel background color */
        INITIAL(UIManager.getColor("Panel.background")),
        PLAYER_X(Color.decode("#003049")),
        PLAYER_O(Color.decode("#d62828")),
        WON(Color.decode("#90be6d"));

        private final Color stateColor;

        private State(Color color) {
            this.stateColor = color;
        }

    }

    private State cellState = State.INITIAL;

    private final PropertyChangeSupport cellStateSupport = new PropertyChangeSupport(this);
    private final VetoableChangeSupport cellStateVetos = new VetoableChangeSupport(this);

    /**
     * Creates new form TTTCell
     */
    public TTTCell() {
        initComponents();
    }

    public void addCellStateChangeListener(CellStateChangeListener listener) {
        cellStateSupport.addPropertyChangeListener("cellState", listener);
    }

    public void addCellStateChangeListener(VetoableCellStateChangeListener listener) {
        cellStateVetos.addVetoableChangeListener("cellState", listener);
    }

    public void removeCellStateChangeListener(CellStateChangeListener listener) {
        cellStateSupport.removePropertyChangeListener("cellState", listener);
    }

    public void removeCellStateChangeListener(VetoableCellStateChangeListener listener) {
        cellStateVetos.removeVetoableChangeListener("cellState", listener);
    }

    public State getCellState() {
        return cellState;
    }

    public void setCellState(State newState) {

        var oldState = this.cellState;

        try {

            cellStateVetos.fireVetoableChange("cellState", oldState, newState);

            if (newState.equals(State.PLAYER_X)) {
                /* Change the label for O button to an empty string */
                btnPlayerO.setText("");
            } else if (newState.equals(State.PLAYER_O)) {
                /* Change the label for X button to an empty string */
                btnPlayerX.setText("");
            }

            toggleButtons(false);

            setBackground(newState.stateColor);
            cellState = newState;

            cellStateSupport.firePropertyChange("cellState", oldState, newState);
        } catch (PropertyVetoException e) {
            // Someone is violating the game's logic...
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void toggleButtons(boolean toggle) {
        /* Disable both buttons */
        btnPlayerX.setEnabled(toggle);
        btnPlayerO.setEnabled(toggle);
    }

    public void onGameReset() {
        cellState = TTTCell.State.INITIAL;
        btnPlayerX.setEnabled(true);
        btnPlayerO.setEnabled(true);
        setBackground(cellState.stateColor);
    }

    @Override
    public void onGameEnd(GameEndEvent evt) {

        if (evt.getCells().isPresent()) {

            /* Check if the current cell is contained inside the winning cells */
            var idx = evt.getCells().get().indexOf(this);
            if (idx >= 0) {
                /* .. then mark the new cell state */
                cellState = TTTCell.State.WON;
                setBackground(cellState.stateColor);
            } else {
                toggleButtons(false);
                setBackground(TTTCell.State.INITIAL.stateColor);
            }

        }

    }

    @Override
    public void onGameRestart() {
        cellState = TTTCell.State.INITIAL;
        btnPlayerO.setText("O");
        btnPlayerX.setText("X");
        setBackground(cellState.stateColor);
        toggleButtons(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnPlayerO = new javax.swing.JButton();
        btnPlayerX = new javax.swing.JButton();

        btnPlayerO.setText("O");
        btnPlayerO.setBorder(null);
        btnPlayerO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayerOActionPerformed(evt);
            }
        });

        btnPlayerX.setText("X");
        btnPlayerX.setBorder(null);
        btnPlayerX.setBorderPainted(false);
        btnPlayerX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayerXActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPlayerO, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                    .addComponent(btnPlayerX, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPlayerX, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPlayerO, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnPlayerXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayerXActionPerformed
        setCellState(State.PLAYER_X);
    }//GEN-LAST:event_btnPlayerXActionPerformed

    private void btnPlayerOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayerOActionPerformed
        setCellState(State.PLAYER_O);
    }//GEN-LAST:event_btnPlayerOActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPlayerO;
    private javax.swing.JButton btnPlayerX;
    // End of variables declaration//GEN-END:variables
}
