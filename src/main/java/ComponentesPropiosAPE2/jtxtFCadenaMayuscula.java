/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ComponentesPropiosAPE2;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 *
 * @author Lenovo LOQ
 */
public class jtxtFCadenaMayuscula extends JTextField {

    public jtxtFCadenaMayuscula() {

        // Escucha cuando pierda el foco
        this.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                alPerderFoco();
            }
        });

        // Escucha cada tecla
        this.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                soloLetrasMayusculas(evt);
            }
        });
    }

    public void soloLetrasMayusculas(java.awt.event.KeyEvent event) {
        char character = event.getKeyChar();

        if (!Character.isLetter(character) && character != ' ') {
            event.consume();
            return;
        }
        event.setKeyChar(Character.toUpperCase(character));
    }

    // ================================
    // AL PERDER FOCO: valida
    // ================================
    private void alPerderFoco() {
        verificarObligatorio();
    }

    // ================================
    // PINTA: rojo=vacío, verde=correcto
    // ================================
    public void verificarObligatorio() {
        if (this.getText().trim().isEmpty()) {
            this.setBorder(new LineBorder(Color.RED, 2));
            this.setToolTipText("⚠ Campo obligatorio");
        } else {
            this.setBorder(new LineBorder(Color.GREEN, 2));
            this.setToolTipText("✓ Dato correcto");
        }
    }

    // ================================
    // MÉTODOS para usar en tu JFrame
    // ================================
    public String getValor() {
        return this.getText().trim();
    }

    public void setValor(String valor) {
        this.setText(valor.toUpperCase()); // siempre guarda en mayúsculas
    }
}
