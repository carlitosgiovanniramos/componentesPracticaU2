/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ComponentesPropiosAPE2;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Lenovo LOQ
 */
public class ColorCeldaRenderer extends JTextField implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        String texto = (value == null) ? "" : value.toString();
        this.setText(texto);
        this.setOpaque(true);

        try {
            int numero = Integer.parseInt(texto.trim());

            if (numero % 2 == 0) {
                // PAR → Verde
                this.setBackground(new Color(144, 238, 144));
            } else {
                // IMPAR → Rojo
                this.setBackground(new Color(255, 99, 99));
            }

        } catch (NumberFormatException e) {
            this.setBackground(Color.WHITE);
        }
        return this;
    }
}
