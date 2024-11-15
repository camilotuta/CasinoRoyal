package Screens.Custom;

import javax.sound.sampled.*;
import javax.swing.JOptionPane;

import java.io.File;
import java.io.IOException;

public class SoundPlay {

    public static void reproducir(String filePath) {
        Thread soundThread = new Thread(() -> {
            try {
                File soundFile = new File(filePath);
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);

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
