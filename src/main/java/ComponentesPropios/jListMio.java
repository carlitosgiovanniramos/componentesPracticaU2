/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ComponentesPropios;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo LOQ
 */
public class jListMio<E> extends JList<E> {

    public jListMio() {
        super();
        configurarRenderer();
    }

    private void configurarRenderer() {

        this.setCellRenderer(new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {

                Component c = super.getListCellRendererComponent(
                        list, value, index, isSelected, cellHasFocus);

                if (isSelected) {
                    return c;
                }

                String texto = (value == null) ? "" : value.toString();

                c.setBackground(Color.WHITE);

                try {
                    int num = Integer.parseInt(texto);

                    if (num % 2 == 0) {
                        c.setBackground(Color.GREEN);
                    } else {
                        c.setBackground(Color.RED);
                    }

                } catch (Exception e) {
                    c.setBackground(Color.WHITE);
                }

                return c;
            }
        });
    }

    // =========================
    // ➕ AGREGAR SIN DUPLICADOS
    // =========================
    public void agregarElemento(String texto) {
        DefaultListModel modelo = (DefaultListModel) this.getModel();

        if (!modelo.contains(texto)) {
            modelo.addElement(texto);
        } else {
            JOptionPane.showMessageDialog(null, "Elemento repetido");
        }
    }

}
