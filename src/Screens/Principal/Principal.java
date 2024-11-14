/*
 cSpell:ignore publicacion ubicacion operacion tahoma  boton codigo conexion simbolos
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Screens.Principal;

import Screens.Custom.CambiarIU;
import Screens.Custom.ObtenerIU;
import Screens.Login.Login;
import Screens.Principal.Games.Carrera;
import Screens.Principal.Games.BlackJack;
import Screens.Principal.Games.Dados;
import Screens.Principal.Games.Poker;
import Screens.Principal.Games.Ruleta.Ruleta;
import Screens.Principal.Games.TragaMonedas;
import Screens.Profile.PersonalProfile;
import Screens.Profile.Transactions;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import Code.Conexion;
import Code.OperacionCRUD;
import Code.VerificarDato;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import javax.swing.JLabel;

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
                ponerFondos(lbPonerFondos);
                deshabilitarBotonCanjear();
                ponerPersonasConectadas(lbPersonasConectadasPoker, 1);
                ponerPersonasConectadas(lbPersonasConectadasRuleta, 2);
                ponerPersonasConectadas(lbPersonasConectadasBlackJack, 3);
                ponerPersonasConectadas(lbPersonasConectadasCarrera, 6);
        }

        public static void ponerFondos(JLabel lbPonerFondos) {
                DecimalFormatSymbols simbolosPersonalizados = new DecimalFormatSymbols();
                simbolosPersonalizados.setGroupingSeparator(',');
                simbolosPersonalizados.setDecimalSeparator('.');

                DecimalFormat formato = new DecimalFormat("#,###.00", simbolosPersonalizados);
                String numeroFormateado = formato.format(PersonalProfile.obtenerFondos());
                CambiarIU.ponerTextoEtiqueta(lbPonerFondos, numeroFormateado);
        }

        public static void ponerPersonasConectadas(JLabel lbPonerConectados, int juego_id) {
                String query = "SELECT personas_conectadas FROM juegos WHERE juego_id = " + juego_id;
                String[] columnas = { "personas_conectadas" };

                new Thread(() -> {
                        while (true) {
                                try (Connection conn = Conexion.conectar()) {
                                        ArrayList<ArrayList<Object>> resultados = OperacionCRUD.seleccionar(conn, query,
                                                        columnas);
                                        if (!resultados.isEmpty()) {
                                                CambiarIU.ponerTextoEtiqueta(lbPonerConectados,
                                                                String.valueOf(resultados.get(0).get(0)));
                                        }
                                        Thread.sleep(1000);

                                } catch (InterruptedException | SQLException e) {
                                        JOptionPane.showMessageDialog(null, e.getMessage(),
                                                        "ERROR AL OBTENER PERSONAS CONECTADAS",
                                                        JOptionPane.ERROR_MESSAGE);
                                }
                        }
                }).start();
        }

        public void deshabilitarBotonCanjear() {
                btnCanjear.setEnabled(VerificarDato.codigoPromocionalValido(ObtenerIU.obtenerTextoCampo(
                                tfCodigoPromocional)));

        }

        public boolean codigoCanjeado(String codigoPromocional) {
                try (Connection conn = Conexion.conectar()) {
                        String query = String.format("SELECT canjeado FROM codigo_promocional WHERE codigo_id = '%s'",
                                        codigoPromocional);
                        ArrayList<ArrayList<Object>> resultado = OperacionCRUD.seleccionar(conn, query,
                                        new String[] { "canjeado" });

                        if (!resultado.isEmpty()) {
                                Object canjeado = resultado.get(0).get(0);
                                if (canjeado instanceof Integer integer) {
                                        return integer == 1;
                                }
                        }
                } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR AL OBTENER CÓDIGO",
                                        JOptionPane.ERROR_MESSAGE);
                }
                return false;
        }

        public boolean codigoRegistrado(String codigoPromocional) {
                try (Connection conn = Conexion.conectar()) {
                        String query = String.format("SELECT codigo_id FROM codigo_promocional WHERE codigo_id = '%s'",
                                        codigoPromocional);
                        ArrayList<ArrayList<Object>> resultado = OperacionCRUD.seleccionar(conn, query,
                                        new String[] { "codigo_id" });

                        return !resultado.isEmpty();
                } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR AL VERIFICAR CÓDIGO",
                                        JOptionPane.ERROR_MESSAGE);
                }
                return false;
        }

        public void canjearCodigo(String codigoPromocional) {
                if (!codigoCanjeado(codigoPromocional)) {
                        try (Connection conn = Conexion.conectar()) {

                                String selectSql = String.format(
                                                "SELECT valor FROM codigo_promocional WHERE codigo_id = '%s';",
                                                codigoPromocional);
                                ArrayList<ArrayList<Object>> resultado = OperacionCRUD.seleccionar(conn, selectSql,
                                                new String[] { "valor" });

                                if (!resultado.isEmpty()) {
                                        double valor = ((Number) resultado.get(0).get(0)).doubleValue();

                                        String updateSql = String.format(
                                                        "UPDATE codigo_promocional SET canjeado_por = '%d', canjeado = 1 WHERE codigo_id = '%s';",
                                                        Login.idUsuarioGuardar, codigoPromocional);

                                        OperacionCRUD.actualizar(conn, updateSql);

                                        Transactions.sumarFondos(valor);

                                        JOptionPane.showMessageDialog(this,
                                                        "¡CÓDIGO PROMOCIONAL CANJEADO EXITOSAMENTE!",
                                                        "¡ÉXITO!", JOptionPane.INFORMATION_MESSAGE);
                                        CambiarIU.ponerTextoCampo(tfCodigoPromocional, "");
                                } else {
                                        JOptionPane.showMessageDialog(this, "¡CÓDIGO PROMOCIONAL NO ENCONTRADO!",
                                                        "¡ERROR!", JOptionPane.ERROR_MESSAGE);
                                }
                        } catch (SQLException e) {
                                JOptionPane.showMessageDialog(this, "¡ERROR AL CANJEAR EL CÓDIGO PROMOCIONAL!",
                                                "¡ERROR!", JOptionPane.ERROR_MESSAGE);
                        }
                } else {
                        JOptionPane.showMessageDialog(this, "¡Este código ya ha sido canjeado!", "¡ERROR!",
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
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                ventanaPrincipal = new javax.swing.JPanel();
                lbJuegos = new javax.swing.JLabel();
                btnDepositar = new javax.swing.JButton();
                lbPonerFondos = new javax.swing.JLabel();
                panelMenu = new javax.swing.JPanel();
                imgCasa = new javax.swing.JLabel();
                imgVolver = new javax.swing.JLabel();
                imgUsuario = new javax.swing.JLabel();
                imgMenuBar = new javax.swing.JLabel();
                scJuegos = new Screens.Custom.ScrollPaneWin11();
                panelPublicaciones = new javax.swing.JPanel();
                lbPersonasConectadasPoker = new javax.swing.JLabel();
                panelPoker = new javax.swing.JPanel();
                imgPoker = new javax.swing.JLabel();
                btnPoker = new javax.swing.JButton();
                lbPersonasConectadasRuleta = new javax.swing.JLabel();
                panelRuleta = new javax.swing.JPanel();
                imgRuleta = new javax.swing.JLabel();
                btnRuleta = new javax.swing.JButton();
                lbPersonasConectadasBlackJack = new javax.swing.JLabel();
                panelBlackJack = new javax.swing.JPanel();
                imgBlackJack = new javax.swing.JLabel();
                btnBlackJack = new javax.swing.JButton();
                panelTragaMonedas = new javax.swing.JPanel();
                imgTragaMonedas = new javax.swing.JLabel();
                btnTragaMonedas = new javax.swing.JButton();
                panelDados = new javax.swing.JPanel();
                imgDados = new javax.swing.JLabel();
                btnDados = new javax.swing.JButton();
                lbPersonasConectadasCarrera = new javax.swing.JLabel();
                panelCarrera = new javax.swing.JPanel();
                imgCarrera = new javax.swing.JLabel();
                btnCarrera = new javax.swing.JButton();
                lbCodigoPromocional = new javax.swing.JLabel();
                tfCodigoPromocional = new javax.swing.JTextField();
                btnCanjear = new javax.swing.JButton();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                ventanaPrincipal.setBackground(new java.awt.Color(27, 9, 5));
                ventanaPrincipal.setPreferredSize(new java.awt.Dimension(1080, 720));
                ventanaPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                lbJuegos.setFont(new java.awt.Font("Crabs", 1, 100)); // NOI18N
                lbJuegos.setForeground(new java.awt.Color(227, 199, 104));
                lbJuegos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbJuegos.setText("Juegos");
                ventanaPrincipal.add(lbJuegos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1080, -1));

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
                ventanaPrincipal.add(btnDepositar, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 50, -1, -1));

                lbPonerFondos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
                lbPonerFondos.setForeground(new java.awt.Color(148, 161, 178));
                lbPonerFondos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                lbPonerFondos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondos.png"))); // NOI18N
                lbPonerFondos.setText("-");
                ventanaPrincipal.add(lbPonerFondos,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1050, -1));

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

                panelPublicaciones.setBackground(new java.awt.Color(36, 38, 41));
                panelPublicaciones.setBorder(
                                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(227, 199, 104)));
                panelPublicaciones.setMinimumSize(new java.awt.Dimension(350, 100000));
                panelPublicaciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                lbPersonasConectadasPoker.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                lbPersonasConectadasPoker.setForeground(new java.awt.Color(148, 161, 178));
                lbPersonasConectadasPoker.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPersonasConectadasPoker
                                .setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/personas.png"))); // NOI18N
                lbPersonasConectadasPoker.setText("0");
                panelPublicaciones.add(lbPersonasConectadasPoker,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 230, 40));

                panelPoker.setBackground(new java.awt.Color(227, 199, 104));
                panelPoker.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)),
                                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255))));
                panelPoker.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                imgPoker.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/poker.png"))); // NOI18N
                panelPoker.add(imgPoker, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

                btnPoker.setBackground(new java.awt.Color(147, 128, 67));
                btnPoker.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                btnPoker.setForeground(new java.awt.Color(255, 255, 255));
                btnPoker.setText("POKER");
                btnPoker.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnPokerActionPerformed(evt);
                        }
                });
                panelPoker.add(btnPoker, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, -1, -1));

                panelPublicaciones.add(panelPoker, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 230, 180));

                lbPersonasConectadasRuleta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                lbPersonasConectadasRuleta.setForeground(new java.awt.Color(148, 161, 178));
                lbPersonasConectadasRuleta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPersonasConectadasRuleta
                                .setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/personas.png"))); // NOI18N
                lbPersonasConectadasRuleta.setText("0");
                panelPublicaciones.add(lbPersonasConectadasRuleta,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 230, 40));

                panelRuleta.setBackground(new java.awt.Color(227, 199, 104));
                panelRuleta.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)),
                                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255))));
                panelRuleta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                imgRuleta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ruleta.png"))); // NOI18N
                imgRuleta.setText("jLabel2");
                panelRuleta.add(imgRuleta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 195, 130));

                btnRuleta.setBackground(new java.awt.Color(147, 128, 67));
                btnRuleta.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                btnRuleta.setForeground(new java.awt.Color(255, 255, 255));
                btnRuleta.setText("RULETA");
                btnRuleta.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnRuletaActionPerformed(evt);
                        }
                });
                panelRuleta.add(btnRuleta, new org.netbeans.lib.awtextra.AbsoluteConstraints(74, 149, -1, -1));

                panelPublicaciones.add(panelRuleta,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, 230, 180));

                lbPersonasConectadasBlackJack.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                lbPersonasConectadasBlackJack.setForeground(new java.awt.Color(148, 161, 178));
                lbPersonasConectadasBlackJack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPersonasConectadasBlackJack
                                .setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/personas.png"))); // NOI18N
                lbPersonasConectadasBlackJack.setText("0");
                panelPublicaciones.add(lbPersonasConectadasBlackJack,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 230, 40));

                panelBlackJack.setBackground(new java.awt.Color(227, 199, 104));
                panelBlackJack.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)),
                                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255))));
                panelBlackJack.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                imgBlackJack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/blackJack.png"))); // NOI18N
                imgBlackJack.setText("jLabel2");
                panelBlackJack.add(imgBlackJack, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 195, 130));

                btnBlackJack.setBackground(new java.awt.Color(147, 128, 67));
                btnBlackJack.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                btnBlackJack.setForeground(new java.awt.Color(255, 255, 255));
                btnBlackJack.setText("BLACKJACK");
                btnBlackJack.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnBlackJackActionPerformed(evt);
                        }
                });
                panelBlackJack.add(btnBlackJack, new org.netbeans.lib.awtextra.AbsoluteConstraints(74, 149, -1, -1));

                panelPublicaciones.add(panelBlackJack,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 50, 230, 180));

                panelTragaMonedas.setBackground(new java.awt.Color(227, 199, 104));
                panelTragaMonedas.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)),
                                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255))));
                panelTragaMonedas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                imgTragaMonedas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tragaMonedas.png"))); // NOI18N
                imgTragaMonedas.setText("jLabel2");
                panelTragaMonedas.add(imgTragaMonedas,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 13, 195, 130));

                btnTragaMonedas.setBackground(new java.awt.Color(147, 128, 67));
                btnTragaMonedas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                btnTragaMonedas.setForeground(new java.awt.Color(255, 255, 255));
                btnTragaMonedas.setText("TRAGA MONEDAS");
                btnTragaMonedas.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnTragaMonedasActionPerformed(evt);
                        }
                });
                panelTragaMonedas.add(btnTragaMonedas,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 149, -1, -1));

                panelPublicaciones.add(panelTragaMonedas,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 230, 180));

                panelDados.setBackground(new java.awt.Color(227, 199, 104));
                panelDados.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)),
                                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255))));
                panelDados.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                imgDados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/dados.png"))); // NOI18N
                imgDados.setText("jLabel2");
                panelDados.add(imgDados, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 13, 195, 130));

                btnDados.setBackground(new java.awt.Color(147, 128, 67));
                btnDados.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                btnDados.setForeground(new java.awt.Color(255, 255, 255));
                btnDados.setText("DADOS");
                btnDados.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnDadosActionPerformed(evt);
                        }
                });
                panelDados.add(btnDados, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 149, -1, -1));

                panelPublicaciones.add(panelDados,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 280, 230, 180));

                lbPersonasConectadasCarrera.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                lbPersonasConectadasCarrera.setForeground(new java.awt.Color(148, 161, 178));
                lbPersonasConectadasCarrera.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPersonasConectadasCarrera
                                .setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/personas.png"))); // NOI18N
                lbPersonasConectadasCarrera.setText("0");
                panelPublicaciones.add(lbPersonasConectadasCarrera,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 240, 230, 40));

                panelCarrera.setBackground(new java.awt.Color(227, 199, 104));
                panelCarrera.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)),
                                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255))));
                panelCarrera.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                imgCarrera.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/carrera.jpg"))); // NOI18N
                imgCarrera.setText("jLabel2");
                panelCarrera.add(imgCarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 195, -1));

                btnCarrera.setBackground(new java.awt.Color(147, 128, 67));
                btnCarrera.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                btnCarrera.setForeground(new java.awt.Color(255, 255, 255));
                btnCarrera.setText("CARRERA");
                btnCarrera.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnCarreraActionPerformed(evt);
                        }
                });
                panelCarrera.add(btnCarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(74, 149, -1, -1));

                panelPublicaciones.add(panelCarrera,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 280, 230, 180));

                scJuegos.setViewportView(panelPublicaciones);

                ventanaPrincipal.add(scJuegos, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 890, 500));

                lbCodigoPromocional.setFont(new java.awt.Font("Crabs", 1, 24)); // NOI18N
                lbCodigoPromocional.setForeground(new java.awt.Color(227, 199, 104));
                lbCodigoPromocional.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbCodigoPromocional.setText("Codigo Promocional:");
                lbCodigoPromocional.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                ventanaPrincipal.add(lbCodigoPromocional,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 670, 210, 30));

                tfCodigoPromocional.setBackground(new java.awt.Color(27, 9, 5));
                tfCodigoPromocional.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
                tfCodigoPromocional.setForeground(new java.awt.Color(255, 255, 255));
                tfCodigoPromocional.setBorder(
                                new javax.swing.border.LineBorder(new java.awt.Color(227, 199, 104), 1, true));
                tfCodigoPromocional.setOpaque(true);
                tfCodigoPromocional.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                                tfCodigoPromocionalKeyReleased(evt);
                        }
                });
                ventanaPrincipal.add(tfCodigoPromocional,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 670, 160, 30));

                btnCanjear.setBackground(new java.awt.Color(147, 128, 67));
                btnCanjear.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                btnCanjear.setForeground(new java.awt.Color(255, 255, 254));
                btnCanjear.setText("Canjear");
                btnCanjear.setActionCommand("Ingresar");
                btnCanjear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnCanjear.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnCanjearActionPerformed(evt);
                        }
                });
                ventanaPrincipal.add(btnCanjear, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 670, -1, 30));

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

        private void btnCanjearActionPerformed(java.awt.event.ActionEvent evt) {
                canjearCodigo(ObtenerIU.obtenerTextoCampo(tfCodigoPromocional));
                deshabilitarBotonCanjear();
                ponerFondos(lbPonerFondos);

        }

        private void tfCodigoPromocionalKeyReleased(java.awt.event.KeyEvent evt) {
                deshabilitarBotonCanjear();

        }

        private void btnDepositarActionPerformed(java.awt.event.ActionEvent evt) {
                Transactions transactions = new Transactions();
                transactions.setVisible(true);
                this.setVisible(false);

        }

        private void btnCarreraActionPerformed(java.awt.event.ActionEvent evt) {
                Carrera carrera = new Carrera();
                carrera.setVisible(true);
                this.setVisible(false);
        }

        private void btnTragaMonedasActionPerformed(java.awt.event.ActionEvent evt) {
                TragaMonedas tragaMonedas = new TragaMonedas();
                tragaMonedas.setVisible(true);
                this.setVisible(false);

        }

        private void btnPokerActionPerformed(java.awt.event.ActionEvent evt) {
                Poker poker = new Poker();
                poker.setVisible(true);
                this.setVisible(false);

        }

        private void btnBlackJackActionPerformed(java.awt.event.ActionEvent evt) {
                BlackJack blackJack = new BlackJack();
                blackJack.setVisible(true);
                this.setVisible(false);

        }

        private void btnRuletaActionPerformed(java.awt.event.ActionEvent evt) {
                Ruleta ruleta = new Ruleta();
                ruleta.setVisible(true);
                this.setVisible(false);

        }

        private void btnDadosActionPerformed(java.awt.event.ActionEvent evt) {
                Dados dados = new Dados();
                dados.setVisible(true);
                this.setVisible(false);

        }

        private void textosLabels() {
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
        private javax.swing.JButton btnBlackJack;
        private javax.swing.JButton btnCanjear;
        private javax.swing.JButton btnCarrera;
        private javax.swing.JButton btnDados;
        private javax.swing.JButton btnDepositar;
        private javax.swing.JButton btnPoker;
        private javax.swing.JButton btnRuleta;
        private javax.swing.JButton btnTragaMonedas;
        private javax.swing.JLabel imgBlackJack;
        private javax.swing.JLabel imgCarrera;
        private javax.swing.JLabel imgCasa;
        private javax.swing.JLabel imgDados;
        private javax.swing.JLabel imgMenuBar;
        private javax.swing.JLabel imgPoker;
        private javax.swing.JLabel imgRuleta;
        private javax.swing.JLabel imgTragaMonedas;
        private javax.swing.JLabel imgUsuario;
        private javax.swing.JLabel imgVolver;
        private javax.swing.JLabel lbCodigoPromocional;
        private javax.swing.JLabel lbJuegos;
        private javax.swing.JLabel lbPersonasConectadasBlackJack;
        private javax.swing.JLabel lbPersonasConectadasCarrera;
        private javax.swing.JLabel lbPersonasConectadasPoker;
        private javax.swing.JLabel lbPersonasConectadasRuleta;
        private javax.swing.JLabel lbPonerFondos;
        private javax.swing.JPanel panelBlackJack;
        private javax.swing.JPanel panelCarrera;
        private javax.swing.JPanel panelDados;
        private javax.swing.JPanel panelMenu;
        private javax.swing.JPanel panelPoker;
        private javax.swing.JPanel panelPublicaciones;
        private javax.swing.JPanel panelRuleta;
        private javax.swing.JPanel panelTragaMonedas;
        private javax.swing.JScrollPane scJuegos;
        private javax.swing.JTextField tfCodigoPromocional;
        private javax.swing.JPanel ventanaPrincipal;
        // End of variables declaration//GEN-END:variables
}
