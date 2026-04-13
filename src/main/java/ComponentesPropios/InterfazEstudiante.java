/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ComponentesPropios;

import com.mysql.cj.xdevapi.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo LOQ
 */
public class InterfazEstudiante extends javax.swing.JFrame {

    /**
     * Creates new form InterfazEstudiante
     */
    DefaultTableModel modeloTabla;

    public InterfazEstudiante() {
        initComponents();
        consumirSelectDAO();
        consumirSelectDAO();
        selectRowStudent();
    }

    //SELECT OPCIONAL
    /*
    public void selectEstudent() {
        try {
            String[] columnas = {"Cedula", "Nombre", "Apellido", "Direccion", "Telefono"};
            String[] filas = new String[5];
            modeloEstudiantes = new DefaultTableModel(null, columnas);
            Conexion cn = new Conexion();
            Connection cc = cn.conectar();
            String sql = "SELECT * FROM estudiantes";
            Statement psd = cc.createStatement();
            ResultSet rs = psd.executeQuery(sql);

            while (rs.next()) {
                filas[0] = rs.getString("estCedula");
                filas[1] = rs.getString("estNombre");
                filas[2] = rs.getString("estApellido");
                filas[3] = rs.getString("estDireccion");
                filas[4] = rs.getString("estTelefono");

                modeloEstudiantes.addRow(filas);
                jtblEstudiantes.setModel(modeloEstudiantes);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
     */
    //SELECT DAO
    public void consumirSelectDAO() {
        try {
            DAO dao = new DAO();
            List<Estudiante> listaEstudiantes = dao.selectStudentDAO();
            String[] columnas = {"Cedula", "Nombre", "Apellido", "Direccion", "Telefono"};
            String[] filas = new String[5];
            modeloTabla = new DefaultTableModel(null, columnas);
            for (Estudiante estudiante : listaEstudiantes) {
                filas[0] = estudiante.getEstCedula();
                filas[1] = estudiante.getEstNombre();
                filas[2] = estudiante.getEstApellido();
                filas[3] = estudiante.getEstDireccion();
                filas[4] = estudiante.getEstTelefono();
                modeloTabla.addRow(filas);
            }
            jTableMio1.setModel(modeloTabla);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /* INSERT OPCIONAL
    public void insertStudent() {
        try {
            Conexion cn = new Conexion();
            Connection cc = cn.Conectar();
            String sql = "INSERT INTO estudiantes VALUES(?,?,?,?,?)";
            PreparedStatement psd = cc.prepareStatement(sql);
            psd.setString(1, jtxtFCedula.getText());
            psd.setString(2, jtxtFNombre.getText());
            psd.setString(3, jtxtFApellido.getText());
            psd.setString(4, jtxtFDireccion.getText());
            psd.setString(5, jtxtFTelefono.getText());

            int respuesta = psd.executeUpdate();
            if (respuesta > 0) {
                JOptionPane.showMessageDialog(null, "Se inserto un Estudiante");
                consumirSelectDAO();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }*/
    //INSERT DAO
    public void consumirInsertarDAO() {
        Estudiante estudiante = new Estudiante(jtxtFCedula.getText(), jtxtFNombre.getText(), jtxtFApellido.getText(), jtxtFDireccion.getText(), jtxtFTelefono.getText());

        DAO dao = new DAO();
        dao.insertStudentDAO(estudiante);
        consumirSelectDAO();
    }

    //UPDATE OPCIONAL
    /*
    public void updateStudent() {
        try {
            Conexion cn = new Conexion();
            Connection cc = cn.Conectar();
            String sql = "UPDATE estudiantes SET estNombre=?, estApellido=?, estDireccion=?, estTelefono=? WHERE estCedula=?";
            PreparedStatement psd = cc.prepareStatement(sql);

            psd.setString(1, jtxtFNombre.getText());
            psd.setString(2, jtxtFApellido.getText());
            psd.setString(3, jtxtFDireccion.getText());
            psd.setString(4, jtxtFTelefono.getText());
            psd.setString(5, jtxtFCedula.getText());

            int opc = psd.executeUpdate();
            if (opc > 0) {
                JOptionPane.showMessageDialog(null, "Se modifico un Estudiante");
                consumirSelectDAO();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }*/
    
    //UPDATE DAO
    public void consumirUpdateDAO() {
        Estudiante estudiante = new Estudiante(jtxtFCedula.getText(), jtxtFNombre.getText(), jtxtFApellido.getText(), jtxtFDireccion.getText(), jtxtFTelefono.getText());
        
        DAO dao = new DAO();
        dao.updateStudent(estudiante);
        consumirSelectDAO();
    }
    
    //SELECCIONAR ELEMENTOS EN LA TABLA
    public void selectRowStudent() {
        jTableMio1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (jTableMio1.getSelectedRow() != -1) {
                    int fila = jTableMio1.getSelectedRow();
                    jtxtFCedula.setText(jTableMio1.getValueAt(fila, 0).toString());
                    jtxtFNombre.setText(jTableMio1.getValueAt(fila, 1).toString());
                    jtxtFApellido.setText(jTableMio1.getValueAt(fila, 2).toString());
                    jtxtFDireccion.setText(jTableMio1.getValueAt(fila, 3).toString());
                    jtxtFTelefono.setText(jTableMio1.getValueAt(fila, 4).toString());
                }
            }
        });
    }
    
    //DELETE OPCIONAL
    /*
    public void deleteStudent() {
        try {
            Conexion cn = new Conexion();
            Connection cc = cn.Conectar();
            String sql = "DELETE FROM estudiantes WHERE estCedula=?";
            PreparedStatement psd = cc.prepareStatement(sql);
            
            psd.setString(1, jtxtFCedula.getText());
            
            int resultado = psd.executeUpdate();
            if (resultado > 0) {
            JOptionPane.showMessageDialog(null, "Se elimino Estudiante");
            consumirSelectDAO();
            }            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }*/
    
    //DELETE DAO
    public void consumirDeleteDAO() {
        try {
            if (JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar Estudiante?", "Eliminar", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
                DAO dao = new DAO();
                Estudiante estudiante = new Estudiante();
                estudiante.setEstCedula(jtxtFCedula.getText());
                
                if(dao.deleteStudentDAO(estudiante)) {
                    JOptionPane.showMessageDialog(null, "Se elimino Estudiante");
                    consumirSelectDAO();
                }
            }
        } catch (Exception e) {
        }
        
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMio1 = new ComponentesPropios.jTableMio();
        jPanel1 = new javax.swing.JPanel();
        jtxtFCedula = new javax.swing.JTextField();
        jtxtFNombre = new javax.swing.JTextField();
        jtxtFDireccion = new javax.swing.JTextField();
        jtxtFApellido = new javax.swing.JTextField();
        jtxtFTelefono = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jbtnNuevo = new javax.swing.JButton();
        jbtnEliminar = new javax.swing.JButton();
        jbtnCancelar = new javax.swing.JButton();
        jbtnGuardar = new javax.swing.JButton();
        jbtnEditar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTableMio1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTableMio1);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Cedula");

        jLabel2.setText("Nombre");

        jLabel3.setText("Apellido");

        jLabel4.setText("Direccion");

        jLabel5.setText("Telefono");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jtxtFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jtxtFApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jtxtFDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jtxtFTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jtxtFCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtFCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtFApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtFDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtFTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(20, 20, 20))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jbtnNuevo.setText("Nuevo");

        jbtnEliminar.setText("Eliminar");
        jbtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEliminarActionPerformed(evt);
            }
        });

        jbtnCancelar.setText("Cancelar");

        jbtnGuardar.setText("Guardar");
        jbtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnGuardarActionPerformed(evt);
            }
        });

        jbtnEditar.setText("Editar");
        jbtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnGuardar)
                    .addComponent(jbtnNuevo)
                    .addComponent(jbtnEliminar)
                    .addComponent(jbtnCancelar)
                    .addComponent(jbtnEditar))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jbtnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jbtnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnEditar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtnCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtnEliminar)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(75, 75, 75))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnGuardarActionPerformed
        // TODO add your handling code here:
        consumirInsertarDAO();
    }//GEN-LAST:event_jbtnGuardarActionPerformed

    private void jbtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditarActionPerformed
        // TODO add your handling code here:
        consumirUpdateDAO();
    }//GEN-LAST:event_jbtnEditarActionPerformed

    private void jbtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEliminarActionPerformed
        // TODO add your handling code here:
        consumirDeleteDAO();
    }//GEN-LAST:event_jbtnEliminarActionPerformed

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
            java.util.logging.Logger.getLogger(InterfazEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazEstudiante().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private ComponentesPropios.jTableMio jTableMio1;
    private javax.swing.JButton jbtnCancelar;
    private javax.swing.JButton jbtnEditar;
    private javax.swing.JButton jbtnEliminar;
    private javax.swing.JButton jbtnGuardar;
    private javax.swing.JButton jbtnNuevo;
    private javax.swing.JTextField jtxtFApellido;
    private javax.swing.JTextField jtxtFCedula;
    private javax.swing.JTextField jtxtFDireccion;
    private javax.swing.JTextField jtxtFNombre;
    private javax.swing.JTextField jtxtFTelefono;
    // End of variables declaration//GEN-END:variables
}
