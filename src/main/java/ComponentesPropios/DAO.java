/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ComponentesPropios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo LOQ
 */
public class DAO {

    public List<Estudiante> selectStudentDAO() {
        List<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiantes";

        try {
            Conexion cn = new Conexion();
            Connection cc = cn.Conectar();
            Statement psd = cc.createStatement();
            ResultSet rs = psd.executeQuery(sql);

            while (rs.next()) {
                Estudiante estudiante = new Estudiante(rs.getString("estCedula"),
                        rs.getString("estNombre"),
                        rs.getString("estApellido"),
                        rs.getString("estDireccion"),
                        rs.getString("estTelefono"));
                lista.add(estudiante);
            }
            return lista;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return null;
    }

    public void insertStudentDAO(Estudiante estudiante) {
        try {
            Conexion cn = new Conexion();
            Connection cc = cn.Conectar();
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
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void updateStudent(Estudiante estudiante) {
        try {
            Conexion cn = new Conexion();
            Connection cc = cn.Conectar();
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
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
        //DELETE OPCIONAL
    public boolean deleteStudentDAO(Estudiante estudiante) {
        try {
            Conexion cn = new Conexion();
            Connection cc = cn.Conectar();
            String sql = "DELETE FROM estudiantes WHERE estCedula=?";
            PreparedStatement psd = cc.prepareStatement(sql);
            
            psd.setString(1, estudiante.getEstCedula());
            
            return psd.executeUpdate() > 0;      
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return false;
    }
}
