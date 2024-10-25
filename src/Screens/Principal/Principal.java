/*
 cSpell:ignore publicacion ubicacion operacion
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Screens.Principal;

import Screens.Custom.CambiarIU;
import Screens.Login.Login;
import Screens.Profile.PersonalProfile;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.ArrayList;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;

/**
 *
 * @author tutaa
 */
public class Principal extends javax.swing.JFrame {

        /**
         * Creates new form Principal
         */
        public Principal() {
                initComponents();

                this.setTitle("Pantalla Principal");
                this.setResizable(false);
                this.setLocationRelativeTo(null);

                this.setIconImage(Toolkit.getDefaultToolkit()
                                .getImage(getClass().getResource("/img/icon.png")));
                textosLabels();

        }

        private ArrayList<ArrayList<Object>> solicitarJuegos() {
                ArrayList<ArrayList<Object>> juegos = new ArrayList<>();
                // try {
                // juegos = OperacionPublicacion
                // .seleccionar("SELECT * FROM incidentes_ambientales ORDER BY id_incidente
                // DESC",
                // new String[] { "id_incidente", "id_usuario", "tipo", "fecha",
                // "departamento",
                // "ciudad", "informacion", "hora" });
                // return juegos;
                // } catch (SQLException e) {
                // e.printStackTrace();
                // }
                return juegos;
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
        // Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                ventanaPrincipal = new javax.swing.JPanel();
                imgReload = new javax.swing.JLabel();
                panelMenu = new javax.swing.JPanel();
                imgCasa = new javax.swing.JLabel();
                imgVolver = new javax.swing.JLabel();
                imgUsuario = new javax.swing.JLabel();
                imgMenuBar = new javax.swing.JLabel();
                lbJuegos = new javax.swing.JLabel();
                scrollPublicaciones = new Screens.Custom.ScrollPaneWin11();
                panelPublicaciones = new javax.swing.JPanel();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                ventanaPrincipal.setBackground(new java.awt.Color(27, 9, 5));
                ventanaPrincipal.setPreferredSize(new java.awt.Dimension(1080, 720));
                ventanaPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                imgReload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/reload.png"))); // NOI18N
                imgReload.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                imgReload.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                imgReloadMouseClicked(evt);
                        }
                });
                ventanaPrincipal.add(imgReload, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 180, -1, -1));

                panelMenu.setBackground(new java.awt.Color(22, 22, 26));
                panelMenu.setOpaque(false);
                panelMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                imgCasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/casaHover.png"))); // NOI18N
                imgCasa.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                panelMenu.add(imgCasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

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
                panelMenu.add(imgVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

                imgUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/usuario.png"))); // NOI18N
                imgUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                imgUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                imgUsuarioMouseClicked(evt);
                        }

                        public void mouseEntered(java.awt.event.MouseEvent evt) {
                                imgUsuarioMouseEntered(evt);
                        }

                        public void mouseExited(java.awt.event.MouseEvent evt) {
                                imgUsuarioMouseExited(evt);
                        }
                });
                panelMenu.add(imgUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

                imgMenuBar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                imgMenuBar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lineaVertical.png"))); // NOI18N
                imgMenuBar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
                panelMenu.add(imgMenuBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, -1));

                ventanaPrincipal.add(panelMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 230, 60, 220));

                lbJuegos.setFont(new java.awt.Font("Crabs", 1, 100)); // NOI18N
                lbJuegos.setForeground(new java.awt.Color(227, 199, 104));
                lbJuegos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbJuegos.setText("Juegos");
                ventanaPrincipal.add(lbJuegos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1080, -1));

                panelPublicaciones.setBackground(new java.awt.Color(36, 38, 41));
                panelPublicaciones.setMinimumSize(new java.awt.Dimension(350, 100000));
                panelPublicaciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                scrollPublicaciones.setViewportView(panelPublicaciones);

                ventanaPrincipal.add(scrollPublicaciones,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, 890, 480));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(ventanaPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(ventanaPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE));

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void imgReloadMouseClicked(java.awt.event.MouseEvent evt) {
        }

        private void textosLabels() {
                imgReload.setToolTipText("Recargar Pestaña");
                imgVolver.setToolTipText("Salir");
                imgUsuario.setToolTipText("Usuario");
        }

        private void imgUsuarioMouseEntered(java.awt.event.MouseEvent evt) {
                CambiarIU.setImageLabel(imgUsuario, "src/img/usuarioHover.png");
        }

        private void imgUsuarioMouseExited(java.awt.event.MouseEvent evt) {
                CambiarIU.setImageLabel(imgUsuario, "src/img/usuario.png");
        }

        private void imgVolverMouseEntered(java.awt.event.MouseEvent evt) {
                CambiarIU.setImageLabel(imgVolver, "src/img/volverHover.png");
        }

        private void imgVolverMouseExited(java.awt.event.MouseEvent evt) {
                CambiarIU.setImageLabel(imgVolver, "src/img/volver.png");
        }

        private void imgVolverMouseClicked(java.awt.event.MouseEvent evt) {
                Login login = new Login();
                login.setVisible(true);
                this.setVisible(false);
        }

        private void imgUsuarioMouseClicked(java.awt.event.MouseEvent evt) {
                PersonalProfile perfil = new PersonalProfile();
                perfil.setVisible(true);
                this.setVisible(false);
        }

        /**
         * @param args the command line arguments
         */
        public static void main(String args[]) {
                FlatMacDarkLaf.setup();
                EventQueue.invokeLater(() -> new Principal().setVisible(true));
        }

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JLabel imgCasa;
        private javax.swing.JLabel imgMenuBar;
        private javax.swing.JLabel imgReload;
        private javax.swing.JLabel imgUsuario;
        private javax.swing.JLabel imgVolver;
        private javax.swing.JLabel lbJuegos;
        private javax.swing.JPanel panelMenu;
        private javax.swing.JPanel panelPublicaciones;
        private javax.swing.JScrollPane scrollPublicaciones;
        private javax.swing.JPanel ventanaPrincipal;
        // End of variables declaration//GEN-END:variables
}
