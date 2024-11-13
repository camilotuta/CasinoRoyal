package consultas;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Actualizar extends Conexion {

	public static void actualizarPersonasConectadas(int juegoId, int personasConectadas) {
		String query = "UPDATE JUEGOS SET personas_conectadas = ? WHERE juego_id = ?";
		try (PreparedStatement pSt = conectar().prepareStatement(query)) {
			pSt.setInt(1, personasConectadas);
			pSt.setInt(2, juegoId);
			pSt.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}


}
