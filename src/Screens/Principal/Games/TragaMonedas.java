/*
 cSpell:ignore publicacion ubicacion operacion
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Screens.Principal.Games;

import Screens.Custom.CambiarIU;
import Screens.Principal.Principal;

import java.awt.EventQueue;
import java.awt.Toolkit;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;

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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ventanaBlackJack = new javax.swing.JPanel();
        imgVolver = new javax.swing.JLabel();
        lbTragaMonedas = new javax.swing.JLabel();
        lbPonerFondos = new javax.swing.JLabel();
        btnDepositar5 = new javax.swing.JButton();
        btnDepositar6 = new javax.swing.JButton();
        btnDepositar7 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        btnDepositar8 = new javax.swing.JButton();

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
        ventanaBlackJack.add(lbTragaMonedas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1080, -1));

        lbPonerFondos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbPonerFondos.setForeground(new java.awt.Color(148, 161, 178));
        lbPonerFondos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbPonerFondos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondos.png"))); // NOI18N
        lbPonerFondos.setText("-");
        ventanaBlackJack.add(lbPonerFondos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1050, -1));

        btnDepositar5.setBackground(new java.awt.Color(147, 128, 67));
        btnDepositar5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDepositar5.setForeground(new java.awt.Color(255, 255, 254));
        btnDepositar5.setText("Cambiar la apuesta");
        btnDepositar5.setActionCommand("Ingresar");
        btnDepositar5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDepositar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepositar5ActionPerformed(evt);
            }
        });
        ventanaBlackJack.add(btnDepositar5, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 660, 200, -1));

        btnDepositar6.setBackground(new java.awt.Color(0, 153, 51));
        btnDepositar6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDepositar6.setForeground(new java.awt.Color(255, 255, 254));
        btnDepositar6.setText("A GIRAR!");
        btnDepositar6.setActionCommand("Ingresar");
        btnDepositar6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDepositar6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepositar6ActionPerformed(evt);
            }
        });
        ventanaBlackJack.add(btnDepositar6, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 590, 130, 40));

        btnDepositar7.setBackground(new java.awt.Color(147, 128, 67));
        btnDepositar7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDepositar7.setForeground(new java.awt.Color(255, 255, 254));
        btnDepositar7.setText("Apostar");
        btnDepositar7.setActionCommand("Ingresar");
        btnDepositar7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDepositar7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepositar7ActionPerformed(evt);
            }
        });
        ventanaBlackJack.add(btnDepositar7, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 660, 120, -1));

        jTextField1.setBackground(new java.awt.Color(27, 9, 5));
        jTextField1.setText("jTextField1");
        ventanaBlackJack.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, 640, 370));

        btnDepositar8.setBackground(new java.awt.Color(147, 128, 67));
        btnDepositar8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDepositar8.setForeground(new java.awt.Color(255, 255, 254));
        btnDepositar8.setText("Retirarse");
        btnDepositar8.setActionCommand("Ingresar");
        btnDepositar8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDepositar8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepositar8ActionPerformed(evt);
            }
        });
        ventanaBlackJack.add(btnDepositar8, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 660, 120, -1));

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

    private void btnDepositar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepositar5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDepositar5ActionPerformed

    private void btnDepositar6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepositar6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDepositar6ActionPerformed

    private void btnDepositar7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepositar7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDepositar7ActionPerformed

    private void btnDepositar8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepositar8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDepositar8ActionPerformed

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
    private javax.swing.JButton btnDepositar5;
    private javax.swing.JButton btnDepositar6;
    private javax.swing.JButton btnDepositar7;
    private javax.swing.JButton btnDepositar8;
    private javax.swing.JLabel imgVolver;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lbPonerFondos;
    private javax.swing.JLabel lbTragaMonedas;
    private javax.swing.JPanel ventanaBlackJack;
    // End of variables declaration//GEN-END:variables
}
