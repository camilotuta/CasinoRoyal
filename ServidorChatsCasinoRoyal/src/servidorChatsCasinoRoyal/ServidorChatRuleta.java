package servidorChatsCasinoRoyal;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.awt.EventQueue;

public class ServidorChatRuleta {

    public static void main(String[] args) {
        FlatMacDarkLaf.setup();

        new Thread(() -> {
            EventQueue.invokeLater(() -> new VistaServidor(4444).setVisible(true));
        }).start();
    }
}
