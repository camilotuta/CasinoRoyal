/*
 cSpell:ignore publicacion ubicacion operacion numeros tahoma
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Screens.Principal.Games;

import Code.ChatClient;
import Code.OperacionCRUD;
import Screens.Custom.CambiarIU;
import Screens.Custom.ObtenerIU;
import Screens.Custom.SoundPlay;
import Screens.Custom.Games.CasillasRuleta;
import Screens.Login.Login;
import Screens.Principal.Principal;
import Screens.Profile.PersonalProfile;
import Screens.Profile.Transactions;

import java.awt.EventQueue;
import java.awt.Toolkit;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author tutaa
 */
public class Ruleta extends javax.swing.JFrame {

        /**
         * Creates new form Ruleta
         */
        private ChatClient chatClient;

        public Ruleta() {
                initComponents();

                this.setTitle("Ruleta");
                this.setResizable(false);
                this.setLocationRelativeTo(null);

                this.setIconImage(Toolkit.getDefaultToolkit()
                                .getImage(getClass().getResource("/img/icon.png")));
                CambiarIU.setImageLabel(lbContenido, "src/img/ruletaQuieta.png");
                ingresarChat();
                taChatRuleta.setEditable(false);
                ponerFondos();
        }

        private void ponerFondos() {

                CambiarIU.ponerTextoEtiqueta(lbPonerFondos,
                                (Double.toString(PersonalProfile.obtenerFondos()) + " Fondos"));

        }

        private String obtenerNombre() {

                try {
                        ArrayList<ArrayList<Object>> datos = OperacionCRUD.seleccionar(
                                        String.format("SELECT * FROM jugadores where jugador_id = %d",
                                                        Login.idUsuarioGuardar),
                                        new String[] { "nombre_usuario" });

                        return (String) datos.get(0).get(0);
                } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR",
                                        JOptionPane.ERROR_MESSAGE);
                }
                return "";
        }

        private void ingresarChat() {
                Thread chatThread = new Thread(() -> {
                        String nombre = obtenerNombre();
                        if (!nombre.isEmpty()) {
                                chatClient = new ChatClient(nombre, taChatRuleta, taMensaje, imgEnviar, 4444);
                        } else {
                                System.out.println("No se pudo obtener el nombre del jugador.");
                        }
                });
                chatThread.start();
        }

        private void girarRuleta(String grupoApostado, int casillaApostada, double valorApostado) {
                if (PersonalProfile
                                .fondosSuficientes(
                                                Double.parseDouble(ObtenerIU.obtenerSeleccionCombo(cbValorApostado)))) {
                        Transactions.restarFondos(valorApostado);
                        ponerFondos();

                        CambiarIU.setImageLabel(lbContenido, "src/img/ruletaGirando.gif");
                        SoundPlay.reproducir("src/sound/ruletaGirando.wav");

                        int casillaJuego = CasillasRuleta.casillaAleatoria();
                        String colorJuego = CasillasRuleta.colorCasilla(casillaJuego);

                        final double[] valorGanado = { 0 };

                        new Thread(() -> {
                                try {
                                        Thread.sleep(5000);
                                } catch (InterruptedException e) {
                                        JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR",
                                                        JOptionPane.ERROR_MESSAGE);
                                }

                                if ((grupoApostado.equalsIgnoreCase("Rojo")
                                                && grupoApostado.equalsIgnoreCase(colorJuego))
                                                || (grupoApostado.equalsIgnoreCase("Negro")
                                                                && grupoApostado.equalsIgnoreCase(colorJuego))) {
                                        valorGanado[0] = valorApostado * 2;

                                } else if (grupoApostado.equalsIgnoreCase("Verde")
                                                && grupoApostado.equalsIgnoreCase(colorJuego)) {
                                        valorGanado[0] = valorApostado * 40;

                                } else if (grupoApostado.equalsIgnoreCase("") && casillaApostada == casillaJuego) {
                                        valorGanado[0] = valorApostado * 35;
                                }

                                String mensaje = "La ruleta ha caído en la casilla: " + casillaJuego + " (" + colorJuego
                                                + ")\n"
                                                + "Ganancia: $" + valorGanado[0];

                                JOptionPane.showMessageDialog(
                                                null,
                                                mensaje,
                                                "Resultado de la Ruleta",
                                                JOptionPane.INFORMATION_MESSAGE);

                                Transactions.sumarFondos(valorGanado[0]);
                                ponerFondos();
                                CambiarIU.setImageLabel(lbContenido, "src/img/ruletaQuieta.png");
                        }).start();
                }
        }

        /**
         * This method is called from within the constructor to initialize the
         * form. WARNING: Do NOT modify this code. The content of this method is
         * always regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                ventanaRuleta = new javax.swing.JPanel();
                imgVolver = new javax.swing.JLabel();
                btnDepositar = new javax.swing.JButton();
                lbRuleta = new javax.swing.JLabel();
                lbPonerFondos = new javax.swing.JLabel();
                lbChat = new javax.swing.JLabel();
                scChatRuleta = new javax.swing.JScrollPane();
                taChatRuleta = new javax.swing.JTextArea();
                scMensaje = new javax.swing.JScrollPane();
                taMensaje = new javax.swing.JTextArea();
                imgEnviar = new javax.swing.JLabel();
                lbApuesta = new javax.swing.JLabel();
                cbValorApostado = new javax.swing.JComboBox<>();
                btnAlVerde = new javax.swing.JButton();
                btnAlRojo = new javax.swing.JButton();
                btnAlNegro = new javax.swing.JButton();
                btnIngresarNumeros = new javax.swing.JButton();
                lbContenido = new javax.swing.JLabel();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                ventanaRuleta.setBackground(new java.awt.Color(27, 9, 5));
                ventanaRuleta.setPreferredSize(new java.awt.Dimension(1080, 720));
                ventanaRuleta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                imgVolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/volver.png"))); // NOI18N
                imgVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                imgVolver.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                imgVolverMouseClicked(evt);
                        }

                        public void mouseEntered(java.awt.event.MouseEvent evt) {
                                imgVolverMouseEntered(evt);
                        }

                        public void mouseExited(java.awt.event.MouseEvent evt) {
                                imgVolverMouseExited(evt);
                        }
                });
                ventanaRuleta.add(imgVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

                btnDepositar.setBackground(new java.awt.Color(147, 128, 67));
                btnDepositar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                btnDepositar.setForeground(new java.awt.Color(255, 255, 254));
                btnDepositar.setText("Depositar");
                btnDepositar.setActionCommand("Ingresar");
                btnDepositar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnDepositar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnDepositarActionPerformed(evt);
                        }
                });
                ventanaRuleta.add(btnDepositar, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 50, -1, -1));

                lbRuleta.setFont(new java.awt.Font("Crabs", 1, 100)); // NOI18N
                lbRuleta.setForeground(new java.awt.Color(227, 199, 104));
                lbRuleta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbRuleta.setText("Ruleta");
                ventanaRuleta.add(lbRuleta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1080, -1));

                lbPonerFondos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
                lbPonerFondos.setForeground(new java.awt.Color(148, 161, 178));
                lbPonerFondos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                lbPonerFondos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondos.png"))); // NOI18N
                lbPonerFondos.setText("-");
                ventanaRuleta.add(lbPonerFondos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1050, -1));

                lbChat.setFont(new java.awt.Font("Crabs", 1, 48)); // NOI18N
                lbChat.setForeground(new java.awt.Color(227, 199, 104));
                lbChat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbChat.setText("Chat");
                ventanaRuleta.add(lbChat, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 310, 220, -1));

                taChatRuleta.setBackground(new java.awt.Color(36, 38, 41));
                taChatRuleta.setColumns(20);
                taChatRuleta.setForeground(new java.awt.Color(148, 161, 178));
                taChatRuleta.setLineWrap(true);
                taChatRuleta.setRows(5);
                taChatRuleta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(227, 199, 104)));
                scChatRuleta.setViewportView(taChatRuleta);

                ventanaRuleta.add(scChatRuleta, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 370, -1, 260));

                taMensaje.setBackground(new java.awt.Color(36, 38, 41));
                taMensaje.setColumns(20);
                taMensaje.setForeground(new java.awt.Color(148, 161, 178));
                taMensaje.setLineWrap(true);
                taMensaje.setRows(2);
                taMensaje.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(227, 199, 104)));
                scMensaje.setViewportView(taMensaje);

                ventanaRuleta.add(scMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 650, 180, 50));

                imgEnviar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/enviar.png"))); // NOI18N
                imgEnviar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                imgEnviar.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                imgEnviarMouseClicked(evt);
                        }

                        public void mouseEntered(java.awt.event.MouseEvent evt) {
                                imgEnviarMouseEntered(evt);
                        }

                        public void mouseExited(java.awt.event.MouseEvent evt) {
                                imgEnviarMouseExited(evt);
                        }
                });
                ventanaRuleta.add(imgEnviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 650, 40, 40));

                lbApuesta.setFont(new java.awt.Font("Crabs", 1, 24)); // NOI18N
                lbApuesta.setForeground(new java.awt.Color(227, 199, 104));
                lbApuesta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbApuesta.setText("Apuesta");
                ventanaRuleta.add(lbApuesta, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 569, 190, 30));

                cbValorApostado.setBackground(new java.awt.Color(27, 9, 5));
                cbValorApostado.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                cbValorApostado.setForeground(new java.awt.Color(224, 195, 102));
                cbValorApostado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "100", "200", "500",
                                "1000", "2000", "5000", "10000", "25000", "50000", "100000" }));
                cbValorApostado.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                cbValorApostadoActionPerformed(evt);
                        }
                });
                ventanaRuleta.add(cbValorApostado,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 600, 190, 40));

                btnAlVerde.setBackground(new java.awt.Color(51, 153, 0));
                btnAlVerde.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                btnAlVerde.setForeground(new java.awt.Color(255, 255, 254));
                btnAlVerde.setText("Al verde");
                btnAlVerde.setActionCommand("Ingresar");
                btnAlVerde.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnAlVerde.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnAlVerdeActionPerformed(evt);
                        }
                });
                ventanaRuleta.add(btnAlVerde, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 610, 120, -1));

                btnAlRojo.setBackground(new java.awt.Color(255, 0, 0));
                btnAlRojo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                btnAlRojo.setForeground(new java.awt.Color(255, 255, 254));
                btnAlRojo.setText("Al rojo");
                btnAlRojo.setActionCommand("Ingresar");
                btnAlRojo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnAlRojo.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnAlRojoActionPerformed(evt);
                        }
                });
                ventanaRuleta.add(btnAlRojo, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 660, 120, -1));

                btnAlNegro.setBackground(new java.awt.Color(0, 0, 0));
                btnAlNegro.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                btnAlNegro.setForeground(new java.awt.Color(255, 255, 254));
                btnAlNegro.setText("Al negro");
                btnAlNegro.setActionCommand("Ingresar");
                btnAlNegro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnAlNegro.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnAlNegroActionPerformed(evt);
                        }
                });
                ventanaRuleta.add(btnAlNegro, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 660, 120, -1));

                btnIngresarNumeros.setBackground(new java.awt.Color(153, 153, 0));
                btnIngresarNumeros.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                btnIngresarNumeros.setForeground(new java.awt.Color(255, 255, 254));
                btnIngresarNumeros.setText("Numeros");
                btnIngresarNumeros.setActionCommand("Ingresar");
                btnIngresarNumeros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnIngresarNumeros.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnIngresarNumerosActionPerformed(evt);
                        }
                });
                ventanaRuleta.add(btnIngresarNumeros,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 610, 120, -1));

                lbContenido.setBackground(new java.awt.Color(36, 38, 41));
                lbContenido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbContenido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ruletaQuieta.png"))); // NOI18N
                ventanaRuleta.add(lbContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 210, 340, 340));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
                                                                .createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(ventanaRuleta,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                1080,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(ventanaRuleta, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void cbValorApostadoActionPerformed(java.awt.event.ActionEvent evt) {
        }

        private void btnAlVerdeActionPerformed(java.awt.event.ActionEvent evt) {
                girarRuleta("Verde", -1, Integer.valueOf(ObtenerIU.obtenerSeleccionCombo(cbValorApostado)));
        }

        private void btnAlNegroActionPerformed(java.awt.event.ActionEvent evt) {
                girarRuleta("Negro", -1, Integer.valueOf(ObtenerIU.obtenerSeleccionCombo(cbValorApostado)));

        }

        private void btnAlRojoActionPerformed(java.awt.event.ActionEvent evt) {
                girarRuleta("Rojo", -1, Integer.valueOf(ObtenerIU.obtenerSeleccionCombo(cbValorApostado)));

        }

        private void btnIngresarNumerosActionPerformed(java.awt.event.ActionEvent evt) {
                String input = JOptionPane.showInputDialog(null, "Adivina el número de la ruleta (0-36):");
                int numeroIngresado = -1;
                try {
                        if (Integer.valueOf(input) instanceof Integer) {
                                numeroIngresado = Integer.valueOf(input);

                                if ((numeroIngresado >= 0 && numeroIngresado <= 36)) {
                                        girarRuleta("", numeroIngresado,
                                                        Integer.valueOf(ObtenerIU
                                                                        .obtenerSeleccionCombo(cbValorApostado)));

                                }

                        } else {

                                JOptionPane.showMessageDialog(null,
                                                "El número ingresado no es válido (debe estar entre 0 y 36).",
                                                "ERROR",
                                                JOptionPane.ERROR_MESSAGE);
                        }

                } catch (Exception e) {
                        JOptionPane.showMessageDialog(null,
                                        "El número ingresado no es válido (debe estar entre 0 y 36).",
                                        "ERROR",
                                        JOptionPane.ERROR_MESSAGE);
                }

        }

        private void btnDepositarActionPerformed(java.awt.event.ActionEvent evt) {
                Transactions transactions = new Transactions();
                transactions.setVisible(true);
                this.setVisible(false);
        }

        private void imgEnviarMouseClicked(java.awt.event.MouseEvent evt) {

        }

        private void imgEnviarMouseEntered(java.awt.event.MouseEvent evt) {
                CambiarIU.setImageLabel(imgEnviar, "src/img/enviarHover.png");

        }

        private void imgEnviarMouseExited(java.awt.event.MouseEvent evt) {
                CambiarIU.setImageLabel(imgEnviar, "src/img/enviar.png");

        }

        private void imgVolverMouseEntered(java.awt.event.MouseEvent evt) {
                CambiarIU.setImageLabel(imgVolver, "src/img/volverHover.png");
        }

        private void imgVolverMouseExited(java.awt.event.MouseEvent evt) {
                CambiarIU.setImageLabel(imgVolver, "src/img/volver.png");
        }

        private void imgVolverMouseClicked(java.awt.event.MouseEvent evt) {
                if (chatClient != null) {
                        chatClient.close();
                } else {
                        System.out.println("El cliente de chat no está inicializado.");
                }
                Principal principal = new Principal();
                principal.setVisible(true);
                this.setVisible(false);
        }

        /**
         * @param args the command line arguments
         */
        public static void main(String args[]) {
                FlatMacDarkLaf.setup();
                EventQueue.invokeLater(() -> new Ruleta().setVisible(true));
        }

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btnAlNegro;
        private javax.swing.JButton btnAlRojo;
        private javax.swing.JButton btnAlVerde;
        private javax.swing.JButton btnDepositar;
        private javax.swing.JButton btnIngresarNumeros;
        private javax.swing.JComboBox<String> cbValorApostado;
        private javax.swing.JLabel imgEnviar;
        private javax.swing.JLabel imgVolver;
        private javax.swing.JLabel lbApuesta;
        private javax.swing.JLabel lbChat;
        private javax.swing.JLabel lbContenido;
        private javax.swing.JLabel lbPonerFondos;
        private javax.swing.JLabel lbRuleta;
        private javax.swing.JScrollPane scChatRuleta;
        private javax.swing.JScrollPane scMensaje;
        private javax.swing.JTextArea taChatRuleta;
        private javax.swing.JTextArea taMensaje;
        private javax.swing.JPanel ventanaRuleta;
        // End of variables declaration//GEN-END:variables
}