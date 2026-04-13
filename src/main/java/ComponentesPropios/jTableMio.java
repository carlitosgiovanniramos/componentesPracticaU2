/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ComponentesPropios;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Lenovo LOQ
 */
public class jTableMio extends JTable {

    public jTableMio() {
        super();
        configurarTamañoCeldas();
        configurarRenderer();
    }

    private void configurarTamañoCeldas() {
        this.setRowHeight(20);
    }

    private void configurarRenderer() {
        this.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

            @Override
            public java.awt.Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {

                java.awt.Component celda = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                if (isSelected) {
                    return celda;
                }

                String texto = (value == null) ? "" : value.toString();

                celda.setBackground(java.awt.Color.WHITE);

                try {
                    int numero = Integer.parseInt(texto);

                    if (numero % 2 == 0) {
                        celda.setBackground(java.awt.Color.GREEN);
                    } else {
                        celda.setBackground(java.awt.Color.RED);
                    }

                } catch (NumberFormatException e) {
                    celda.setBackground(java.awt.Color.WHITE);
                }

                return celda;
            }
        });
    }

    // ===============================
    // ✏️ 2. CONTROL DE EDICIÓN
    // ===============================
    private void configurarEdicion() {
        // Solo columna 2 editable
        this.setDefaultEditor(Object.class, new DefaultCellEditor(new JTextField()) {
            @Override
            public boolean isCellEditable(java.util.EventObject e) {
                int col = getSelectedColumn();
                return col == 1;
            }
        });
    }

    // ===============================
    // 🔽 3. ORDENAMIENTO
    // ===============================
    private void configurarOrdenamiento() {
        this.setAutoCreateRowSorter(true);
    }

    // ===============================
    // ⚡ 4. EVENTOS (cuando cambian datos)
    // ===============================
    private void configurarEventos() {

        this.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                System.out.println("Dato modificado en la tabla");
            }
        });
    }

    // ===============================
    // 🚫 5. BLOQUEAR EDICIÓN POR COLUMNA
    // ============================== 
    @Override
    public boolean isCellEditable(int row, int column) {
        return false; // columna 0 no editable
    }
}
