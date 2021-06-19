package modelos;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;

public class Conexion {

    Connection cn;

    public Connection getConexion() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String user = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/db_proyecto_ventas";

        try {
            Class.forName(driver);
            cn = DriverManager.getConnection(url, user, password);
        } catch (Exception ex) {
            System.out.println("No conexion" + " " + ex);
        }
        return cn;
    }

    public void closeConexion(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException sql) {
            
        }
    }
}
