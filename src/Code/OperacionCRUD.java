// cSpell:ignore operacion publicacion conexion informacion
package Code;

import java.sql.SQLException;
import java.util.ArrayList;

public class OperacionCRUD extends Conexion{
   public static void registrar(String query) throws SQLException {
        conectar();
        try {
            pSt = connect.prepareStatement(query);
            pSt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ERROR AL REGISTRAR INFORMACION:\n" + e.getMessage());
        } finally {
            cerrarConexion();
        }
    }

    public static ArrayList<ArrayList<Object>> seleccionar(String query, String[] columnas) throws SQLException {
        conectar();
        ArrayList<ArrayList<Object>> registros = new ArrayList<>();
        try {
            pSt = connect.prepareStatement(query);
            result = pSt.executeQuery();

            while (result.next()) {
                ArrayList<Object> registro = new ArrayList<>();
                for (String i : columnas) {
                    registro.add(result.getObject(i));
                }
                registros.add(registro);
            }
        } catch (SQLException e) {
            System.out.println("ERROR AL SELECCIONAR INFORMACIÓN:\n" + e.getMessage());
        } finally {
            cerrarConexion();
        }
        return registros;
    }

    public static void actualizar(String query) throws SQLException {
        conectar();
        try {
            pSt = connect.prepareStatement(query);
            pSt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ERROR AL ACTUALIZAR INFORMACIÓN:\n" + e.getMessage());
        } finally {
            cerrarConexion();
        }
    }

    public static void eliminar(String query) throws SQLException {
        conectar();
        try {
            pSt = connect.prepareStatement(query);
            pSt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ERROR AL BORRAR INFORMACIÓN:\n" + e.getMessage());
        } finally {
            cerrarConexion();
        }
    }
}
