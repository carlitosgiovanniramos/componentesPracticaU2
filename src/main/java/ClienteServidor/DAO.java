/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClienteServidor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo LOQ
 */
public class DAO {

    public void insertStudentDAO(Estudiante estudiante) {
        try {
            Conexion cn = new Conexion();
            Connection cc = cn.conectar();
            String sql = "INSERT INTO estudiantes VALUES(?,?,?,?,?)";
            PreparedStatement psd = cc.prepareStatement(sql);
            psd.setString(1, estudiante.getEstCedula());
            psd.setString(2, estudiante.getEstNombre());
            psd.setString(3, estudiante.getEstApellido());
            psd.setString(4, estudiante.getEstDireccion());
            psd.setString(5, estudiante.getEstTelefono());

            int respuesta = psd.executeUpdate();
            if (respuesta > 0) {
                JOptionPane.showMessageDialog(null, "Se inserto un Estudiante");
                selectStudentDao();
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public List<Estudiante> selectStudentDao() {
        try {
            List<Estudiante> lista = new ArrayList();
            Conexion cn = new Conexion();
            Connection cc = cn.conectar();
            Statement psd = cc.createStatement();
            String sql = "select * from estudiantes";
            ResultSet rs = psd.executeQuery(sql);
            while (rs.next()) {
                //registros[0]=rs.getString("estCedula");
                //registros[1]=rs.getString("estNombre");
                //registros[2]=rs.getString("estApellido");
                //registros[3]=rs.getString("estDireccion");
                //registros[4]=rs.getString("estTelefono");
                //model.addRow(registros);
                Estudiante est = new Estudiante(rs.getString("estCedula"),
                        rs.getString("estNombre"),
                        rs.getString("estApellido"),
                        rs.getString("estDireccion"),
                        rs.getString("estTelefono"));
                lista.add(est);
            }
            return lista;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return null;
    }

    public boolean deleteStudentDAO(Estudiante estudiante) {
        try {
            Conexion cn = new Conexion();
            Connection cc = cn.conectar();
            String sql = "DELETE FROM estudiantes WHERE estCedula=?";
            PreparedStatement psd = cc.prepareStatement(sql);

            psd.setString(1, estudiante.getEstCedula());

            return psd.executeUpdate() > 0;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
    }
    
        public void updateStudentDAO(Estudiante estudiante) {
        try {
            Conexion cn = new Conexion();
            Connection cc = cn.conectar();
            String sql = "UPDATE estudiantes SET estNombre=?, estApellido=?, estDireccion=?, estTelefono=? WHERE estCedula=?";
            PreparedStatement psd = cc.prepareStatement(sql);

            psd.setString(1, estudiante.getEstNombre());
            psd.setString(2, estudiante.getEstApellido());
            psd.setString(3, estudiante.getEstDireccion());
            psd.setString(4, estudiante.getEstTelefono());
            psd.setString(5, estudiante.getEstCedula());

            int opc = psd.executeUpdate();
            if (opc > 0) {
                JOptionPane.showMessageDialog(null, "Se modifico un Estudiante");
                selectStudentDao();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
