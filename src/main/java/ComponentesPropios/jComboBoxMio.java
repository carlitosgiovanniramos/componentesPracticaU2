/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ComponentesPropios;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Lenovo LOQ
 */
public class jComboBoxMio extends JComboBox {

    public jComboBoxMio() {
        super();
        configurarRenderer();
    }

    private void configurarRenderer() {
        this.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component celda = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (isSelected) {
                    return celda;
                }
                String texto = (value == null) ? "" : value.toString();
                celda.setBackground(Color.WHITE);

                try {
                    int num = Integer.parseInt(texto);

                    if (num % 2 == 0) {
                        celda.setBackground(Color.GREEN);
                    } else {
                        celda.setBackground(Color.RED);
                    }

                } catch (Exception e) {
                    celda.setBackground(Color.WHITE);
                }
                return celda;
            }
        });
    }
}
