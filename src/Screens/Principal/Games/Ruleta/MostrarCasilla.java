/*
 cSpell:ignore publicacion ubicacion operacion numeros tahoma
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Screens.Principal.Games.Ruleta;

import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 *
 * @author tutaa
 */
public class MostrarCasilla extends javax.swing.JFrame {

        /**
         * Creates new form MostrarCasilla
         * 
         * @param numero
         * @param r
         * @param g
         * @param b
         */

        public MostrarCasilla(int numero, int r, int g, int b) {
                initComponents();

                this.setTitle("Ruleta");
                this.setResizable(false);
                this.setLocationRelativeTo(null);

                this.setIconImage(Toolkit.getDefaultToolkit()
                                .getImage(getClass().getResource("/img/icon.png")));
                panelCasilla.setBackground(new java.awt.Color(r, g, b));
                lbNumeroCasilla.setText(String.valueOf(numero));
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

                ventanaMostrarCasilla = new javax.swing.JPanel();
                panelCasilla = new javax.swing.JPanel();
                lbNumeroCasilla = new javax.swing.JLabel();

                setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

                ventanaMostrarCasilla.setBackground(new java.awt.Color(27, 9, 5));
                ventanaMostrarCasilla.setPreferredSize(new java.awt.Dimension(1080, 720));
                ventanaMostrarCasilla.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                lbNumeroCasilla.setFont(new java.awt.Font("Segoe UI", 1, 60)); // NOI18N
                lbNumeroCasilla.setForeground(new java.awt.Color(255, 255, 255));
                lbNumeroCasilla.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbNumeroCasilla.setText("-");

                javax.swing.GroupLayout panelCasillaLayout = new javax.swing.GroupLayout(panelCasilla);
                panelCasilla.setLayout(panelCasillaLayout);
                panelCasillaLayout.setHorizontalGroup(
                                panelCasillaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panelCasillaLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(lbNumeroCasilla,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                108, Short.MAX_VALUE)
                                                                .addContainerGap()));
                panelCasillaLayout.setVerticalGroup(
                                panelCasillaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panelCasillaLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(lbNumeroCasilla,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                108, Short.MAX_VALUE)
                                                                .addContainerGap()));

                ventanaMostrarCasilla.add(panelCasilla,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 120, 120));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(ventanaMostrarCasilla,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 220,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(ventanaMostrarCasilla,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 185,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE));

                pack();
        }// </editor-fold>//GEN-END:initComponents

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JLabel lbNumeroCasilla;
        private javax.swing.JPanel panelCasilla;
        private javax.swing.JPanel ventanaMostrarCasilla;
        // End of variables declaration//GEN-END:variables
}
