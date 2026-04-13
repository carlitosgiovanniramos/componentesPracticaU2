/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ComponentesPropiosAPE2;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Lenovo LOQ
 */
public class jtblColores extends JTable {

    public jtblColores() {
        // constructor vacío está bien
        this.setRowHeight(35);
    }

    // ================================
    // Cada vez que pongas el modelo
    // automáticamente aplica el pintor
    // ================================
    @Override
    public void setModel(javax.swing.table.TableModel model) {
        super.setModel(model); // primero carga el modelo

        // DESPUÉS aplica el pintor (ya hay columnas!)
        ColorCeldaRenderer pintor = new ColorCeldaRenderer();
        for (int i = 0; i < this.getColumnCount(); i++) {
            this.getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(pintor);
        }
    }

}
