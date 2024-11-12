/*
 cSpell:ignore publicacion ubicacion operacion tahoma
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Screens.Principal.Games;

import Code.ChatClient;
import Screens.Custom.Games.Carrera.CarreraCarros;
import Screens.Custom.Games.Carrera.Carro;
import Code.OperacionCRUD;
import Screens.Custom.CambiarIU;

import Screens.Custom.ObtenerIU;
import Screens.Custom.SoundPlay;
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
public class Carrera extends javax.swing.JFrame {

        /**
         * Creates new form Carrera
         */
        private ChatClient chatClient;

        public Carrera() {
                initComponents();

                this.setTitle("Bingo");
                this.setResizable(false);
                this.setLocationRelativeTo(null);

                this.setIconImage(Toolkit.getDefaultToolkit()
                                .getImage(getClass().getResource("/img/icon.png")));
                ingresarChat();
                taChatCarrera.setEditable(false);
                ponerFondos();
                ponerCarros();
        }

        private void ponerFondos() {

                CambiarIU.ponerTextoEtiqueta(lbPonerFondos,
                                (Double.toString(PersonalProfile.obtenerFondos()) + " Fondos"));

        }

        private void ponerCarros() {
                taContenidoCarrera.setText("");

                for (int i = 0; i < CarreraCarros.conductores.size(); i++) {
                        taContenidoCarrera.append("\n" + (i + 1) + " - " + CarreraCarros.conductores.get(i) + ": "
                                        + CarreraCarros.iconosCarros[i] + "\n\n");
                }
        }

        private String obtenerNombre() {

                try {
                        ArrayList<ArrayList<Object>> datos = OperacionCRUD.seleccionar(
                                        String.format("SELECT * FROM jugadores where jugador_id = %d",
                                                        Login.idUsuarioGuardar),
                                        new String[] { "nombre_usuario" });

                        String[] nombreCompleto = ((String) datos.get(0).get(0)).split(" ");
                        String nombreApellido = "";
                        if (nombreCompleto.length <= 5 && nombreCompleto.length >= 3) {
                                nombreApellido = nombreCompleto[0] + " " + nombreCompleto[2];

                        } else if (nombreCompleto.length == 1) {
                                nombreApellido = nombreCompleto[0];
                        } else {
                                nombreApellido = nombreCompleto[0] + " " + nombreCompleto[1];
                        }

                        return nombreApellido;
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
                                chatClient = new ChatClient(nombre, taChatCarrera, taMensaje, imgEnviar, 1111);
                        } else {
                                JOptionPane.showMessageDialog(null,
                                                "No se pudo obtener el nombre del jugador.", "ERROR",
                                                JOptionPane.ERROR_MESSAGE);
                        }
                });
                chatThread.start();
        }

        private void iniciarCarrera(int carroGanador, double valorApostado) {

                if (PersonalProfile.fondosSuficientes(
                                Double.parseDouble(ObtenerIU.obtenerSeleccionCombo(cbValorApostado)))) {
                        Transactions.restarFondos(valorApostado);
                        ponerFondos();
                        SoundPlay.reproducir("src/sound/carrera.wav");
                        CambiarIU.deshabilitarBotones(btnApostarCarro1, btnApostarCarro2, btnApostarCarro3,
                                        btnApostarCarro4);

                        final double[] valorGanado = { 0 };
                        new Thread(() -> {

                                CarreraCarros carrera = new CarreraCarros(taContenidoCarrera, lbCuentaRegresiva);
                                try {
                                        carrera.iniciarCarrera();
                                        while (!carrera.hayGanador()) {
                                                carrera.avanzarCarros();
                                                carrera.mostrarPistas();
                                        }

                                        if (carrera.ganadores.size() == 1
                                                        && CarreraCarros.conductores.indexOf((carrera.ganadores.get(0)
                                                                        .getNombreConductor())) == (carroGanador - 1)) {

                                                valorGanado[0] = valorApostado * 10;

                                        } else if (carrera.ganadores.size() == 2
                                                        && CarreraCarros.conductores.contains((carrera.ganadores.get(0)
                                                                        .getNombreConductor()))) {
                                                valorGanado[0] = valorApostado * 5;
                                        } else if (carrera.ganadores.size() == 3
                                                        && CarreraCarros.conductores.contains((carrera.ganadores.get(0)
                                                                        .getNombreConductor()))) {
                                                valorGanado[0] = valorApostado * 2.5;
                                        } else if (carrera.ganadores.size() == 3
                                                        && CarreraCarros.conductores.contains((carrera.ganadores.get(0)
                                                                        .getNombreConductor()))) {
                                                valorGanado[0] = valorApostado * 1.25;
                                        }

                                        Thread.sleep(2000);
                                        ponerCarros();
                                        carrera.mostrarGanador();
                                        String mensaje = "Ganancia: $" + valorGanado[0];

                                        var textoGanadores = "LOS GANADORES SON: \n";

                                        if (carrera.ganadores.size() == 1) {
                                                textoGanadores += "El ganador es: "
                                                                + carrera.ganadores.get(0).getNombreConductor()
                                                                                .toUpperCase()
                                                                + " "
                                                                + carrera.ganadores.get(0).getIcon() + "\n";
                                        } else {
                                                for (Carro i : carrera.ganadores) {
                                                        textoGanadores += i.getNombreConductor().toUpperCase() + " "
                                                                        + i.getIcon() + "\n";
                                                }
                                        }

                                        String mensajeFinal = textoGanadores + "\n" + mensaje;

                                        JOptionPane.showMessageDialog(null, mensajeFinal, "Resultado de la partida",
                                                        JOptionPane.INFORMATION_MESSAGE);

                                        Transactions.sumarFondos(valorGanado[0]);
                                        ponerFondos();
                                        CambiarIU.habilitarBotones(btnApostarCarro1, btnApostarCarro2,
                                                        btnApostarCarro3,
                                                        btnApostarCarro4);
                                } catch (InterruptedException e) {
                                        JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR",
                                                        JOptionPane.ERROR_MESSAGE);
                                }
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
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                ventanaBingo = new javax.swing.JPanel();
                btnDepositar = new javax.swing.JButton();
                imgVolver = new javax.swing.JLabel();
                lbCarrera = new javax.swing.JLabel();
                lbPonerFondos = new javax.swing.JLabel();
                lbChat = new javax.swing.JLabel();
                scChatCarrera = new javax.swing.JScrollPane();
                taChatCarrera = new javax.swing.JTextArea();
                scMensaje = new javax.swing.JScrollPane();
                taMensaje = new javax.swing.JTextArea();
                imgEnviar = new javax.swing.JLabel();
                btnApostarCarro2 = new javax.swing.JButton();
                btnApostarCarro1 = new javax.swing.JButton();
                btnApostarCarro3 = new javax.swing.JButton();
                btnApostarCarro4 = new javax.swing.JButton();
                scCarrera = new javax.swing.JScrollPane();
                taContenidoCarrera = new javax.swing.JTextArea();
                cbValorApostado = new javax.swing.JComboBox<>();
                lbCuentaRegresiva = new javax.swing.JLabel();
                lbApuesta = new javax.swing.JLabel();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                ventanaBingo.setBackground(new java.awt.Color(27, 9, 5));
                ventanaBingo.setPreferredSize(new java.awt.Dimension(1080, 720));
                ventanaBingo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
                ventanaBingo.add(btnDepositar, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 50, -1, -1));

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
                ventanaBingo.add(imgVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

                lbCarrera.setFont(new java.awt.Font("Crabs", 1, 100)); // NOI18N
                lbCarrera.setForeground(new java.awt.Color(227, 199, 104));
                lbCarrera.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbCarrera.setText("Carrera");
                ventanaBingo.add(lbCarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1080, -1));

                lbPonerFondos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
                lbPonerFondos.setForeground(new java.awt.Color(148, 161, 178));
                lbPonerFondos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                lbPonerFondos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondos.png"))); // NOI18N
                lbPonerFondos.setText("-");
                ventanaBingo.add(lbPonerFondos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1050, -1));

                lbChat.setFont(new java.awt.Font("Crabs", 1, 48)); // NOI18N
                lbChat.setForeground(new java.awt.Color(227, 199, 104));
                lbChat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbChat.setText("Chat");
                ventanaBingo.add(lbChat, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 310, 220, -1));

                taChatCarrera.setBackground(new java.awt.Color(36, 38, 41));
                taChatCarrera.setColumns(20);
                taChatCarrera.setForeground(new java.awt.Color(148, 161, 178));
                taChatCarrera.setLineWrap(true);
                taChatCarrera.setRows(5);
                taChatCarrera.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(227, 199, 104)));
                scChatCarrera.setViewportView(taChatCarrera);

                ventanaBingo.add(scChatCarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 370, -1, 260));

                taMensaje.setBackground(new java.awt.Color(36, 38, 41));
                taMensaje.setColumns(20);
                taMensaje.setForeground(new java.awt.Color(148, 161, 178));
                taMensaje.setLineWrap(true);
                taMensaje.setRows(2);
                taMensaje.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(227, 199, 104)));
                scMensaje.setViewportView(taMensaje);

                ventanaBingo.add(scMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 650, 180, 50));

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
                ventanaBingo.add(imgEnviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 650, 40, 40));

                btnApostarCarro2.setBackground(new java.awt.Color(147, 128, 67));
                btnApostarCarro2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                btnApostarCarro2.setForeground(new java.awt.Color(255, 255, 254));
                btnApostarCarro2.setText("Carro 2");
                btnApostarCarro2.setActionCommand("Ingresar");
                btnApostarCarro2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnApostarCarro2.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnApostarCarro2ActionPerformed(evt);
                        }
                });
                ventanaBingo.add(btnApostarCarro2,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 570, 140, -1));

                btnApostarCarro1.setBackground(new java.awt.Color(147, 128, 67));
                btnApostarCarro1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                btnApostarCarro1.setForeground(new java.awt.Color(255, 255, 254));
                btnApostarCarro1.setText("Carro 1");
                btnApostarCarro1.setActionCommand("Ingresar");
                btnApostarCarro1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnApostarCarro1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnApostarCarro1ActionPerformed(evt);
                        }
                });
                ventanaBingo.add(btnApostarCarro1,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 570, 140, -1));

                btnApostarCarro3.setBackground(new java.awt.Color(147, 128, 67));
                btnApostarCarro3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                btnApostarCarro3.setForeground(new java.awt.Color(255, 255, 254));
                btnApostarCarro3.setText("Carro 3");
                btnApostarCarro3.setActionCommand("Ingresar");
                btnApostarCarro3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnApostarCarro3.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnApostarCarro3ActionPerformed(evt);
                        }
                });
                ventanaBingo.add(btnApostarCarro3,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 650, 140, -1));

                btnApostarCarro4.setBackground(new java.awt.Color(147, 128, 67));
                btnApostarCarro4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                btnApostarCarro4.setForeground(new java.awt.Color(255, 255, 254));
                btnApostarCarro4.setText("Carro 4");
                btnApostarCarro4.setActionCommand("Ingresar");
                btnApostarCarro4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnApostarCarro4.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnApostarCarro4ActionPerformed(evt);
                        }
                });
                ventanaBingo.add(btnApostarCarro4,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 650, 140, -1));

                taContenidoCarrera.setBackground(new java.awt.Color(36, 38, 41));
                taContenidoCarrera.setColumns(20);
                taContenidoCarrera.setFont(new java.awt.Font("Segoe UI Emoji", 0, 18)); // NOI18N
                taContenidoCarrera.setRows(5);
                taContenidoCarrera.setText("🚙, 🚜, 🏍️, 🚚, 🚐, 🚑, 🚒, 🏎️, 🚓, 🚔, 🚕, 🚖, 🚘, 🚛\n");
                taContenidoCarrera
                                .setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 0)));
                scCarrera.setViewportView(taContenidoCarrera);

                ventanaBingo.add(scCarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, 720, 280));

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
                ventanaBingo.add(cbValorApostado, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 590, 190, 40));

                lbCuentaRegresiva.setFont(new java.awt.Font("Crabs", 1, 36)); // NOI18N
                lbCuentaRegresiva.setForeground(new java.awt.Color(227, 199, 104));
                lbCuentaRegresiva.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                ventanaBingo.add(lbCuentaRegresiva,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 180, 190, 50));

                lbApuesta.setFont(new java.awt.Font("Crabs", 1, 24)); // NOI18N
                lbApuesta.setForeground(new java.awt.Color(227, 199, 104));
                lbApuesta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbApuesta.setText("Apuesta");
                ventanaBingo.add(lbApuesta, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 560, 190, 30));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(ventanaBingo, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(ventanaBingo, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE));

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void cbValorApostadoActionPerformed(java.awt.event.ActionEvent evt) {

        }

        private void btnApostarCarro1ActionPerformed(java.awt.event.ActionEvent evt) {
                iniciarCarrera(1, Integer.valueOf(ObtenerIU.obtenerSeleccionCombo(cbValorApostado)));

        }

        private void btnApostarCarro2ActionPerformed(java.awt.event.ActionEvent evt) {
                iniciarCarrera(2, Integer.valueOf(ObtenerIU.obtenerSeleccionCombo(cbValorApostado)));

        }

        private void btnApostarCarro3ActionPerformed(java.awt.event.ActionEvent evt) {
                iniciarCarrera(3, Integer.valueOf(ObtenerIU.obtenerSeleccionCombo(cbValorApostado)));

        }

        private void btnApostarCarro4ActionPerformed(java.awt.event.ActionEvent evt) {
                iniciarCarrera(4, Integer.valueOf(ObtenerIU.obtenerSeleccionCombo(cbValorApostado)));

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
                        JOptionPane.showMessageDialog(null,
                                        "El cliente de chat no está inicializado.", "ERROR",
                                        JOptionPane.ERROR_MESSAGE);

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
                EventQueue.invokeLater(() -> {
                        new Carrera().setVisible(true);
                });
        }

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btnApostarCarro1;
        private javax.swing.JButton btnApostarCarro2;
        private javax.swing.JButton btnApostarCarro3;
        private javax.swing.JButton btnApostarCarro4;
        private javax.swing.JButton btnDepositar;
        private javax.swing.JComboBox<String> cbValorApostado;
        private javax.swing.JLabel imgEnviar;
        private javax.swing.JLabel imgVolver;
        private javax.swing.JLabel lbApuesta;
        private javax.swing.JLabel lbCarrera;
        private javax.swing.JLabel lbChat;
        private javax.swing.JLabel lbCuentaRegresiva;
        private javax.swing.JLabel lbPonerFondos;
        private javax.swing.JScrollPane scCarrera;
        private javax.swing.JScrollPane scChatCarrera;
        private javax.swing.JScrollPane scMensaje;
        private javax.swing.JTextArea taChatCarrera;
        private javax.swing.JTextArea taContenidoCarrera;
        private javax.swing.JTextArea taMensaje;
        private javax.swing.JPanel ventanaBingo;
        // End of variables declaration//GEN-END:variables
}
