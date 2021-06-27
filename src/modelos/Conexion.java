package modelos;

import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
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
            DesktopNotify.setDefaultTheme(NotifyTheme.Red);
            DesktopNotify.showDesktopMessage("Error en la conexion", "No se ha podido establecer conexion con la base de datos", DesktopNotify.FAIL, 8000);
        }
        return cn;
    }

    public void closeConexion(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException sql) {
            DesktopNotify.setDefaultTheme(NotifyTheme.Red);
            DesktopNotify.showDesktopMessage("Error en la conexion", "No se ha podido establecer conexion con la base de datos", DesktopNotify.FAIL, 8000);
        }
    }
}
