package servidorChatsCasinoRoyal;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.awt.EventQueue;

public class ServidorChatPoker {

	public static void main(String[] args) {
		FlatMacDarkLaf.setup();

		new Thread(() -> {
			EventQueue.invokeLater(() -> new VistaServidor(3333, 1).setVisible(true));
		}).start();

	}
}
