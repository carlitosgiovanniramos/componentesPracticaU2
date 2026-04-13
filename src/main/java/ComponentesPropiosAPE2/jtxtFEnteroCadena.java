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
public class jtxtFEnteroCadena extends JTextField {

    public jtxtFEnteroCadena() {
        super();
        this.setText("");

        // Escucha cuando pierde el foco
        this.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                validarCampo();
            }
        });

        // Escucha cada tecla presionada
        this.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                soloPermitirAlfanumerico(evt);
            }
        });
    }

    // ========================
    // REGLA DE TECLADO
    // Solo deja escribir letras y números
    // ========================
    private void soloPermitirAlfanumerico(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();

        // Character.isLetterOrDigit permite A-Z, a-z y 0-9
        // También permitimos la tecla borrar (backspace)
        if (!Character.isLetterOrDigit(c) && c != '\b') {
            evt.consume(); // Bloquea cualquier otro símbolo (puntos, comas, espacios)
        }
    }

    // ========================
    // VALIDACIÓN AL PERDER FOCO
    // ========================
    private void validarCampo() {
        String texto = this.getText().trim();

        if (texto.isEmpty()) {
            // ERROR: Está vacío
            this.setBorder(new LineBorder(Color.RED, 2));
            this.setToolTipText("⚠ Este campo no puede estar vacío");
        } else {
            // ÉXITO: Tiene contenido alfanumérico
            this.setBorder(new LineBorder(Color.GREEN, 2));
            this.setToolTipText("✓ Dato alfanumérico válido");

            // Opcional: Si el texto es puramente numérico, podrías quitar ceros a la izquierda
            // pero si es "A001", se debe dejar tal cual.
            limpiarCerosSiEsSoloNumero(texto);
        }
    }

    private void limpiarCerosSiEsSoloNumero(String texto) {
        // Solo si el contenido son puramente dígitos, lo formateamos
        if (texto.matches("\\d+")) {
            long valor = Long.parseLong(texto);
            this.setText(String.valueOf(valor));
        }
    }

}
