/*
 cSpell:ignore publicacion ubicacion operacion
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Screens.Principal.Games;

import Code.ChatClient;
import Code.OperacionCRUD;
import Screens.Custom.CambiarIU;
import Screens.Login.Login;
import Screens.Principal.Principal;

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
                                .getImage(getClass().getResource("/img/icon.png")));
                ingresarChat();
                taChatBlackJack.setEditable(false);

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
                                chatClient = new ChatClient(nombre, taChatBlackJack, taMensaje, imgEnviar, 2222);
                        } else {
                                System.out.println("No se pudo obtener el nombre del jugador.");
                        }
                });
                chatThread.start();
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ventanaBlackJack = new javax.swing.JPanel();
        lbChat = new javax.swing.JLabel();
        imgVolver = new javax.swing.JLabel();
        scChatBlackJack = new javax.swing.JScrollPane();
        taChatBlackJack = new javax.swing.JTextArea();
        scMensaje = new javax.swing.JScrollPane();
        taMensaje = new javax.swing.JTextArea();
        imgEnviar = new javax.swing.JLabel();
        lbContenido = new javax.swing.JLabel();
        lbBlackJack = new javax.swing.JLabel();
        btnDepositar5 = new javax.swing.JButton();
        btnDepositar6 = new javax.swing.JButton();
        lbPonerFondos = new javax.swing.JLabel();
        btnDepositar7 = new javax.swing.JButton();
        btnDepositar8 = new javax.swing.JButton();
        btnDepositar9 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ventanaBlackJack.setBackground(new java.awt.Color(27, 9, 5));
        ventanaBlackJack.setPreferredSize(new java.awt.Dimension(1080, 720));
        ventanaBlackJack.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbChat.setFont(new java.awt.Font("Crabs", 1, 48)); // NOI18N
        lbChat.setForeground(new java.awt.Color(227, 199, 104));
        lbChat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbChat.setText("Chat");
        ventanaBlackJack.add(lbChat, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 310, 220, -1));

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

        taChatBlackJack.setBackground(new java.awt.Color(36, 38, 41));
        taChatBlackJack.setColumns(20);
        taChatBlackJack.setForeground(new java.awt.Color(148, 161, 178));
        taChatBlackJack.setLineWrap(true);
        taChatBlackJack.setRows(5);
        taChatBlackJack.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(227, 199, 104)));
        scChatBlackJack.setViewportView(taChatBlackJack);

        ventanaBlackJack.add(scChatBlackJack, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 370, -1, 260));

        taMensaje.setBackground(new java.awt.Color(36, 38, 41));
        taMensaje.setColumns(20);
        taMensaje.setForeground(new java.awt.Color(148, 161, 178));
        taMensaje.setLineWrap(true);
        taMensaje.setRows(2);
        taMensaje.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(227, 199, 104)));
        scMensaje.setViewportView(taMensaje);

        ventanaBlackJack.add(scMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 650, 180, 50));

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
        ventanaBlackJack.add(imgEnviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 650, 40, 40));
        ventanaBlackJack.add(lbContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 550, 520));

        lbBlackJack.setFont(new java.awt.Font("Crabs", 1, 100)); // NOI18N
        lbBlackJack.setForeground(new java.awt.Color(227, 199, 104));
        lbBlackJack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbBlackJack.setText("BlackJack");
        ventanaBlackJack.add(lbBlackJack, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1080, -1));

        btnDepositar5.setBackground(new java.awt.Color(204, 51, 0));
        btnDepositar5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDepositar5.setForeground(new java.awt.Color(255, 255, 254));
        btnDepositar5.setText("Plantarse");
        btnDepositar5.setActionCommand("Ingresar");
        btnDepositar5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDepositar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepositar5ActionPerformed(evt);
            }
        });
        ventanaBlackJack.add(btnDepositar5, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 580, 140, 40));

        btnDepositar6.setBackground(new java.awt.Color(147, 128, 67));
        btnDepositar6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDepositar6.setForeground(new java.awt.Color(255, 255, 254));
        btnDepositar6.setText("Apostar");
        btnDepositar6.setActionCommand("Ingresar");
        btnDepositar6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDepositar6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepositar6ActionPerformed(evt);
            }
        });
        ventanaBlackJack.add(btnDepositar6, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 660, 100, -1));

        lbPonerFondos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbPonerFondos.setForeground(new java.awt.Color(148, 161, 178));
        lbPonerFondos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbPonerFondos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondos.png"))); // NOI18N
        lbPonerFondos.setText("-");
        ventanaBlackJack.add(lbPonerFondos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1050, -1));

        btnDepositar7.setBackground(new java.awt.Color(147, 128, 67));
        btnDepositar7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDepositar7.setForeground(new java.awt.Color(255, 255, 254));
        btnDepositar7.setText("Retirarse");
        btnDepositar7.setActionCommand("Ingresar");
        btnDepositar7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDepositar7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepositar7ActionPerformed(evt);
            }
        });
        ventanaBlackJack.add(btnDepositar7, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 660, 120, -1));

        btnDepositar8.setBackground(new java.awt.Color(147, 128, 67));
        btnDepositar8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDepositar8.setForeground(new java.awt.Color(255, 255, 254));
        btnDepositar8.setText("Cambiar la apuesta");
        btnDepositar8.setActionCommand("Ingresar");
        btnDepositar8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDepositar8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepositar8ActionPerformed(evt);
            }
        });
        ventanaBlackJack.add(btnDepositar8, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 660, 200, -1));

        btnDepositar9.setBackground(new java.awt.Color(0, 153, 51));
        btnDepositar9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDepositar9.setForeground(new java.awt.Color(255, 255, 254));
        btnDepositar9.setText("Pedir cartas");
        btnDepositar9.setActionCommand("Ingresar");
        btnDepositar9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDepositar9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepositar9ActionPerformed(evt);
            }
        });
        ventanaBlackJack.add(btnDepositar9, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 580, 150, 40));

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

    private void btnDepositar9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepositar9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDepositar9ActionPerformed

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
                EventQueue.invokeLater(() -> new BlackJack().setVisible(true));
        }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDepositar5;
    private javax.swing.JButton btnDepositar6;
    private javax.swing.JButton btnDepositar7;
    private javax.swing.JButton btnDepositar8;
    private javax.swing.JButton btnDepositar9;
    private javax.swing.JLabel imgEnviar;
    private javax.swing.JLabel imgVolver;
    private javax.swing.JLabel lbBlackJack;
    private javax.swing.JLabel lbChat;
    private javax.swing.JLabel lbContenido;
    private javax.swing.JLabel lbPonerFondos;
    private javax.swing.JScrollPane scChatBlackJack;
    private javax.swing.JScrollPane scMensaje;
    private javax.swing.JTextArea taChatBlackJack;
    private javax.swing.JTextArea taMensaje;
    private javax.swing.JPanel ventanaBlackJack;
    // End of variables declaration//GEN-END:variables
}
