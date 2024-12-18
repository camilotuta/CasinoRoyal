/*
 cSpell:ignore publicacion ubicacion operacion tahoma
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main.java.com.casinoRoyal.ui.views.games.poker;

import main.java.com.casinoRoyal.service.communication.ChatClient;
import main.java.com.casinoRoyal.ui.utils.CambiarIU;
import main.java.com.casinoRoyal.ui.views.principal.Principal;
import main.java.com.casinoRoyal.ui.views.profile.PersonalProfile;
import main.java.com.casinoRoyal.ui.views.transactions.Transactions;

import java.awt.EventQueue;
import java.awt.Toolkit;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import javax.swing.JOptionPane;

/**
 *
 * @author tutaa
 */
public class Poker extends javax.swing.JFrame {

        /**
         * Creates new formPoker
         */
        private ChatClient chatClient;

        public Poker() {
                initComponents();

                this.setTitle("Poker");
                this.setResizable(false);
                this.setLocationRelativeTo(null);

                this.setIconImage(Toolkit.getDefaultToolkit()
                                .getImage(getClass().getResource("/main/resources/assets/img/icon.png")));
                ingresarChat();
                taChatPoker.setEditable(false);
                Principal.actualizarFondos(lbPonerFondos);
                new CambiarIU().setImageLabel(lbContenido, "/main/resources/assets/img/poker/pokerPartida.gif");
                Principal.ponerPersonasConectadas(lbPersonasConectadas, 1);
        }

        private void ingresarChat() {
                Thread chatThread = new Thread(() -> {
                        String nombre = PersonalProfile.obtenerNombre();
                        if (!nombre.isEmpty()) {
                                chatClient = new ChatClient(nombre, taChatPoker, taMensaje, imgEnviar, 3333);
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
                        chatClient = null;
                } else {
                        System.err.println("El cliente de chat ya está cerrado o no fue inicializado.");
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
        // Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                ventanaBingo = new javax.swing.JPanel();
                imgVolver = new javax.swing.JLabel();
                btnDepositar = new javax.swing.JButton();
                lbPoker = new javax.swing.JLabel();
                lbPonerFondos = new javax.swing.JLabel();
                lbChat = new javax.swing.JLabel();
                lbPersonasConectadas = new javax.swing.JLabel();
                scChatPoker = new javax.swing.JScrollPane();
                taChatPoker = new javax.swing.JTextArea();
                scMensaje = new javax.swing.JScrollPane();
                taMensaje = new javax.swing.JTextArea();
                imgEnviar = new javax.swing.JLabel();
                lbContenido = new javax.swing.JLabel();
                btnApostar = new javax.swing.JButton();
                btnCambiarApuesta = new javax.swing.JButton();
                btnRetirarse = new javax.swing.JButton();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                ventanaBingo.setBackground(new java.awt.Color(27, 9, 5));
                ventanaBingo.setPreferredSize(new java.awt.Dimension(1080, 720));
                ventanaBingo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
                ventanaBingo.add(imgVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

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

                lbPoker.setFont(new java.awt.Font("Crabs", 1, 100)); // NOI18N
                lbPoker.setForeground(new java.awt.Color(227, 199, 104));
                lbPoker.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPoker.setText("Poker");
                ventanaBingo.add(lbPoker, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1080, -1));

                lbPonerFondos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
                lbPonerFondos.setForeground(new java.awt.Color(148, 161, 178));
                lbPonerFondos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                lbPonerFondos.setIcon(new javax.swing.ImageIcon(
                                getClass().getResource("/main/resources/assets/img/fondos.png"))); // NOI18N
                lbPonerFondos.setText("-");
                ventanaBingo.add(lbPonerFondos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1050, -1));

                lbChat.setFont(new java.awt.Font("Crabs", 1, 48)); // NOI18N
                lbChat.setForeground(new java.awt.Color(227, 199, 104));
                lbChat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbChat.setText("Chat");
                ventanaBingo.add(lbChat, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 310, 220, -1));

                lbPersonasConectadas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                lbPersonasConectadas.setForeground(new java.awt.Color(148, 161, 178));
                lbPersonasConectadas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                lbPersonasConectadas.setIcon(new javax.swing.ImageIcon(
                                getClass().getResource("/main/resources/assets/img/personas.png"))); // NOI18N
                lbPersonasConectadas.setText("0");
                ventanaBingo.add(lbPersonasConectadas,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 340, 300, -1));

                taChatPoker.setBackground(new java.awt.Color(36, 38, 41));
                taChatPoker.setColumns(20);
                taChatPoker.setForeground(new java.awt.Color(148, 161, 178));
                taChatPoker.setLineWrap(true);
                taChatPoker.setRows(5);
                taChatPoker.setWrapStyleWord(true);
                taChatPoker.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(227, 199, 104)));
                scChatPoker.setViewportView(taChatPoker);

                ventanaBingo.add(scChatPoker, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 370, -1, 260));

                taMensaje.setBackground(new java.awt.Color(36, 38, 41));
                taMensaje.setColumns(20);
                taMensaje.setForeground(new java.awt.Color(148, 161, 178));
                taMensaje.setLineWrap(true);
                taMensaje.setRows(2);
                taMensaje.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(227, 199, 104)));
                scMensaje.setViewportView(taMensaje);

                ventanaBingo.add(scMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 650, 180, 50));

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
                ventanaBingo.add(imgEnviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 650, 40, 40));

                lbContenido.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 0)));
                ventanaBingo.add(lbContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 650, 380));

                btnApostar.setBackground(new java.awt.Color(51, 102, 1));
                btnApostar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                btnApostar.setForeground(new java.awt.Color(255, 255, 254));
                btnApostar.setText("Apostar");
                btnApostar.setActionCommand("Ingresar");
                btnApostar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                ventanaBingo.add(btnApostar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 630, 130, -1));

                btnCambiarApuesta.setBackground(new java.awt.Color(102, 1, 102));
                btnCambiarApuesta.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                btnCambiarApuesta.setForeground(new java.awt.Color(255, 255, 254));
                btnCambiarApuesta.setText("Cambiar la apuesta");
                btnCambiarApuesta.setActionCommand("Ingresar");
                btnCambiarApuesta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                ventanaBingo.add(btnCambiarApuesta,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 660, 210, -1));

                btnRetirarse.setBackground(new java.awt.Color(153, 0, 1));
                btnRetirarse.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                btnRetirarse.setForeground(new java.awt.Color(255, 255, 254));
                btnRetirarse.setText("Retirarse");
                btnRetirarse.setActionCommand("Ingresar");
                btnRetirarse.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                ventanaBingo.add(btnRetirarse, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 630, 120, -1));

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

        private void btnDepositarActionPerformed(java.awt.event.ActionEvent evt) {
                Transactions transactions = new Transactions();
                transactions.setVisible(true);
                Principal.pantallaAnterior = this;
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
                EventQueue.invokeLater(() -> new Poker().setVisible(true));
        }

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btnApostar;
        private javax.swing.JButton btnCambiarApuesta;
        private javax.swing.JButton btnDepositar;
        private javax.swing.JButton btnRetirarse;
        private javax.swing.JLabel imgEnviar;
        private javax.swing.JLabel imgVolver;
        private javax.swing.JLabel lbChat;
        private javax.swing.JLabel lbContenido;
        private javax.swing.JLabel lbPersonasConectadas;
        private javax.swing.JLabel lbPoker;
        private javax.swing.JLabel lbPonerFondos;
        private javax.swing.JScrollPane scChatPoker;
        private javax.swing.JScrollPane scMensaje;
        private javax.swing.JTextArea taChatPoker;
        private javax.swing.JTextArea taMensaje;
        private javax.swing.JPanel ventanaBingo;
        // End of variables declaration//GEN-END:variables
}
