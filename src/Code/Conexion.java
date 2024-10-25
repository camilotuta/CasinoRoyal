// cSpell:ignore publicacion operacion conexion
package Code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {

    static String url = "jdbc:sqlite:db/casino_royal.db";
    static Connection connect;
    static PreparedStatement pSt;
    static ResultSet result = null;

    public static void conectar() {
        try {
            Class.forName("org.sqlite.JDBC");
            connect = DriverManager.getConnection(url);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR DE CONEXIÓN:\n" + e.getMessage());
        }
    }

    public static void cerrarConexion() {
        try {
            if (pSt != null) {
                pSt.close();
            }
            if (result != null) {
                result.close();
            }
            if (connect != null) {
                connect.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

    public static void operaciones() throws SQLException {

        // ^ REGISTRAR
        OperacionCRUD.registrar(
                "INSERT INTO usuarios (correo,contraseña,nombre,departamento,ciudad,edad,"
                        + "fecha_registro ) VALUES ('laura@gmail.com','Laura1234!','Laura Gomez',"
                        // cSpell:disable-next-line
                        + "'Antioquia','Medellín',25,DATE ('now'));");

        // ^ ELIMINAR
        OperacionCRUD.eliminar("delete from usuarios where correo like '%@example%'");

        // ^ SELECCIONAR
        System.out.println(OperacionCRUD.seleccionar("select * from usuarios",
                new String[] { "nombre", "edad", "correo" }));

        // ^ ACTUALIZAR
        OperacionCRUD.actualizar("update usuarios set nombre = 'Adrian Camilo Tuta Cortes' where "
                + "id_usuario = 1");

    }

}
