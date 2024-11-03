package servidorChatsCasinoRoyal;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.awt.EventQueue;

public class ServidorChatBingo {

    public static void main(String[] args) {
        FlatMacDarkLaf.setup();

        new Thread(() -> {
            EventQueue.invokeLater(() -> new VistaServidor(1111).setVisible(true));
        }).start();
    }
}
