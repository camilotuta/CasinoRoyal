/*
 cspell:ignore ubicacion dias operacion biografia boton informacion nathalya tahoma minimos conexion
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main.java.com.casinoRoyal.ui.views.profile;

import main.java.com.casinoRoyal.core.database.Conexion;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.sql.Connection;

import main.java.com.casinoRoyal.core.database.OperacionCRUD;
import main.java.com.casinoRoyal.util.dates.Dates;
import main.java.com.casinoRoyal.ui.utils.CambiarIU;
import main.java.com.casinoRoyal.ui.utils.ObtenerIU;
import main.java.com.casinoRoyal.ui.views.login.Login;
import main.java.com.casinoRoyal.ui.views.principal.Principal;
import main.java.com.casinoRoyal.ui.views.transactions.Transactions;

/**
 *
 * @author tutaa
 */
public class PersonalProfile extends javax.swing.JFrame {

        /**
         * Creates new form PersonalProfile
         */
        public PersonalProfile() {
                initComponents();

                this.setTitle("Perfil Personal");
                this.setResizable(false);
                this.setLocationRelativeTo(null);

                this.setIconImage(Toolkit.getDefaultToolkit()
                                .getImage(getClass().getResource("/img/icon.png")));

                desactivarBotonGuardar();

                ponerInformacion();
                textosLabels();
        }

        private void textosLabels() {
                imgVolver.setToolTipText("Salir");
                imgCasa.setToolTipText("Pantalla Principal");
        }

        public static double obtenerFondos() {
                double fondos = 0.0;
                String query = "SELECT fondos_jugador FROM jugadores WHERE jugador_id = " + Login.idUsuarioGuardar;
                String[] columnas = { "fondos_jugador" };

                try (Connection conn = Conexion.conectar()) {
                        ArrayList<ArrayList<Object>> resultados = OperacionCRUD.seleccionar(conn, query, columnas);
                        if (!resultados.isEmpty()) {
                                fondos = (double) resultados.get(0).get(0);
                        }
                } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR AL OBTENER FONDOS",
                                        JOptionPane.ERROR_MESSAGE);
                }
                return fondos;
        }

        public static boolean fondosSuficientes(double fondosMinimosJuego) {
                if (!(obtenerFondos() >= fondosMinimosJuego)) {
                        String mensaje = "No tienes suficientes fondos para apostar. Se requiere un mínimo de $"
                                        + fondosMinimosJuego;
                        JOptionPane.showMessageDialog(
                                        null,
                                        mensaje,
                                        "Fondos Insuficientes",
                                        JOptionPane.ERROR_MESSAGE);

                }
                return obtenerFondos() >= fondosMinimosJuego;
        }

        private void ponerInformacion() {
                ArrayList<ArrayList<Object>> datos;

                try (Connection conn = Conexion.conectar()) {
                        if (conn == null) {
                                throw new SQLException("No se pudo establecer la conexión a la base de datos.");
                        }
                        datos = OperacionCRUD.seleccionar(conn,
                                        String.format("SELECT nombre_usuario, correo_jugador, fondos_jugador, biografia, fecha_nacimiento "
                                                        + "FROM jugadores WHERE jugador_id = %d",
                                                        Login.idUsuarioGuardar),
                                        new String[] { "nombre_usuario", "correo_jugador", "fondos_jugador",
                                                        "biografia", "fecha_nacimiento" });

                        if (!datos.isEmpty() && !datos.get(0).isEmpty()) {
                                CambiarIU.ponerTextoEtiqueta(lbPonerNombre, (String) datos.get(0).get(0));
                                CambiarIU.ponerTextoEtiqueta(lbPonerCorreo, (String) datos.get(0).get(1));

                                String fechaNacimiento = (String) datos.get(0).get(4);
                                if (fechaNacimiento != null && !fechaNacimiento.isEmpty()) {
                                        int edad = Dates.restarFechasSinDiasBisiestos(fechaNacimiento,
                                                        Dates.obtenerFechaHoy());
                                        CambiarIU.ponerTextoEtiqueta(lbPonerEdad, (String.valueOf(edad) + " años"));
                                }

                                Principal.ponerFondos(lbPonerFondos);
                                CambiarIU.ponerTextoArea(txtMostrarBiografia, (String) datos.get(0).get(3));
                        }

                } catch (SQLException e) {
                        JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
        }

        public static String obtenerNombre() {
                ArrayList<ArrayList<Object>> datos;

                try (Connection conn = Conexion.conectar()) {
                        if (conn == null) {
                                throw new SQLException("No se pudo establecer la conexión a la base de datos.");
                        }

                        datos = OperacionCRUD.seleccionar(
                                        conn,
                                        String.format("SELECT nombre_usuario FROM jugadores WHERE jugador_id = %d",
                                                        Login.idUsuarioGuardar),
                                        new String[] { "nombre_usuario" });

                        if (!datos.isEmpty() && !datos.get(0).isEmpty()) {
                                String nombreCompleto = (String) datos.get(0).get(0);
                                String[] nombreSeparado = nombreCompleto.split(" ");

                                return switch (nombreSeparado.length) {
                                        case 1 -> nombreSeparado[0];
                                        case 2 -> nombreSeparado[0] + " " + nombreSeparado[1];
                                        default -> nombreSeparado[0] + " " + nombreSeparado[2];
                                };
                        }

                } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }

                return "";
        }

        private void desactivarBotonGuardar() {
                btnGuardarBiografia.setEnabled(!ObtenerIU.obtenerTextoPanel(tfCambiarBiografia)
                                .equals(ObtenerIU.obtenerTextoArea(txtMostrarBiografia))
                                && ObtenerIU.obtenerTextoPanel(tfCambiarBiografia).length() > 5);
        }

        private void actualizarBiografia() throws SQLException {

                try (Connection conn = Conexion.conectar()) {
                        if (conn == null) {
                                throw new SQLException("No se pudo establecer la conexión a la base de datos.");
                        }
                        String nuevaBiografia = ObtenerIU.obtenerTextoPanel(tfCambiarBiografia);
                        OperacionCRUD.actualizar(conn,
                                        String.format("UPDATE jugadores SET biografia = '%s' WHERE jugador_id = %d",
                                                        nuevaBiografia, Login.idUsuarioGuardar));
                        ponerInformacion();
                        CambiarIU.ponerTextoPanel(tfCambiarBiografia, "");
                        desactivarBotonGuardar();
                } catch (SQLException e) {
                        JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
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
        // Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                ventanaProfile = new javax.swing.JPanel();
                lbPerfil = new javax.swing.JLabel();
                lbPonerFondos = new javax.swing.JLabel();
                panelMenu = new javax.swing.JPanel();
                imgCasa = new javax.swing.JLabel();
                imgVolver = new javax.swing.JLabel();
                imgUsuario = new javax.swing.JLabel();
                imgMenuBar = new javax.swing.JLabel();
                lbPonerNombre = new javax.swing.JLabel();
                lbPonerEdad = new javax.swing.JLabel();
                lbPonerCorreo = new javax.swing.JLabel();
                lbBiografía = new javax.swing.JLabel();
                scrollPonerBiografia = new main.java.com.casinoRoyal.ui.components.ScrollPaneWin11();
                txtMostrarBiografia = new javax.swing.JTextArea();
                lbCambiarBiografia = new javax.swing.JLabel();
                scrollCambiarBiografia = new main.java.com.casinoRoyal.ui.components.ScrollPaneWin11();
                tfCambiarBiografia = new javax.swing.JTextPane();
                btnCancelarBiografia = new javax.swing.JButton();
                btnGuardarBiografia = new javax.swing.JButton();
                btnVerTransacciones = new javax.swing.JButton();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                ventanaProfile.setBackground(new java.awt.Color(27, 9, 5));
                ventanaProfile.setForeground(new java.awt.Color(255, 255, 255));
                ventanaProfile.setPreferredSize(new java.awt.Dimension(1080, 720));
                ventanaProfile.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                lbPerfil.setFont(new java.awt.Font("Crabs", 1, 100)); // NOI18N
                lbPerfil.setForeground(new java.awt.Color(227, 199, 104));
                lbPerfil.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPerfil.setText("Perfil");
                ventanaProfile.add(lbPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1080, -1));

                lbPonerFondos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
                lbPonerFondos.setForeground(new java.awt.Color(148, 161, 178));
                lbPonerFondos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                lbPonerFondos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondos.png"))); // NOI18N
                lbPonerFondos.setText("-");
                ventanaProfile.add(lbPonerFondos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1050, -1));

                panelMenu.setBackground(new java.awt.Color(22, 22, 26));
                panelMenu.setOpaque(false);
                panelMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                imgCasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/casa.png"))); // NOI18N
                imgCasa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                imgCasa.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                imgCasaMouseClicked(evt);
                        }

                        public void mouseEntered(java.awt.event.MouseEvent evt) {
                                imgCasaMouseEntered(evt);
                        }

                        public void mouseExited(java.awt.event.MouseEvent evt) {
                                imgCasaMouseExited(evt);
                        }
                });
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

                imgUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/usuarioHover.png"))); // NOI18N
                imgUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                panelMenu.add(imgUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

                imgMenuBar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                imgMenuBar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lineaVertical.png"))); // NOI18N
                imgMenuBar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
                panelMenu.add(imgMenuBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, -1));

                ventanaProfile.add(panelMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 230, 60, 220));

                lbPonerNombre.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
                lbPonerNombre.setForeground(new java.awt.Color(255, 255, 255));
                lbPonerNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerNombre.setText("Laura Nathalya Abril Velasquez");
                lbPonerNombre.setToolTipText("");
                ventanaProfile.add(lbPonerNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 1080, -1));

                lbPonerEdad.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
                lbPonerEdad.setForeground(new java.awt.Color(255, 255, 255));
                lbPonerEdad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerEdad.setText("19 años");
                ventanaProfile.add(lbPonerEdad, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 1080, -1));

                lbPonerCorreo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
                lbPonerCorreo.setForeground(new java.awt.Color(255, 255, 255));
                lbPonerCorreo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerCorreo.setText("lalaabril01@gmail.com");
                ventanaProfile.add(lbPonerCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 1080, -1));

                lbBiografía.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
                lbBiografía.setForeground(new java.awt.Color(224, 195, 102));
                lbBiografía.setText("BIOGRAFÍA: ");
                ventanaProfile.add(lbBiografía, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 340, -1, -1));

                scrollPonerBiografia.setBackground(new java.awt.Color(0, 2, 2));

                txtMostrarBiografia.setEditable(false);
                txtMostrarBiografia.setBackground(new java.awt.Color(27, 9, 5));
                txtMostrarBiografia.setColumns(1);
                txtMostrarBiografia.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
                txtMostrarBiografia.setForeground(new java.awt.Color(255, 255, 255));
                txtMostrarBiografia.setLineWrap(true);
                txtMostrarBiografia.setRows(3);
                txtMostrarBiografia.setWrapStyleWord(true);
                txtMostrarBiografia.setBorder(null);
                txtMostrarBiografia.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                txtMostrarBiografia.setFocusable(false);
                scrollPonerBiografia.setViewportView(txtMostrarBiografia);

                ventanaProfile.add(scrollPonerBiografia,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 380, 380, 100));

                lbCambiarBiografia.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
                lbCambiarBiografia.setForeground(new java.awt.Color(224, 195, 102));
                lbCambiarBiografia.setText("CAMBIAR BIOGRAFÍA: ");
                ventanaProfile.add(lbCambiarBiografia,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 490, -1, -1));

                scrollCambiarBiografia.setBackground(new java.awt.Color(0, 2, 2));

                tfCambiarBiografia.setBackground(new java.awt.Color(27, 9, 5));
                tfCambiarBiografia.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
                tfCambiarBiografia.setForeground(new java.awt.Color(255, 255, 255));
                tfCambiarBiografia.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                                tfCambiarBiografiaKeyReleased(evt);
                        }
                });
                scrollCambiarBiografia.setViewportView(tfCambiarBiografia);

                ventanaProfile.add(scrollCambiarBiografia,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 530, 380, 80));

                btnCancelarBiografia.setBackground(new java.awt.Color(147, 128, 67));
                btnCancelarBiografia.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                btnCancelarBiografia.setForeground(new java.awt.Color(255, 255, 254));
                btnCancelarBiografia.setText("Cancelar");
                btnCancelarBiografia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnCancelarBiografia.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnCancelarBiografiaActionPerformed(evt);
                        }
                });
                ventanaProfile.add(btnCancelarBiografia,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 650, -1, -1));

                btnGuardarBiografia.setBackground(new java.awt.Color(147, 128, 67));
                btnGuardarBiografia.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                btnGuardarBiografia.setForeground(new java.awt.Color(255, 255, 254));
                btnGuardarBiografia.setText("Guardar");
                btnGuardarBiografia.setActionCommand("Ingresar");
                btnGuardarBiografia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnGuardarBiografia.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                try {
                                        btnGuardarBiografiaActionPerformed(evt);
                                } catch (SQLException e) {
                                        JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR",
                                                        JOptionPane.ERROR_MESSAGE);
                                }
                        }
                });
                ventanaProfile.add(btnGuardarBiografia,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 650, 110, -1));

                btnVerTransacciones.setBackground(new java.awt.Color(147, 128, 67));
                btnVerTransacciones.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                btnVerTransacciones.setForeground(new java.awt.Color(255, 255, 254));
                btnVerTransacciones.setText("Ver Transacciones");
                btnVerTransacciones.setActionCommand("Ingresar");
                btnVerTransacciones.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnVerTransacciones.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnVerTransaccionesActionPerformed(evt);
                        }
                });
                ventanaProfile.add(btnVerTransacciones,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 650, -1, -1));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(ventanaProfile, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(ventanaProfile, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void btnVerTransaccionesActionPerformed(java.awt.event.ActionEvent evt) {
                Transactions transactions = new Transactions();
                transactions.setVisible(true);
                this.setVisible(false);

        }

        private void imgVolverMouseClicked(java.awt.event.MouseEvent evt) {
                Login login = new Login();
                login.setVisible(true);
                this.setVisible(false);
        }

        private void imgVolverMouseEntered(java.awt.event.MouseEvent evt) {
                CambiarIU.setImageLabel(imgVolver, "src/img/volverHover.png");
        }

        private void imgVolverMouseExited(java.awt.event.MouseEvent evt) {
                CambiarIU.setImageLabel(imgVolver, "src/img/volver.png");
        }

        private void imgCasaMouseClicked(java.awt.event.MouseEvent evt) {
                Principal principal = new Principal();
                principal.setVisible(true);
                this.setVisible(false);
        }

        private void imgCasaMouseEntered(java.awt.event.MouseEvent evt) {
                CambiarIU.setImageLabel(imgCasa, "src/img/casaHover.png");
        }

        private void imgCasaMouseExited(java.awt.event.MouseEvent evt) {
                CambiarIU.setImageLabel(imgCasa, "src/img/casa.png");
        }

        private void btnGuardarBiografiaActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {
                try {
                        actualizarBiografia();
                } catch (SQLException e) {
                        JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR",
                                        JOptionPane.ERROR_MESSAGE);

                }
        }

        private void btnCancelarBiografiaActionPerformed(java.awt.event.ActionEvent evt) {
                CambiarIU.ponerTextoPanel(tfCambiarBiografia, "");
                btnGuardarBiografia.setEnabled(false);
        }

        private void tfCambiarBiografiaKeyReleased(java.awt.event.KeyEvent evt) {
                desactivarBotonGuardar();
        }

        /**
         * @param args the command line arguments
         */
        public static void main(String args[]) {
                FlatMacDarkLaf.setup();
                EventQueue.invokeLater(() -> new PersonalProfile().setVisible(true));
        }

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btnCancelarBiografia;
        private javax.swing.JButton btnGuardarBiografia;
        private javax.swing.JButton btnVerTransacciones;
        private javax.swing.JLabel imgCasa;
        private javax.swing.JLabel imgMenuBar;
        private javax.swing.JLabel imgUsuario;
        private javax.swing.JLabel imgVolver;
        private javax.swing.JLabel lbBiografía;
        private javax.swing.JLabel lbCambiarBiografia;
        private javax.swing.JLabel lbPerfil;
        private javax.swing.JLabel lbPonerCorreo;
        private javax.swing.JLabel lbPonerEdad;
        private javax.swing.JLabel lbPonerFondos;
        private javax.swing.JLabel lbPonerNombre;
        private javax.swing.JPanel panelMenu;
        private javax.swing.JScrollPane scrollCambiarBiografia;
        private javax.swing.JScrollPane scrollPonerBiografia;
        private javax.swing.JTextPane tfCambiarBiografia;
        private javax.swing.JTextArea txtMostrarBiografia;
        private javax.swing.JPanel ventanaProfile;
        // End of variables declaration//GEN-END:variables
}
