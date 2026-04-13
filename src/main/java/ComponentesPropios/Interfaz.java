/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ComponentesPropios;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo LOQ
 */
public class Interfaz extends javax.swing.JFrame {

    /**
     * Creates new form Interfaz
     */
    DefaultTableModel modelo;
    DefaultListModel modeloLista;
    DefaultListModel modeloLista1;
    DefaultComboBoxModel modeloCombo;
    boolean activo = false;

    public Interfaz() {
        initComponents();
        cargarTabla();
        cargarLista();
        cargarListaAutomaticamente();
        cargarComboBox();
        selectValoresTabla();
        selectRowStudent();
    }

    public void cargarTabla() {

    }

    //Cargar Lista de forma ambigua sin Base de datos
    /*
    public void cargarFilas() {
        String filas[] = new String[5];
        String texto = jtxtNumero.getText();S
        try {
            int numero = Integer.parseInt(texto);
            if (numero % 2 == 0) {
                filas[0] = String.valueOf(numero);
            } else {
                filas[1] = String.valueOf(numero);
            }
        } catch (NumberFormatException e) {
            filas[2] = texto;
        }
        String valorLista = jListMio2.getSelectedValue();
        String valorCombo = String.valueOf(jComboBoxMio1.getSelectedItem());
        filas[3] = valorLista;
        filas[4] = valorCombo;
        modelo.addRow(filas);
    }
     */
    public void cargarComboBox() {
        String valoresCombo[] = {"1", "2", "test"};
        modeloCombo = new DefaultComboBoxModel(valoresCombo);
        jComboBoxMio1.setModel(modeloCombo);
    }

    public void cargarLista() {
        modeloLista = new DefaultListModel();
        jListMio1.setModel(modeloLista);
    }

    public void cargarListaBoton() {
        String texto = jtxtFLista.getText();
        jListMio1.agregarElemento(texto);
    }

    public void cargarListaAutomaticamente() {
        String valoresLista[] = {"1", "2", "3", "4"};
        modeloLista1 = new DefaultListModel();
        for (String valor : valoresLista) {
            modeloLista1.addElement(valor);
        }
        jListMio2.setModel(modeloLista1);
    }

    public void selectValoresTabla() {
        try {
            String[] columnas = {"Par", "Impar", "Cadena", "Lista", "ComboBox"};
            modelo = new DefaultTableModel(null, columnas);
            jtblValores.setModel(modelo);
            String[] filas = new String[5];
            Conexion cn = new Conexion();
            Connection cc = cn.Conectar();
            String sql = "SELECT * FROM valores";
            Statement psd = cc.createStatement();
            ResultSet rs = psd.executeQuery(sql);

            while (rs.next()) {
                filas[0] = rs.getString("par");
                filas[1] = rs.getString("impar");
                filas[2] = rs.getString("cadena");
                filas[3] = rs.getString("lista");
                filas[4] = rs.getString("combo");

                modelo.addRow(filas);
                jtblValores.setModel(modelo);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void insertValor() {

        String valorPar = "";
        String valorImpar = "";
        String valorCadena = "";

        String texto = jtxtNumero.getText();
        try {
            int numero = Integer.parseInt(texto);
            if (numero % 2 == 0) {
                valorPar = String.valueOf(numero);
            } else {
                valorImpar = String.valueOf(numero);
            }
        } catch (NumberFormatException ex) {
            valorCadena = texto;
        }

        String valorLista = jListMio2.getSelectedValue();
        String valorCombo = String.valueOf(jComboBoxMio1.getSelectedItem());

        if (valorLista == null) {
            valorLista = "";
        }
        if (valorCombo.equals("null")) {
            valorCombo = "";
        }

        String sql = "INSERT INTO valores VALUES(?,?,?,?,?)";

        try {
            Conexion cn = new Conexion();
            Connection cc = cn.Conectar();
            PreparedStatement psd = cc.prepareStatement(sql);

            psd.setString(1, valorPar);
            psd.setString(2, valorImpar);
            psd.setString(3, valorCadena);
            psd.setString(4, valorLista);
            psd.setString(5, valorCombo);

            int respuesta = psd.executeUpdate();

            if (respuesta > 0) {
                JOptionPane.showMessageDialog(null, "Se insertaron los valores correctamente");
                selectValoresTabla();
            }
            psd.close();
            cc.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar en BD: " + ex.getMessage());
        }
    }

// ... dentro de tu clase ...
    public void selectRowStudent() {
        jtblValores.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // El !e.getValueIsAdjusting() evita que el evento se dispare dos veces seguidas por el clic
                if (!e.getValueIsAdjusting() && jtblValores.getSelectedRow() != -1) {

                    int fila = jtblValores.getSelectedRow();

                    // 1. RECUPERAR EL NÚMERO / TEXTO
                    // Extraemos los valores de las columnas 0, 1 y 2 asumiendo que evitamos errores de NullPointer
                    String valPar = jtblValores.getValueAt(fila, 0) != null ? jtblValores.getValueAt(fila, 0).toString() : "";
                    String valImpar = jtblValores.getValueAt(fila, 1) != null ? jtblValores.getValueAt(fila, 1).toString() : "";
                    String valCadena = jtblValores.getValueAt(fila, 2) != null ? jtblValores.getValueAt(fila, 2).toString() : "";

                    // Evaluamos cuál de los tres tiene texto y lo devolvemos al jtxtNumero
                    if (!valPar.isEmpty()) {
                        jtxtNumero.setText(valPar);
                    } else if (!valImpar.isEmpty()) {
                        jtxtNumero.setText(valImpar);
                    } else {
                        jtxtNumero.setText(valCadena);
                    }

                    // 2. RECUPERAR EL VALOR DE LA LISTA (Columna 3)
                    String valorLista = jtblValores.getValueAt(fila, 3) != null ? jtblValores.getValueAt(fila, 3).toString() : "";
                    // setSelectedValue busca el texto en tu JList y lo marca como seleccionado. 
                    // El "true" le dice a la lista que haga scroll automático si el valor está oculto muy abajo.
                    jListMio2.setSelectedValue(valorLista, true);

                    // 3. RECUPERAR EL VALOR DEL COMBOBOX (Columna 4)
                    String valorCombo = jtblValores.getValueAt(fila, 4) != null ? jtblValores.getValueAt(fila, 4).toString() : "";
                    // setSelectedItem busca el texto en el JComboBox y lo selecciona
                    jComboBoxMio1.setSelectedItem(valorCombo);

                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jtblValores = new ComponentesPropios.jTableMio();
        jtxtNumero = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jtxtFLista = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListMio1 = new ComponentesPropios.jListMio<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListMio2 = new ComponentesPropios.jListMio<>();
        jComboBoxMio1 = new ComponentesPropios.jComboBoxMio();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jtblValores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jtblValores);

        jtxtNumero.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxtNumeroFocusLost(evt);
            }
        });

        jButton1.setText("Cargar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cargar Lista");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jListMio1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jListMio1);

        jListMio2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jListMio2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListMio2MouseClicked(evt);
            }
        });
        jListMio2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jListMio2KeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(jListMio2);

        jComboBoxMio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMio1ActionPerformed(evt);
            }
        });

        jButton3.setText("Conectar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Cambiar Color");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jtxtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2)
                            .addComponent(jScrollPane1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtxtFLista, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxMio1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(230, 230, 230))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jtxtFLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addComponent(jScrollPane1))
                            .addGap(18, 18, 18)
                            .addComponent(jButton2)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxMio1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addContainerGap(82, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtNumeroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtNumeroFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtNumeroFocusLost

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        insertValor();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        cargarListaBoton();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jListMio2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListMio2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jListMio2MouseClicked

    private void jListMio2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jListMio2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jListMio2KeyPressed

    private void jComboBoxMio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMio1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxMio1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Conexion conexion = new Conexion();
        conexion.Conectar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:

        if (activo == false) {
            jButton4.setBackground(Color.GREEN);
            activo = true;
        } else {
            jButton4.setBackground(Color.red);
            activo = false;
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private ComponentesPropios.jComboBoxMio jComboBoxMio1;
    private ComponentesPropios.jListMio<String> jListMio1;
    private ComponentesPropios.jListMio<String> jListMio2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private ComponentesPropios.jTableMio jtblValores;
    private javax.swing.JTextField jtxtFLista;
    private javax.swing.JTextField jtxtNumero;
    // End of variables declaration//GEN-END:variables
}
