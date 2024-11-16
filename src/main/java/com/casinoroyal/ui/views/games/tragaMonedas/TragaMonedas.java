/*
 cSpell:ignore publicacion ubicacion operacion tahoma tragamonedas numeros
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main.java.com.casinoRoyal.ui.views.games.tragaMonedas;

import main.java.com.casinoRoyal.ui.utils.CambiarIU;
import main.java.com.casinoRoyal.ui.utils.ObtenerIU;
import main.java.com.casinoRoyal.ui.utils.SoundPlay;
import main.java.com.casinoRoyal.game.tragaMonedas.CasillasTragaMonedas;
import main.java.com.casinoRoyal.ui.views.principal.Principal;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JOptionPane;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import main.java.com.casinoRoyal.ui.views.profile.PersonalProfile;
import main.java.com.casinoRoyal.ui.views.transactions.Transactions;

/**
 *
 * @author tutaa
 */
public class TragaMonedas extends javax.swing.JFrame {

        /**
         * Creates new form TragaMonedas
         */
        public TragaMonedas() {
                initComponents();

                this.setTitle("BlackJack");
                this.setResizable(false);
                this.setLocationRelativeTo(null);

                this.setIconImage(Toolkit.getDefaultToolkit()
                                .getImage(getClass().getResource("/img/icon.png")));

                ponerCasillas();
                Principal.ponerFondos(lbPonerFondos);
        }

        private void ponerCasillas() {
                CambiarIU.setImageLabel(lbCasilla1, "src/img/tragamonedas/0.png");
                CambiarIU.setImageLabel(lbCasilla2, "src/img/tragamonedas/1.png");
                CambiarIU.setImageLabel(lbCasilla3, "src/img/tragamonedas/2.png");
        }

        private void girarTragamonedas(double valorApostado) {

                if (PersonalProfile.fondosSuficientes(
                                Double.parseDouble(ObtenerIU.obtenerSeleccionCombo(cbValorApostado)))) {
                        Transactions.restarFondos(valorApostado);
                        Principal.ponerFondos(lbPonerFondos);

                        CambiarIU.setImageLabel(lbCasilla1, "src/img/tragamonedas/casillaGirando.gif");
                        CambiarIU.setImageLabel(lbCasilla2, "src/img/tragamonedas/casillaGirando.gif");
                        CambiarIU.setImageLabel(lbCasilla3, "src/img/tragamonedas/casillaGirando.gif");

                        SoundPlay.reproducir("src/sound/tragamonedasGirando.wav");
                        CambiarIU.deshabilitarBotones(btnGirar, btnAllIn);
                        int numeroCasilla1 = CasillasTragaMonedas.casillaAleatorio();
                        int numeroCasilla2 = CasillasTragaMonedas.casillaAleatorio();
                        int numeroCasilla3 = CasillasTragaMonedas.casillaAleatorio();

                        final double[] valorGanado = { 0 };

                        new Thread(() -> {
                                try {
                                        Thread.sleep(4000);

                                        if (numeroCasilla1 == numeroCasilla2 && numeroCasilla3 == numeroCasilla1) {
                                                valorGanado[0] = valorApostado * 10;

                                        }

                                        CambiarIU.setImageLabel(lbCasilla1,
                                                        "src/img/tragamonedas/" + numeroCasilla1 + ".png");
                                        CambiarIU.setImageLabel(lbCasilla2,
                                                        "src/img/tragamonedas/" + numeroCasilla2 + ".png");
                                        CambiarIU.setImageLabel(lbCasilla3,
                                                        "src/img/tragamonedas/" + numeroCasilla3 + ".png");

                                        Thread.sleep(2000);
                                        String mensaje = "Ganancia: $" + valorGanado[0];

                                        JOptionPane.showMessageDialog(this, mensaje, "Resultado de la partida",
                                                        JOptionPane.INFORMATION_MESSAGE);

                                        CambiarIU.habilitarBotones(btnGirar, btnAllIn);
                                        Transactions.sumarFondos(valorGanado[0]);
                                        Principal.ponerFondos(lbPonerFondos);
                                } catch (InterruptedException e) {
                                        JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR",
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
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                ventanaBlackJack = new javax.swing.JPanel();
                imgVolver = new javax.swing.JLabel();
                btnDepositar = new javax.swing.JButton();
                lbTragaMonedas = new javax.swing.JLabel();
                lbPonerFondos = new javax.swing.JLabel();
                panelJuego = new javax.swing.JPanel();
                jPanel1 = new javax.swing.JPanel();
                lbCasilla1 = new javax.swing.JLabel();
                jPanel2 = new javax.swing.JPanel();
                lbCasilla2 = new javax.swing.JLabel();
                jPanel3 = new javax.swing.JPanel();
                lbCasilla3 = new javax.swing.JLabel();
                btnAllIn = new javax.swing.JButton();
                btnGirar = new javax.swing.JButton();
                lbApuesta = new javax.swing.JLabel();
                cbValorApostado = new javax.swing.JComboBox<>();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                ventanaBlackJack.setBackground(new java.awt.Color(27, 9, 5));
                ventanaBlackJack.setPreferredSize(new java.awt.Dimension(1080, 720));
                ventanaBlackJack.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

                lbTragaMonedas.setFont(new java.awt.Font("Crabs", 1, 100)); // NOI18N
                lbTragaMonedas.setForeground(new java.awt.Color(227, 199, 104));
                lbTragaMonedas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbTragaMonedas.setText("Traga Monedas");
                ventanaBlackJack.add(lbTragaMonedas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1080, -1));

                lbPonerFondos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
                lbPonerFondos.setForeground(new java.awt.Color(148, 161, 178));
                lbPonerFondos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                lbPonerFondos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondos.png"))); // NOI18N
                lbPonerFondos.setText("-");
                ventanaBlackJack.add(lbPonerFondos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1050, -1));

                panelJuego.setBackground(new java.awt.Color(36, 38, 41));
                panelJuego.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                jPanel1.setBackground(new java.awt.Color(51, 51, 51));

                lbCasilla1.setBackground(new java.awt.Color(51, 51, 0));
                lbCasilla1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbCasilla1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tragaMonedas/0.png"))); // NOI18N

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 130, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(lbCasilla1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                );
                jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 130, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(lbCasilla1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                );

                panelJuego.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 130, 130));

                jPanel2.setBackground(new java.awt.Color(51, 51, 51));

                lbCasilla2.setBackground(new java.awt.Color(51, 51, 0));
                lbCasilla2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbCasilla2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tragaMonedas/1.png"))); // NOI18N

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 130, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(lbCasilla2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                );
                jPanel2Layout.setVerticalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 130, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(lbCasilla2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                );

                panelJuego.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, 130, 130));

                jPanel3.setBackground(new java.awt.Color(51, 51, 51));

                lbCasilla3.setBackground(new java.awt.Color(51, 51, 0));
                lbCasilla3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbCasilla3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tragaMonedas/2.png"))); // NOI18N

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 130, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(lbCasilla3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                );
                jPanel3Layout.setVerticalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 130, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(lbCasilla3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                );

                panelJuego.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, 130, 130));

                ventanaBlackJack.add(panelJuego, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 230, 610, 240));

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
                ventanaBlackJack.add(btnAllIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 620, 140, 50));

                btnGirar.setBackground(new java.awt.Color(0, 153, 51));
                btnGirar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                btnGirar.setForeground(new java.awt.Color(255, 255, 254));
                btnGirar.setText("A GIRAR!");
                btnGirar.setActionCommand("Ingresar");
                btnGirar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnGirar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnGirarActionPerformed(evt);
                        }
                });
                ventanaBlackJack.add(btnGirar, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 620, 140, 50));

                lbApuesta.setFont(new java.awt.Font("Crabs", 1, 24)); // NOI18N
                lbApuesta.setForeground(new java.awt.Color(227, 199, 104));
                lbApuesta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbApuesta.setText("Apuesta");
                ventanaBlackJack.add(lbApuesta, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 520, 190, 30));

                cbValorApostado.setBackground(new java.awt.Color(27, 9, 5));
                cbValorApostado.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                cbValorApostado.setForeground(new java.awt.Color(224, 195, 102));
                cbValorApostado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "100", "200", "500", "1000", "2000", "5000", "10000", "25000", "50000", "100000" }));
                ventanaBlackJack.add(cbValorApostado, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 550, 190, 40));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(ventanaBlackJack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(ventanaBlackJack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void btnAllInActionPerformed(java.awt.event.ActionEvent evt) {
                cbValorApostado.setSelectedIndex(0);
                girarTragamonedas(PersonalProfile.obtenerFondos());

        }

        private void btnGirarActionPerformed(java.awt.event.ActionEvent evt) {
                girarTragamonedas(Integer.parseInt(ObtenerIU.obtenerSeleccionCombo(cbValorApostado)));

        }

        private void btnDepositarActionPerformed(java.awt.event.ActionEvent evt) {
                Transactions transactions = new Transactions();
                transactions.setVisible(true);
                this.setVisible(false);
        }

        private void imgVolverMouseEntered(java.awt.event.MouseEvent evt) {
                CambiarIU.setImageLabel(imgVolver, "src/img/volverHover.png");
        }

        private void imgVolverMouseExited(java.awt.event.MouseEvent evt) {
                CambiarIU.setImageLabel(imgVolver, "src/img/volver.png");
        }

        private void imgVolverMouseClicked(java.awt.event.MouseEvent evt) {
                Principal principal = new Principal();
                principal.setVisible(true);
                this.setVisible(false);
        }

        /**
         * @param args the command line arguments
         */
        public static void main(String args[]) {
                FlatMacDarkLaf.setup();
                EventQueue.invokeLater(() -> new TragaMonedas().setVisible(true));
        }

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btnAllIn;
        private javax.swing.JButton btnDepositar;
        private javax.swing.JButton btnGirar;
        private javax.swing.JComboBox<String> cbValorApostado;
        private javax.swing.JLabel imgVolver;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JPanel jPanel3;
        private javax.swing.JLabel lbApuesta;
        private javax.swing.JLabel lbCasilla1;
        private javax.swing.JLabel lbCasilla2;
        private javax.swing.JLabel lbCasilla3;
        private javax.swing.JLabel lbPonerFondos;
        private javax.swing.JLabel lbTragaMonedas;
        private javax.swing.JPanel panelJuego;
        private javax.swing.JPanel ventanaBlackJack;
        // End of variables declaration//GEN-END:variables
}
