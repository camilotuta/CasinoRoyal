package servidorChatsCasinoRoyal;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.awt.EventQueue;

public class ServidorChatCarrera {

	public static void main(String[] args) {
		FlatMacDarkLaf.setup();

		new Thread(() -> {
			EventQueue.invokeLater(() -> new VistaServidor(1111, 6).setVisible(true));
		}).start();
	}
}
