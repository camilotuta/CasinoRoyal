// cSpell: ignore operacion
package main.java.com.casinoRoyal.core.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class OperacionCRUD {

    public static void registrar(Connection conn, String query) {
        try (PreparedStatement pSt = conn.prepareStatement(query)) {
            pSt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static ArrayList<ArrayList<Object>> seleccionar(Connection conn, String query, String[] columnas) {
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
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return registros;
    }

    public static void actualizar(Connection conn, String query) {
        try (PreparedStatement pSt = conn.prepareStatement(query)) {
            pSt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void eliminar(Connection conn, String query) {
        try (PreparedStatement pSt = conn.prepareStatement(query)) {
            pSt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
