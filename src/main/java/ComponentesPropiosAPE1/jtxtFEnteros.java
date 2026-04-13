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
public class jtxtFEnteros extends JTextField {

// ========================
    // CONSTRUCTOR
    // ========================
    public jtxtFEnteros() {
        
        super();
        // Valor inicial
        this.setText("0");
        
        // Escucha cuando pierde el foco
        this.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                alPerderFoco();
            }
        });
        
        // Escucha cada tecla presionada
        this.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                soloPermitirEnteros(evt);
            }
        });
    }

    // ========================
    // REGLA DE TECLADO
    // Solo deja escribir dígitos (0-9)
    // ========================
    private void soloPermitirEnteros(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        
        // Si NO es un dígito → ignora la tecla
        if (!Character.isDigit(c)) {
            evt.consume(); // bloquea la tecla
        }
    }

    // ========================
    // AL PERDER FOCO
    // Valida y pinta el borde
    // ========================
    private void alPerderFoco() {
        verificarObligatorio();
        formatearEntero();
    }

    // ========================
    // VERIFICAR SI ESTÁ VACÍO
    // Rojo = vacío/error, Gris = correcto
    // ========================
    public void verificarObligatorio() {
        if (this.getText().trim().isEmpty()) {
            this.setBorder(new LineBorder(Color.RED, 2));   // rojo = vacío!
            this.setToolTipText("⚠ Este campo es obligatorio"); // tooltip de error
        } else {
            this.setBorder(new LineBorder(Color.GREEN, 2)); // verde = correcto!
            this.setToolTipText("✓ Dato correcto");
        }
    }

    // ========================
    // FORMATEAR: si está vacío pone 0
    // ========================
    private void formatearEntero() {
        try {
            if (this.getText().trim().isEmpty()) {
                this.setText("0");
            } else {
                // Convierte y reconvierte para limpiar ceros extra (ej: "007" → "7")
                int num = Integer.parseInt(this.getText().trim());
                this.setText(String.valueOf(num));
            }
        } catch (NumberFormatException e) {
            this.setText("0"); // si algo falla, pone 0
        }
    }

    // ========================
    // MÉTODOS ÚTILES
    // Para obtener y poner valores desde tu JFrame
    // ========================
    public Integer getValor() {
        try {
            return Integer.parseInt(this.getText().trim());
        } catch (Exception e) {
            return 0;
        }
    }

    public void setValor(Integer valor) {
        this.setText(String.valueOf(valor));
    }
}
