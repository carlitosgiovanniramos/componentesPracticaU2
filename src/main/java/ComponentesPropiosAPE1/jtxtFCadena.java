/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ComponentesPropiosAPE1;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 *
 * @author Lenovo LOQ
 */
public class jtxtFCadena extends JTextField {

    public jtxtFCadena() {
        super();

        this.setText("");

        this.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent event) {
                alPerderFoco();
            }
        });
        this.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent event) {
                soloPermitirLetras(event);
            }
        });

    }

    private void soloPermitirLetras(java.awt.event.KeyEvent event) {
        char character = event.getKeyChar();

        if (!Character.isLetter(character) && character != ' ') {
            event.consume();
        }
    }

    private void alPerderFoco() {
        verificarObligatorio();
    }

    private void verificarObligatorio() {
        if (this.getText().trim().isEmpty()) {
            setBorder(new LineBorder(Color.RED, 2));
            setToolTipText("⚠ Este campo es obligatorio"); // tooltip de error
        } else {
            this.setBorder(new LineBorder(Color.GREEN, 2)); // verde = correcto!
            this.setToolTipText("✓ Dato correcto");
        }
    }

    public String getValor() {
        return this.getText().trim();
    }

    public void setValor(String valor) {
        this.setText(valor);
    }

}
