/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ComponentesPropiosAPE1;

import java.awt.Color;
import static java.awt.PageAttributes.ColorType.COLOR;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 *
 * @author Lenovo LOQ
 */
public class jtxtFDecimal extends JTextField {

    
    public jtxtFDecimal() {
        super();
        
        this.setText("0.00");
        
        this.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                alPerderFoco();
            }
        });
        
        this.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                soloPermitirDecimales(evt);
            }
        });
    }

    private void soloPermitirDecimales(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        
        // Solo permite dígitos y el punto decimal
        if (!Character.isDigit(c) && c != '.') {
            evt.consume();
        }
        
        // Solo un punto decimal permitido
        if (c == '.' && this.getText().contains(".")) {
            evt.consume();
        }
    }

    private void alPerderFoco() {
        verificarObligatorio();
        formatearDecimal();
    }

    public void verificarObligatorio() {
        if (this.getText().trim().isEmpty()) {
            this.setBorder(new LineBorder(Color.RED, 2));
            this.setToolTipText("⚠ Este campo es obligatorio");
        } else {
            this.setBorder(new LineBorder(Color.GREEN, 2));
            this.setToolTipText("✓ Dato correcto");
        }
    }

    private void formatearDecimal() {
        try {
            if (this.getText().trim().isEmpty()) {
                this.setText("0.00");
            } else {
                double num = Double.parseDouble(this.getText());
                this.setText(String.format("%.2f", num)); // siempre 2 decimales
            }
        } catch (Exception e) {
            this.setText("0.00");
        }
    }

    public Double getValor() {
        try {
            return Double.parseDouble(this.getText().trim());
        } catch (Exception e) {
            return 0.0;
        }
    }

    public void setValor(Double valor) {
        this.setText(String.format("%.2f", valor));
    }
}
