/*
 cSpell:ignore lalaa transaccion operacion localtime strftime
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Screens.Profile;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import Code.OperacionCRUD;

import java.awt.EventQueue;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Screens.Custom.CambiarIU;
import Screens.Login.Login;

import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author lalaa
 */
public class Transactions extends javax.swing.JFrame {

        /**
         * Creates new form Transactions
         */

        public Transactions() {
                initComponents();

                this.setTitle("Transacciones");
                this.setResizable(false);
                this.setLocationRelativeTo(null);

                this.setIconImage(Toolkit.getDefaultToolkit()
                                .getImage(getClass().getResource("/img/icon.png")));
                ponerTransaccionesUsuario();
        }

        private void ponerTransaccionesUsuario() {
                try {
                        ArrayList<ArrayList<Object>> transaccionesUsuarios = OperacionCRUD.seleccionar(
                                        String.format("SELECT * FROM transacciones WHERE jugador_id = '%s' ",
                                                        Login.idUsuarioGuardar),
                                        new String[] { "fecha_transaccion", "valor_transaccion", "tipo_transaccion",
                                                        "estado_transaccion" });

                        String[] columnas = { "Fecha Transacción", "Valor Transacción", "Tipo Transacción",
                                        "Estado Transacción" };

                        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

                        for (ArrayList<Object> fila : transaccionesUsuarios) {
                                modelo.addRow(fila.toArray());
                        }

                        tablaTransacciones.setModel(modelo);
                } catch (SQLException e) {
                        e.printStackTrace();
                }

        }

        private double obtenerFondosJugador() {
                ArrayList<ArrayList<Object>> datos;
                try {
                        datos = OperacionCRUD.seleccionar(
                                        String.format("SELECT * FROM jugadores where jugador_id = %d",
                                                        Login.idUsuarioGuardar),
                                        new String[] { "fondos_jugador"
                                        });
                        return (double) datos.get(0).get(0);
                } catch (SQLException e) {
                        e.printStackTrace();
                }
                return 0;
        }

        private void hacerDeposito(double valorDeposito) {
                try {
                        double fondosTotales = obtenerFondosJugador() + valorDeposito;
                        OperacionCRUD.actualizar(
                                        String.format("update jugadores set fondos_jugador = %d where jugador_id = %d",
                                                        (int) fondosTotales, Login.idUsuarioGuardar));

                        registrarTransaccion("Deposito", valorDeposito, "Completada");
                        JOptionPane.showMessageDialog(
                                        null,
                                        String.format("EL DEPÓSITO DE %.2f A SU CUENTA HA SIDO UN ÉXITO.",
                                                        valorDeposito),
                                        "DEPÓSITO EXITOSO",
                                        JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR DE DEPÓSITO",
                                        JOptionPane.ERROR_MESSAGE);
                }
        }

        private void hacerRetiro(double valorRetiro) {
                try {
                        double fondosTotales = obtenerFondosJugador() - valorRetiro;
                        OperacionCRUD.actualizar(
                                        String.format("update jugadores set fondos_jugador = %d where jugador_id = %d",
                                                        (int) fondosTotales, Login.idUsuarioGuardar));

                        registrarTransaccion("Retiro", valorRetiro, "Pendiente");
                        JOptionPane.showMessageDialog(
                                        null,
                                        String.format("EL RETIRO DE %.2f A SU CUENTA HA SIDO UN ÉXITO.", valorRetiro),
                                        "RETIRO EXITOSO",
                                        JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR DE RETIRO",
                                        JOptionPane.ERROR_MESSAGE);
                }
        }

        private void registrarTransaccion(String tipo, double valor, String estado) {
                try {
                        OperacionCRUD.registrar(String.format(
                                        "INSERT INTO TRANSACCIONES (fecha_transaccion, jugador_id, valor_transaccion, tipo_transaccion, estado_transaccion) "
                                                        + "VALUES (strftime('%%d/%%m/%%Y %%H:%%M:%%S' , 'now' , 'localtime'), '%s', %s, '%s', '%s');",
                                        String.valueOf(Login.idUsuarioGuardar), String.valueOf(valor), tipo, estado));

                } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR DE REGISTRO",
                                        JOptionPane.ERROR_MESSAGE);
                }

                ponerTransaccionesUsuario();
        }

        /**
         * This method is called from within the constructor to initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is always
         * regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                ventanaTransacciones = new javax.swing.JPanel();
                imgVolver = new javax.swing.JLabel();
                lbTransacciones = new javax.swing.JLabel();
                tbContenidoTransacciones = new javax.swing.JScrollPane();
                tablaTransacciones = new javax.swing.JTable();
                btnDepositar = new javax.swing.JButton();
                btnRetirar = new javax.swing.JButton();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setBackground(new java.awt.Color(27, 9, 5));

                ventanaTransacciones.setBackground(new java.awt.Color(27, 9, 5));
                ventanaTransacciones.setPreferredSize(new java.awt.Dimension(1080, 720));
                ventanaTransacciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
                ventanaTransacciones.add(imgVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

                lbTransacciones.setFont(new java.awt.Font("Crabs", 1, 85)); // NOI18N
                lbTransacciones.setForeground(new java.awt.Color(227, 199, 104));
                lbTransacciones.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbTransacciones.setText("Transacciones");
                ventanaTransacciones.add(lbTransacciones,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 1080, -1));

                tablaTransacciones.setBackground(new java.awt.Color(36, 38, 41));
                tablaTransacciones.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                tablaTransacciones.setForeground(new java.awt.Color(255, 255, 255));
                tablaTransacciones.setModel(new javax.swing.table.DefaultTableModel(
                                new Object[][] {
                                                {},
                                                {},
                                                {},
                                                {},
                                                {},
                                                {}
                                },
                                new String[] {

                                }));
                tablaTransacciones.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
                tablaTransacciones.setGridColor(new java.awt.Color(255, 51, 51));
                tbContenidoTransacciones.setViewportView(tablaTransacciones);

                ventanaTransacciones.add(tbContenidoTransacciones,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 980, 490));

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
                ventanaTransacciones.add(btnDepositar,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 630, -1, -1));

                btnRetirar.setBackground(new java.awt.Color(147, 128, 67));
                btnRetirar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                btnRetirar.setForeground(new java.awt.Color(255, 255, 254));
                btnRetirar.setText("Retirar");
                btnRetirar.setActionCommand("Ingresar");
                btnRetirar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnRetirar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnRetirarActionPerformed(evt);
                        }
                });
                ventanaTransacciones.add(btnRetirar,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 630, -1, -1));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(ventanaTransacciones,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(ventanaTransacciones,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, 682,
                                                                Short.MAX_VALUE));

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void btnDepositarActionPerformed(java.awt.event.ActionEvent evt) {
                double deposito = Double.parseDouble(
                                JOptionPane.showInputDialog(null, "INGRESE LA CANTIDAD DE DINERO QUE QUIERE DEPOSITAR:",
                                                "DEPOSITAR DINERO", JOptionPane.QUESTION_MESSAGE));
                while (deposito <= 0) {
                        JOptionPane.showMessageDialog(null, "INGRESE UNA CANTIDAD DE DINERO VÁLIDA.",
                                        "ERROR EN DEPOSITO",
                                        JOptionPane.ERROR_MESSAGE);

                        deposito = Double.parseDouble(
                                        JOptionPane.showInputDialog(null,
                                                        "INGRESE LA CANTIDAD DE DINERO QUE QUIERE DEPOSITAR:",
                                                        "DEPOSITAR DINERO", JOptionPane.QUESTION_MESSAGE));
                }
                hacerDeposito(deposito);

        }

        private void btnRetirarActionPerformed(java.awt.event.ActionEvent evt) {
                double retiro = Double.parseDouble(
                                JOptionPane.showInputDialog(null, "INGRESE LA CANTIDAD DE DINERO QUE QUIERE RETIRAR:",
                                                "RETIRAR DINERO", JOptionPane.QUESTION_MESSAGE));

                while (retiro > obtenerFondosJugador() || retiro <= 0) {
                        JOptionPane.showMessageDialog(null, "INGRESE UNA CANTIDAD DE DINERO VÁLIDA.", "ERROR EN RETIRO",
                                        JOptionPane.ERROR_MESSAGE);

                        retiro = Double.parseDouble(
                                        JOptionPane.showInputDialog(null,
                                                        "INGRESE LA CANTIDAD DE DINERO QUE QUIERE RETIRAR:",
                                                        "RETIRAR DINERO", JOptionPane.QUESTION_MESSAGE));
                }
                hacerRetiro(retiro);

        }

        private void imgVolverMouseEntered(java.awt.event.MouseEvent evt) {
                CambiarIU.setImageLabel(imgVolver, "src/img/volverHover.png");
        }

        private void imgVolverMouseExited(java.awt.event.MouseEvent evt) {
                CambiarIU.setImageLabel(imgVolver, "src/img/volver.png");
        }

        private void imgVolverMouseClicked(java.awt.event.MouseEvent evt) {
                PersonalProfile personalProfile = new PersonalProfile();
                personalProfile.setVisible(true);
                this.setVisible(false);
        }

        /**
         * @param args the command line arguments
         */
        public static void main(String args[]) {
                FlatMacDarkLaf.setup();
                EventQueue.invokeLater(() -> new Transactions().setVisible(true));
        }

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btnDepositar;
        private javax.swing.JButton btnRetirar;
        private javax.swing.JLabel imgVolver;
        private javax.swing.JLabel lbTransacciones;
        private javax.swing.JTable tablaTransacciones;
        private javax.swing.JScrollPane tbContenidoTransacciones;
        private javax.swing.JPanel ventanaTransacciones;
        // End of variables declaration//GEN-END:variables
}
