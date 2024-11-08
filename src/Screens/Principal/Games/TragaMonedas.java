/*
 cSpell:ignore publicacion ubicacion operacion
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Screens.Principal.Games;

import Screens.Custom.CambiarIU;
import Screens.Login.Login;
import Screens.Principal.Principal;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import Code.OperacionCRUD;

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

                ponerFondos();
        }

        private void ponerFondos() {
                try {
                        ArrayList<ArrayList<Object>> datos = OperacionCRUD.seleccionar(
                                        String.format("SELECT * FROM jugadores where jugador_id = %d",
                                                        Login.idUsuarioGuardar),
                                        new String[] { "fondos_jugador" });

                        CambiarIU.ponerTextoEtiqueta(lbPonerFondos, (datos.get(0).get(0) + " Fondos"));

                } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR",
                                        JOptionPane.ERROR_MESSAGE);

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
        // Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                ventanaBlackJack = new javax.swing.JPanel();
                imgVolver = new javax.swing.JLabel();
                lbTragaMonedas = new javax.swing.JLabel();
                lbPonerFondos = new javax.swing.JLabel();
                btnDepositar = new javax.swing.JButton();
                btnGirar = new javax.swing.JButton();
                btnApostar = new javax.swing.JButton();
                btnCambiarApuesta = new javax.swing.JButton();
                btnRetirarse = new javax.swing.JButton();
                lbContenido = new javax.swing.JLabel();

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

                lbTragaMonedas.setFont(new java.awt.Font("Crabs", 1, 100)); // NOI18N
                lbTragaMonedas.setForeground(new java.awt.Color(227, 199, 104));
                lbTragaMonedas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbTragaMonedas.setText("Traga Monedas");
                ventanaBlackJack.add(lbTragaMonedas,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1080, -1));

                lbPonerFondos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
                lbPonerFondos.setForeground(new java.awt.Color(148, 161, 178));
                lbPonerFondos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                lbPonerFondos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondos.png"))); // NOI18N
                lbPonerFondos.setText("-");
                ventanaBlackJack.add(lbPonerFondos,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1050, -1));

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
                ventanaBlackJack.add(btnGirar, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 590, 130, 40));

                btnApostar.setBackground(new java.awt.Color(147, 128, 67));
                btnApostar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                btnApostar.setForeground(new java.awt.Color(255, 255, 254));
                btnApostar.setText("Apostar");
                btnApostar.setActionCommand("Ingresar");
                btnApostar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnApostar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnApostarActionPerformed(evt);
                        }
                });
                ventanaBlackJack.add(btnApostar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 660, 120, -1));

                btnCambiarApuesta.setBackground(new java.awt.Color(147, 128, 67));
                btnCambiarApuesta.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                btnCambiarApuesta.setForeground(new java.awt.Color(255, 255, 254));
                btnCambiarApuesta.setText("Cambiar la apuesta");
                btnCambiarApuesta.setActionCommand("Ingresar");
                btnCambiarApuesta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnCambiarApuesta.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnCambiarApuestaActionPerformed(evt);
                        }
                });
                ventanaBlackJack.add(btnCambiarApuesta,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 660, 200, -1));

                btnRetirarse.setBackground(new java.awt.Color(147, 128, 67));
                btnRetirarse.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                btnRetirarse.setForeground(new java.awt.Color(255, 255, 254));
                btnRetirarse.setText("Retirarse");
                btnRetirarse.setActionCommand("Ingresar");
                btnRetirarse.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnRetirarse.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnRetirarseActionPerformed(evt);
                        }
                });
                ventanaBlackJack.add(btnRetirarse,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 660, 120, -1));
                ventanaBlackJack.add(lbContenido,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 170, 550, 410));

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

        private void btnCambiarApuestaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCambiarApuestaActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_btnCambiarApuestaActionPerformed

        private void btnGirarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnGirarActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_btnGirarActionPerformed

        private void btnApostarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnApostarActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_btnApostarActionPerformed

        private void btnRetirarseActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnRetirarseActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_btnRetirarseActionPerformed

        private void btnDepositarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnDepositarActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_btnDepositarActionPerformed

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
        private javax.swing.JButton btnApostar;
        private javax.swing.JButton btnCambiarApuesta;
        private javax.swing.JButton btnDepositar;
        private javax.swing.JButton btnGirar;
        private javax.swing.JButton btnRetirarse;
        private javax.swing.JLabel imgVolver;
        private javax.swing.JLabel lbContenido;
        private javax.swing.JLabel lbPonerFondos;
        private javax.swing.JLabel lbTragaMonedas;
        private javax.swing.JPanel ventanaBlackJack;
        // End of variables declaration//GEN-END:variables
}
