package main.java.com.casinoRoyal.service.user;

import java.io.*;

import javax.swing.JOptionPane;

public class DatosUsuario {
    private final String filePath;

    public DatosUsuario() {
        String userDocuments = System.getProperty("user.home") + File.separator + "Documents";
        this.filePath = userDocuments + File.separator + "datos_casino_royal.txt";
    }

    public void guardarInfo(String email, String password) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

                writer.write(email + " " + password);
            }
        } catch (IOException e) {
            System.err.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    public String[] obtenerInfo() {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            if ((line = reader.readLine()) != null) {
                return line.split(" ");
            }
            return new String[] { "", "" };
        } catch (IOException e) {
            System.err.println("Error al leer los datos: " + e.getMessage());
        }
        return null;
    }
}