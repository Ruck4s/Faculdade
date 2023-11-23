package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnectionDAO {
    public Connection connectBD() {
        Connection conn = null;
        
        try {
            String url = "jdbc:mysql://localhost:3306/crud_db?user=root&password=";
            conn = DriverManager.getConnection(url); 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return conn;
    }
}

