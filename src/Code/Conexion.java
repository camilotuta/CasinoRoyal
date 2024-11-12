// cSpell:ignore publicacion operacion conexion
package Code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {

    private static final String URL = "jdbc:sqlite:db/casino_royal.db";

    // Método para obtener una nueva conexión a la base de datos
    public static Connection conectar() {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(URL);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR DE CONEXIÓN:\n" + e.getMessage());
            return null;
        }
    }

    // Método para cerrar una conexión, PreparedStatement y ResultSet
    public static void cerrarConexion(Connection conn, PreparedStatement pSt, ResultSet result) {
        try {
            if (result != null) {
                result.close();
            }
            if (pSt != null) {
                pSt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

   
}
