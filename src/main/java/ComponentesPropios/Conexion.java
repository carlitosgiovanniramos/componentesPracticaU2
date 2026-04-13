/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ComponentesPropios;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo LOQ
 */
public class Conexion {
    public Connection Conectar() {
        Connection cn =  null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost/cuarto", "root", "");
            System.out.println((cn == null) ? "no valio" : "valio");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return cn;
    }
}
