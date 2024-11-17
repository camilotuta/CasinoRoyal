package main.java.com.casinoRoyal.ui.utils;

import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.JOptionPane;

public class SoundPlay {

    public void reproducir(String path) {
        Thread soundThread = new Thread(() -> {
            try {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(getClass().getResource(path));

                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);

                clip.start();

                clip.addLineListener(event -> {
                    if (event.getType() == LineEvent.Type.STOP) {
                        clip.close();
                    }
                });

                clip.drain();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                JOptionPane.showMessageDialog(
                        null, e.getMessage(), "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        soundThread.start();
    }
}
