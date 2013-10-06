package main;


import javax.swing.table.DefaultTableModel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Teddy Reiss
 */
public class RobotTableModel extends DefaultTableModel {
    private final boolean[] editable;
    /**
     * 
     * @param editableCols needs to be the same length as the number of columns
     */
    public RobotTableModel(boolean[] editableCols, Object[] columnNames, int rowCount) {
        super(columnNames, rowCount);
        editable = editableCols;
    }
    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
    @Override
    public boolean isCellEditable(int row, int col) {
        if (editable[col]) {
            return true;
        } else {
            return false;
        }
    }
}
