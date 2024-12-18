/*
 cSpell:ignore publicacion ubicacion operacion tahoma
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main.java.com.casinoRoyal.ui.views.games.blackJack;

import main.java.com.casinoRoyal.service.communication.ChatClient;
import main.java.com.casinoRoyal.ui.utils.CambiarIU;
import main.java.com.casinoRoyal.ui.utils.ObtenerIU;
import main.java.com.casinoRoyal.ui.utils.SoundPlay;
import main.java.com.casinoRoyal.game.blackJack.PartidaBlackJack;
import main.java.com.casinoRoyal.ui.views.principal.Principal;
import main.java.com.casinoRoyal.ui.views.profile.PersonalProfile;
import main.java.com.casinoRoyal.ui.views.transactions.Transactions;

import java.awt.EventQueue;
import java.awt.Toolkit;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author tutaa
 */
public class BlackJack extends javax.swing.JFrame {

        /**
         * Creates new form BlackJack
         */
        private ChatClient chatClient;

        public BlackJack() {
                initComponents();

                this.setTitle("BlackJack");
                this.setResizable(false);
                this.setLocationRelativeTo(null);

                this.setIconImage(Toolkit.getDefaultToolkit()
                                .getImage(getClass().getResource("/main/resources/assets/img/icon.png")));
                ingresarChat();
                taChatBlackJack.setEditable(false);
                Principal.actualizarFondos(lbPonerFondos);
                Principal.ponerPersonasConectadas(lbPersonasConectadas, 3);
        }

        private void ingresarChat() {
                Thread chatThread = new Thread(() -> {
                        String nombre = PersonalProfile.obtenerNombre();
                        if (!nombre.isEmpty()) {
                                chatClient = new ChatClient(nombre, taChatBlackJack, taMensaje, imgEnviar, 2222);
                        } else {
                                JOptionPane.showMessageDialog(this,
                                                "No se pudo obtener el nombre del jugador.", "ERROR",
                                                JOptionPane.ERROR_MESSAGE);
                        }
                });
                chatThread.start();
        }

        private void cerrarChat() {
                if (chatClient != null) {
                        chatClient.close();
                        chatClient = null; // Liberar el recurso
                } else {
                        System.err.println("El cliente de chat ya está cerrado o no fue inicializado.");
                }
        }

        private void jugarBlackJack(double valorApostado) {

                if (PersonalProfile.fondosSuficientes(
                                Double.parseDouble(ObtenerIU.obtenerSeleccionCombo(cbValorApostado)))) {

                        if (PersonalProfile.fondosSuficientes(valorApostado)) {
                                Transactions.restarFondos(valorApostado);
                                new SoundPlay().reproducir("/main/resources/assets/sound/blackjack.wav");
                                SwingUtilities.invokeLater(() -> CambiarIU.deshabilitarBotones(btnJugar, btnAllIn));

                                new Thread(() -> {
                                        try {

                                                PartidaBlackJack PBJ = new PartidaBlackJack(panelJuego, valorApostado);

                                                Thread.sleep(2000);

                                                while (PBJ.partidaEnCurso) {

                                                        SwingUtilities.invokeLater(() -> {

                                                        });
                                                        Thread.sleep(100);
                                                }

                                                SwingUtilities.invokeLater(
                                                                () -> CambiarIU.habilitarBotones(btnJugar, btnAllIn));

                                        } catch (InterruptedException e) {

                                                SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(this,
                                                                e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE));
                                        } catch (Exception e) {

                                                SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(this,
                                                                e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE));
                                        }
                                }).start();
                        } else {

                                JOptionPane.showMessageDialog(this, "No tienes suficientes fondos para esta apuesta.",
                                                "ERROR",
                                                JOptionPane.ERROR_MESSAGE);
                        }
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
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                ventanaBlackJack = new javax.swing.JPanel();
                imgVolver = new javax.swing.JLabel();
                btnDepositar = new javax.swing.JButton();
                lbBlackJack = new javax.swing.JLabel();
                lbPonerFondos = new javax.swing.JLabel();
                lbChat = new javax.swing.JLabel();
                lbPersonasConectadas = new javax.swing.JLabel();
                scChatBlackJack = new javax.swing.JScrollPane();
                taChatBlackJack = new javax.swing.JTextArea();
                scMensaje = new javax.swing.JScrollPane();
                taMensaje = new javax.swing.JTextArea();
                imgEnviar = new javax.swing.JLabel();
                panelJuego = new javax.swing.JPanel();
                btnAllIn = new javax.swing.JButton();
                btnJugar = new javax.swing.JButton();
                lbApuesta = new javax.swing.JLabel();
                cbValorApostado = new javax.swing.JComboBox<>();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                ventanaBlackJack.setBackground(new java.awt.Color(27, 9, 5));
                ventanaBlackJack.setPreferredSize(new java.awt.Dimension(1080, 720));
                ventanaBlackJack.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                imgVolver.setIcon(new javax.swing.ImageIcon(
                                getClass().getResource("/main/resources/assets/img/volver.png"))); // NOI18N
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
                ventanaBlackJack.add(imgVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

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
                ventanaBlackJack.add(btnDepositar, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 50, -1, -1));

                lbBlackJack.setFont(new java.awt.Font("Crabs", 1, 100)); // NOI18N
                lbBlackJack.setForeground(new java.awt.Color(227, 199, 104));
                lbBlackJack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbBlackJack.setText("BlackJack");
                ventanaBlackJack.add(lbBlackJack, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1080, -1));

                lbPonerFondos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
                lbPonerFondos.setForeground(new java.awt.Color(148, 161, 178));
                lbPonerFondos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                lbPonerFondos.setIcon(new javax.swing.ImageIcon(
                                getClass().getResource("/main/resources/assets/img/fondos.png"))); // NOI18N
                lbPonerFondos.setText("-");
                ventanaBlackJack.add(lbPonerFondos,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1050, -1));

                lbChat.setFont(new java.awt.Font("Crabs", 1, 48)); // NOI18N
                lbChat.setForeground(new java.awt.Color(227, 199, 104));
                lbChat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbChat.setText("Chat");
                ventanaBlackJack.add(lbChat, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 310, 220, -1));

                lbPersonasConectadas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                lbPersonasConectadas.setForeground(new java.awt.Color(148, 161, 178));
                lbPersonasConectadas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                lbPersonasConectadas.setIcon(new javax.swing.ImageIcon(
                                getClass().getResource("/main/resources/assets/img/personas.png"))); // NOI18N
                lbPersonasConectadas.setText("0");
                ventanaBlackJack.add(lbPersonasConectadas,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 340, 300, -1));

                taChatBlackJack.setBackground(new java.awt.Color(36, 38, 41));
                taChatBlackJack.setColumns(20);
                taChatBlackJack.setForeground(new java.awt.Color(148, 161, 178));
                taChatBlackJack.setLineWrap(true);
                taChatBlackJack.setRows(5);
                taChatBlackJack.setBorder(
                                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(227, 199, 104)));
                scChatBlackJack.setViewportView(taChatBlackJack);

                ventanaBlackJack.add(scChatBlackJack,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 370, -1, 260));

                taMensaje.setBackground(new java.awt.Color(36, 38, 41));
                taMensaje.setColumns(20);
                taMensaje.setForeground(new java.awt.Color(148, 161, 178));
                taMensaje.setLineWrap(true);
                taMensaje.setRows(2);
                taMensaje.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(227, 199, 104)));
                scMensaje.setViewportView(taMensaje);

                ventanaBlackJack.add(scMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 650, 180, 50));

                imgEnviar.setIcon(new javax.swing.ImageIcon(
                                getClass().getResource("/main/resources/assets/img/enviar.png"))); // NOI18N
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
                ventanaBlackJack.add(imgEnviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 650, 40, 40));

                panelJuego.setBackground(new java.awt.Color(36, 38, 41));
                panelJuego.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 0)));

                javax.swing.GroupLayout panelJuegoLayout = new javax.swing.GroupLayout(panelJuego);
                panelJuego.setLayout(panelJuegoLayout);
                panelJuegoLayout.setHorizontalGroup(
                                panelJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 758, Short.MAX_VALUE));
                panelJuegoLayout.setVerticalGroup(
                                panelJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 428, Short.MAX_VALUE));

                ventanaBlackJack.add(panelJuego, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 760, 430));

                btnAllIn.setBackground(new java.awt.Color(139, 0, 0));
                btnAllIn.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
                btnAllIn.setForeground(new java.awt.Color(255, 255, 254));
                btnAllIn.setText("All In");
                btnAllIn.setActionCommand("Ingresar");
                btnAllIn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 215, 0), 1, true));
                btnAllIn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnAllIn.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnAllInActionPerformed(evt);
                        }
                });
                ventanaBlackJack.add(btnAllIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 620, 140, 50));

                btnJugar.setBackground(new java.awt.Color(102, 1, 51));
                btnJugar.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
                btnJugar.setForeground(new java.awt.Color(255, 255, 254));
                btnJugar.setText("Jugar");
                btnJugar.setActionCommand("Ingresar");
                btnJugar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnJugar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnJugarActionPerformed(evt);
                        }
                });
                ventanaBlackJack.add(btnJugar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 620, 140, 50));

                lbApuesta.setFont(new java.awt.Font("Crabs", 1, 24)); // NOI18N
                lbApuesta.setForeground(new java.awt.Color(227, 199, 104));
                lbApuesta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbApuesta.setText("Apuesta");
                ventanaBlackJack.add(lbApuesta, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 600, 190, 30));

                cbValorApostado.setBackground(new java.awt.Color(27, 9, 5));
                cbValorApostado.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                cbValorApostado.setForeground(new java.awt.Color(224, 195, 102));
                cbValorApostado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "100", "200", "500",
                                "1000", "2000", "5000", "10000", "25000", "50000", "100000" }));
                ventanaBlackJack.add(cbValorApostado,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 630, 190, 40));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(ventanaBlackJack, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(ventanaBlackJack, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE));

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void btnAllInActionPerformed(java.awt.event.ActionEvent evt) {
                cbValorApostado.setSelectedIndex(0);
                jugarBlackJack(PersonalProfile.obtenerFondos());

        }

        private void btnJugarActionPerformed(java.awt.event.ActionEvent evt) {
                jugarBlackJack(Integer.parseInt(ObtenerIU.obtenerSeleccionCombo(cbValorApostado)));

        }

        private void btnDepositarActionPerformed(java.awt.event.ActionEvent evt) {
                Transactions transactions = new Transactions();
                Principal.pantallaAnterior = this;
                transactions.setVisible(true);
                this.setVisible(false);
        }

        private void imgEnviarMouseClicked(java.awt.event.MouseEvent evt) {

        }

        private void imgEnviarMouseEntered(java.awt.event.MouseEvent evt) {
                new CambiarIU().setImageLabel(imgEnviar, "/main/resources/assets/img/enviarHover.png");

        }

        private void imgEnviarMouseExited(java.awt.event.MouseEvent evt) {
                new CambiarIU().setImageLabel(imgEnviar, "/main/resources/assets/img/enviar.png");

        }

        private void imgVolverMouseEntered(java.awt.event.MouseEvent evt) {
                new CambiarIU().setImageLabel(imgVolver, "/main/resources/assets/img/volverHover.png");
        }

        private void imgVolverMouseExited(java.awt.event.MouseEvent evt) {
                new CambiarIU().setImageLabel(imgVolver, "/main/resources/assets/img/volver.png");
        }

        private void imgVolverMouseClicked(java.awt.event.MouseEvent evt) {
                cerrarChat();
                if (Principal.pantallaAnterior != null) {
                        Principal.pantallaAnterior.setVisible(true);
                } else {
                        JOptionPane.showMessageDialog(this, "No hay una pantalla anterior para volver.", "Aviso",
                                        JOptionPane.WARNING_MESSAGE);
                }
                dispose();
        }

        /**
         * @param args the command line arguments
         */
        public static void main(String args[]) {
                FlatMacDarkLaf.setup();
                EventQueue.invokeLater(() -> new BlackJack().setVisible(true));
        }

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btnAllIn;
        private javax.swing.JButton btnDepositar;
        private javax.swing.JButton btnJugar;
        private javax.swing.JComboBox<String> cbValorApostado;
        private javax.swing.JLabel imgEnviar;
        private javax.swing.JLabel imgVolver;
        private javax.swing.JLabel lbApuesta;
        private javax.swing.JLabel lbBlackJack;
        private javax.swing.JLabel lbChat;
        private javax.swing.JLabel lbPersonasConectadas;
        private javax.swing.JLabel lbPonerFondos;
        private javax.swing.JPanel panelJuego;
        private javax.swing.JScrollPane scChatBlackJack;
        private javax.swing.JScrollPane scMensaje;
        private javax.swing.JTextArea taChatBlackJack;
        private javax.swing.JTextArea taMensaje;
        private javax.swing.JPanel ventanaBlackJack;
        // End of variables declaration//GEN-END:variables
}
