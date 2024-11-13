package servidorChatsCasinoRoyal;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.awt.EventQueue;

public class ServidorChatBlackJack {

	public static void main(String[] args) {
		FlatMacDarkLaf.setup();

		new Thread(() -> {
			EventQueue.invokeLater(() -> new VistaServidor(2222, 3).setVisible(true));
		}).start();

	}
}
