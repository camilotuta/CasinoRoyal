package main.java.com.casinoRoyal.ui.utils;

import static main.java.com.casinoRoyal.util.dates.Dates.formatter;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import raven.datetime.component.date.DatePicker;

public class CambiarIU {
    public static void setImageLabel(JLabel labelName, String root) {
        new Thread(() -> {
            ImageIcon image = new ImageIcon(root);
            Icon icon = new ImageIcon(
                    image.getImage().getScaledInstance(labelName.getWidth(), labelName.getHeight(),
                            Image.SCALE_DEFAULT));
            labelName.setIcon(icon);
        }).start();
    }

    public static void setImageLabelSize(JLabel labelName, String root, int width, int height) {
        ImageIcon image = new ImageIcon(root);
        Icon icon = new ImageIcon(
                image.getImage().getScaledInstance(width, height,
                        Image.SCALE_DEFAULT));
        labelName.setIcon(icon);
    }

    public static void ponerTextoEtiqueta(JLabel label, String texto) {
        label.setText(texto);
    }

    public static void ponerTextoCampo(JTextField field, String texto) {
        field.setText(texto);
    }

    public static void ponerTextoArea(JTextArea txtArea, String texto) {
        txtArea.setText(texto);
    }

    public static void ponerTextoPanel(JTextPane txtPanel, String texto) {
        txtPanel.setText(texto);
    }

    public static void ponerTextoCampoContraseña(JPasswordField contraseñaField, String texto) {
        contraseñaField.setText(texto);
    }

    public static String obtenerFechaSeleccionada(DatePicker datePicker) {
        return formatter.format(datePicker.getSelectedDate());
    }

    public static void deshabilitarBotones(JButton... args) {
        for (JButton b : args) {
            b.setEnabled(false);
        }
    }

    public static void habilitarBotones(JButton... args) {
        for (JButton b : args) {
            b.setEnabled(true);
        }
    }
}
