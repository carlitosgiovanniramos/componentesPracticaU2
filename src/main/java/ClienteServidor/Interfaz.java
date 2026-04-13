/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ClienteServidor;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Lenovo LOQ
 */
public class Interfaz extends javax.swing.JFrame {

    /**
     * Creates new form Interfaz
     */
    DefaultTableModel modeloEstudiantes;

    public Interfaz() {
        initComponents();
        selectStudent();
        selectRowStudent();
    }

    public void clearFields() {
        jtxtFCedula.setText("");
        jtxtFNombre.setText("");
        jtxtFApellido.setText("");
        jtxtFDireccion.setText("");
        jtxtFTelefono.setText("");
    }

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
    public void selectStudent() {
        try {
            DAO dao = new DAO();
            List<Estudiante> listaEstudiantes = dao.selectStudentDao();
            String[] columnas = {"Cedula", "Nombre", "Apellido", "Direccion", "Telefono"};
            String[] filas = new String[5];
            modeloEstudiantes = new DefaultTableModel(null, columnas);
            for (Estudiante estudiante : listaEstudiantes) {
                filas[0] = estudiante.getEstCedula();
                filas[1] = estudiante.getEstNombre();
                filas[2] = estudiante.getEstApellido();
                filas[3] = estudiante.getEstDireccion();
                filas[4] = estudiante.getEstTelefono();
                modeloEstudiantes.addRow(filas);
            }
            jtblEstudiantes.setModel(modeloEstudiantes);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /*
    public void insertStudent() {
        try {
            Conexion cn = new Conexion();
            Connection cc = cn.conectar();
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
                selectEstudent();
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
     */
    public void consumirInsertarDAO() {
        Estudiante estudiante = new Estudiante(jtxtFCedula.getText(), jtxtFNombre.getText(), jtxtFApellido.getText(), jtxtFDireccion.getText(), jtxtFTelefono.getText());

        DAO dao = new DAO();
        dao.insertStudentDAO(estudiante);
        selectStudent();
    }
        public void consumirUpdateDAO() {
        Estudiante estudiante = new Estudiante(jtxtFCedula.getText(), jtxtFNombre.getText(), jtxtFApellido.getText(), jtxtFDireccion.getText(), jtxtFTelefono.getText());

        DAO dao = new DAO();
        dao.updateStudentDAO(estudiante);
        selectStudent();
    }

    public void deleteStudent() {
        try {
            if (JOptionPane.showConfirmDialog(null,
                    "¿Estás seguro de eliminar el estudiante?",
                    "Eliminar",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                DAO dao = new DAO();
                Estudiante estudiante = new Estudiante();
                estudiante.setEstCedula(jtxtFCedula.getText());

                if (dao.deleteStudentDAO(estudiante)) {
                    JOptionPane.showMessageDialog(null, "Se eliminó el estudiante");
                    selectStudent();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /*
    public void updateStudent() {
        try {
            Conexion cn = new Conexion();
            Connection cc = cn.conectar();
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
                selectStudent();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }*/

    public void selectRowStudent() {
        jtblEstudiantes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (jtblEstudiantes.getSelectedRow() != -1) {
                    int fila = jtblEstudiantes.getSelectedRow();
                    jtxtFCedula.setText(jtblEstudiantes.getValueAt(fila, 0).toString());
                    jtxtFNombre.setText(jtblEstudiantes.getValueAt(fila, 1).toString());
                    jtxtFApellido.setText(jtblEstudiantes.getValueAt(fila, 2).toString());
                    jtxtFDireccion.setText(jtblEstudiantes.getValueAt(fila, 3).toString());
                    jtxtFTelefono.setText(jtblEstudiantes.getValueAt(fila, 4).toString());
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

        jPasswordField1 = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        jlblCedula = new javax.swing.JLabel();
        jlblNombre = new javax.swing.JLabel();
        jlblApellido = new javax.swing.JLabel();
        jlblTelefono = new javax.swing.JLabel();
        jlblDireccion = new javax.swing.JLabel();
        jtxtFNombre = new javax.swing.JTextField();
        jtxtFApellido = new javax.swing.JTextField();
        jtxtFCedula = new javax.swing.JTextField();
        jtxtFTelefono = new javax.swing.JTextField();
        jtxtFDireccion = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jbtnNuevo = new javax.swing.JButton();
        jbtnGuardar = new javax.swing.JButton();
        jbtnEditar = new javax.swing.JButton();
        jbtnBorrar = new javax.swing.JButton();
        jbtnCancelar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblEstudiantes = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        jPasswordField1.setText("jPasswordField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jlblCedula.setText("Ingrese Cedula");

        jlblNombre.setText("Ingrese Nombre");

        jlblApellido.setText("Ingrese Apellido");

        jlblTelefono.setText("Ingrese Telefono");

        jlblDireccion.setText("Ingrese Direccion");

        jtxtFNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtFNombreActionPerformed(evt);
            }
        });

        jtxtFTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtFTelefonoActionPerformed(evt);
            }
        });

        jtxtFDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtFDireccionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlblCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlblApellido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlblNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlblTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtxtFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtFApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtFTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtFDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtFCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblCedula)
                    .addComponent(jtxtFCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblNombre)
                    .addComponent(jtxtFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblApellido)
                    .addComponent(jtxtFApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblDireccion)
                    .addComponent(jtxtFDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblTelefono)
                    .addComponent(jtxtFTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jbtnNuevo.setText("Nuevo");
        jbtnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnNuevoActionPerformed(evt);
            }
        });

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

        jbtnBorrar.setText("Borrar");
        jbtnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnBorrarActionPerformed(evt);
            }
        });

        jbtnCancelar.setText("Cancelar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnCancelar)
                    .addComponent(jbtnBorrar)
                    .addComponent(jbtnEditar)
                    .addComponent(jbtnGuardar)
                    .addComponent(jbtnNuevo))
                .addGap(48, 48, 48))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jbtnNuevo)
                .addGap(18, 18, 18)
                .addComponent(jbtnGuardar)
                .addGap(18, 18, 18)
                .addComponent(jbtnEditar)
                .addGap(18, 18, 18)
                .addComponent(jbtnBorrar)
                .addGap(18, 18, 18)
                .addComponent(jbtnCancelar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jtblEstudiantes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jtblEstudiantes);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Ingresar Estudiantes");

        jButton1.setText("Conectar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtFTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtFTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtFTelefonoActionPerformed

    private void jtxtFDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtFDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtFDireccionActionPerformed

    private void jtxtFNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtFNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtFNombreActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Conexion cn = new Conexion();
        cn.conectar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jbtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnGuardarActionPerformed
        // TODO add your handling code here:
        consumirInsertarDAO();

    }//GEN-LAST:event_jbtnGuardarActionPerformed

    private void jbtnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnBorrarActionPerformed
        // TODO add your handling code here:
        deleteStudent();
    }//GEN-LAST:event_jbtnBorrarActionPerformed

    private void jbtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditarActionPerformed
        // TODO add your handling code here:
        consumirUpdateDAO();
    }//GEN-LAST:event_jbtnEditarActionPerformed

    private void jbtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnNuevoActionPerformed
        // TODO add your handling code here:
        clearFields();
    }//GEN-LAST:event_jbtnNuevoActionPerformed

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnBorrar;
    private javax.swing.JButton jbtnCancelar;
    private javax.swing.JButton jbtnEditar;
    private javax.swing.JButton jbtnGuardar;
    private javax.swing.JButton jbtnNuevo;
    private javax.swing.JLabel jlblApellido;
    private javax.swing.JLabel jlblCedula;
    private javax.swing.JLabel jlblDireccion;
    private javax.swing.JLabel jlblNombre;
    private javax.swing.JLabel jlblTelefono;
    private javax.swing.JTable jtblEstudiantes;
    private javax.swing.JTextField jtxtFApellido;
    private javax.swing.JTextField jtxtFCedula;
    private javax.swing.JTextField jtxtFDireccion;
    private javax.swing.JTextField jtxtFNombre;
    private javax.swing.JTextField jtxtFTelefono;
    // End of variables declaration//GEN-END:variables
}
