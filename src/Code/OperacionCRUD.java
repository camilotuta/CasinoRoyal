package Code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OperacionCRUD {

    // Método para registrar información en la base de datos
    public static void registrar(Connection conn, String query) throws SQLException {
        try (PreparedStatement pSt = conn.prepareStatement(query)) {
            pSt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ERROR AL REGISTRAR INFORMACIÓN:\n" + e.getMessage());
            throw e;
        }
    }

    // Método para seleccionar información de la base de datos
    public static ArrayList<ArrayList<Object>> seleccionar(Connection conn, String query, String[] columnas) throws SQLException {
        ArrayList<ArrayList<Object>> registros = new ArrayList<>();
        try (PreparedStatement pSt = conn.prepareStatement(query);
             ResultSet result = pSt.executeQuery()) {
             
            while (result.next()) {
                ArrayList<Object> registro = new ArrayList<>();
                for (String columna : columnas) {
                    registro.add(result.getObject(columna));
                }
                registros.add(registro);
            }
        } catch (SQLException e) {
            System.out.println("ERROR AL SELECCIONAR INFORMACIÓN:\n" + e.getMessage());
            throw e;
        }
        return registros;
    }

    // Método para actualizar información en la base de datos
    public static void actualizar(Connection conn, String query) throws SQLException {
        try (PreparedStatement pSt = conn.prepareStatement(query)) {
            pSt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ERROR AL ACTUALIZAR INFORMACIÓN:\n" + e.getMessage());
            throw e;
        }
    }

    // Método para eliminar información de la base de datos
    public static void eliminar(Connection conn, String query) throws SQLException {
        try (PreparedStatement pSt = conn.prepareStatement(query)) {
            pSt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ERROR AL BORRAR INFORMACIÓN:\n" + e.getMessage());
            throw e;
        }
    }
}
