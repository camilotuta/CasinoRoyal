/*
 cSpell:ignore publicacion ubicacion operacion tahoma numeros
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main.java.com.casinoRoyal.ui.views.games.dados;

import main.java.com.casinoRoyal.ui.utils.CambiarIU;
import main.java.com.casinoRoyal.ui.utils.ObtenerIU;
import main.java.com.casinoRoyal.ui.utils.SoundPlay;
import main.java.com.casinoRoyal.game.dados.NumerosDado;
import main.java.com.casinoRoyal.ui.views.principal.Principal;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JOptionPane;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import main.java.com.casinoRoyal.ui.views.profile.PersonalProfile;
import main.java.com.casinoRoyal.ui.views.transactions.Transactions;

import java.awt.HeadlessException;

/**
 *
 * @author tutaa
 */
public class Dados extends javax.swing.JFrame {

        /**
         * Creates new form Dados
         */
        public Dados() {
                initComponents();

                this.setTitle("BlackJack");
                this.setResizable(false);
                this.setLocationRelativeTo(null);

                this.setIconImage(Toolkit.getDefaultToolkit()
                                .getImage(getClass().getResource("/img/icon.png")));
                Principal.ponerFondos(lbPonerFondos);
        }

        private void lanzarDados(String tipoApuesta, double valorApostado, Integer... args) {
                if (PersonalProfile.fondosSuficientes(
                                Double.parseDouble(ObtenerIU.obtenerSeleccionCombo(cbValorApostado)))) {
                        Transactions.restarFondos(valorApostado);
                        Principal.ponerFondos(lbPonerFondos);

                        CambiarIU.deshabilitarBotones(btnApostarPar, btnApostarImpar, btnApostarSuma, btnApostarDobles);
                        CambiarIU.setImageLabel(lbDado1, "src/img/dados/dadoGirando.gif");
                        CambiarIU.setImageLabel(lbDado2, "src/img/dados/dadoGirando.gif");
                        SoundPlay.reproducir("src/sound/lanzandoDados.wav");

                        int numeroDado1 = NumerosDado.numeroAleatorio();
                        int numeroDado2 = NumerosDado.numeroAleatorio();

                        final double[] valorGanado = { 0 };

                        new Thread(() -> {
                                try {
                                        Thread.sleep(4000);

                                        if (tipoApuesta.equalsIgnoreCase("par")
                                                        && (numeroDado1 % 2 == 0 && numeroDado2 % 2 == 0)) {
                                                valorGanado[0] = valorApostado * 2;

                                        } else if (tipoApuesta.equalsIgnoreCase("impar")
                                                        && (numeroDado1 % 2 != 0 && numeroDado2 % 2 != 0)) {
                                                valorGanado[0] = valorApostado * 2;
                                        } else if (tipoApuesta.equalsIgnoreCase("suma")
                                                        && (numeroDado1 + numeroDado2) == args[0]) {
                                                valorGanado[0] = valorApostado
                                                                * NumerosDado.cuotaSumaPagar(numeroDado1 + numeroDado2);

                                        } else if (tipoApuesta.equalsIgnoreCase("dobles")
                                                        && ((numeroDado1 == args[0] && numeroDado2 == args[1])
                                                                        || (numeroDado2 == args[0]
                                                                                        && numeroDado1 == args[1]))) {
                                                valorGanado[0] = valorApostado * 30;
                                        }

                                        CambiarIU.setImageLabel(lbDado1, "src/img/dados/dado" + numeroDado1 + ".png");
                                        CambiarIU.setImageLabel(lbDado2, "src/img/dados/dado" + numeroDado2 + ".png");

                                        Thread.sleep(2000);
                                        String mensaje = "Los dados han caído en: " + numeroDado1 + " y " + numeroDado2
                                                        + ". Total: " + (numeroDado1 + numeroDado2) + "\n"
                                                        + "Ganancia: $" + valorGanado[0];

                                        JOptionPane.showMessageDialog(this, mensaje, "Resultado de los Dados",
                                                        JOptionPane.INFORMATION_MESSAGE);

                                        CambiarIU.habilitarBotones(btnApostarPar, btnApostarImpar, btnApostarSuma,
                                                        btnApostarDobles);
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
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                ventanaBlackJack = new javax.swing.JPanel();
                imgVolver = new javax.swing.JLabel();
                btnDepositar = new javax.swing.JButton();
                lbDados = new javax.swing.JLabel();
                lbPonerFondos = new javax.swing.JLabel();
                lbDado1 = new javax.swing.JLabel();
                lbDado2 = new javax.swing.JLabel();
                btnApostarPar = new javax.swing.JButton();
                btnApostarImpar = new javax.swing.JButton();
                btnApostarSuma = new javax.swing.JButton();
                btnApostarDobles = new javax.swing.JButton();
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

                lbDados.setFont(new java.awt.Font("Crabs", 1, 100)); // NOI18N
                lbDados.setForeground(new java.awt.Color(227, 199, 104));
                lbDados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbDados.setText("Dados");
                ventanaBlackJack.add(lbDados, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1080, -1));

                lbPonerFondos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
                lbPonerFondos.setForeground(new java.awt.Color(148, 161, 178));
                lbPonerFondos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                lbPonerFondos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondos.png"))); // NOI18N
                lbPonerFondos.setText("-");
                ventanaBlackJack.add(lbPonerFondos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1050, -1));

                lbDado1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/dados/dado1.png"))); // NOI18N
                ventanaBlackJack.add(lbDado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 280, 154, 156));

                lbDado2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/dados/dado6.png"))); // NOI18N
                ventanaBlackJack.add(lbDado2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 280, 154, 156));

                btnApostarPar.setBackground(new java.awt.Color(153, 1, 102));
                btnApostarPar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                btnApostarPar.setForeground(new java.awt.Color(255, 255, 254));
                btnApostarPar.setText("Ambos Par");
                btnApostarPar.setActionCommand("Ingresar");
                btnApostarPar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnApostarPar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnApostarParActionPerformed(evt);
                        }
                });
                ventanaBlackJack.add(btnApostarPar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 580, -1, -1));

                btnApostarImpar.setBackground(new java.awt.Color(204, 102, 1));
                btnApostarImpar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                btnApostarImpar.setForeground(new java.awt.Color(255, 255, 254));
                btnApostarImpar.setText("Ambos Impar");
                btnApostarImpar.setActionCommand("Ingresar");
                btnApostarImpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnApostarImpar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnApostarImparActionPerformed(evt);
                        }
                });
                ventanaBlackJack.add(btnApostarImpar, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 580, -1, -1));

                btnApostarSuma.setBackground(new java.awt.Color(1, 153, 153));
                btnApostarSuma.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                btnApostarSuma.setForeground(new java.awt.Color(255, 255, 254));
                btnApostarSuma.setText("Suma de los Dados");
                btnApostarSuma.setActionCommand("Ingresar");
                btnApostarSuma.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnApostarSuma.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnApostarSumaActionPerformed(evt);
                        }
                });
                ventanaBlackJack.add(btnApostarSuma, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 640, -1, -1));

                btnApostarDobles.setBackground(new java.awt.Color(51, 1, 153));
                btnApostarDobles.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                btnApostarDobles.setForeground(new java.awt.Color(255, 255, 254));
                btnApostarDobles.setText("Dobles");
                btnApostarDobles.setActionCommand("Ingresar");
                btnApostarDobles.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnApostarDobles.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnApostarDoblesActionPerformed(evt);
                        }
                });
                ventanaBlackJack.add(btnApostarDobles, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 640, -1, -1));

                lbApuesta.setFont(new java.awt.Font("Crabs", 1, 24)); // NOI18N
                lbApuesta.setForeground(new java.awt.Color(227, 199, 104));
                lbApuesta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbApuesta.setText("Apuesta");
                ventanaBlackJack.add(lbApuesta, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 540, 190, 30));

                cbValorApostado.setBackground(new java.awt.Color(27, 9, 5));
                cbValorApostado.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                cbValorApostado.setForeground(new java.awt.Color(224, 195, 102));
                cbValorApostado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "100", "200", "500", "1000", "2000", "5000", "10000", "25000", "50000", "100000" }));
                ventanaBlackJack.add(cbValorApostado, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 570, 190, 40));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(ventanaBlackJack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(ventanaBlackJack, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void btnApostarParActionPerformed(java.awt.event.ActionEvent evt) {
                lanzarDados("par", Integer.parseInt(ObtenerIU.obtenerSeleccionCombo(cbValorApostado)));

        }

        private void btnApostarImparActionPerformed(java.awt.event.ActionEvent evt) {
                lanzarDados("impar", Integer.parseInt(ObtenerIU.obtenerSeleccionCombo(cbValorApostado)));

        }

        private void btnApostarSumaActionPerformed(java.awt.event.ActionEvent evt) {
                String input = JOptionPane.showInputDialog(this,
                                "Adivina el número de la suma de ambos dados (2-12):");
                int numeroIngresado = -1;
                try {
                        numeroIngresado = Integer.parseInt(input);

                        if (numeroIngresado >= 2 && numeroIngresado <= 12) {

                                lanzarDados("suma",
                                                Integer.parseInt(ObtenerIU
                                                                .obtenerSeleccionCombo(cbValorApostado)),
                                                numeroIngresado);

                        } else {

                                JOptionPane.showMessageDialog(this,
                                                "El número ingresado no es válido (debe estar entre 2 y 12).",
                                                "ERROR",
                                                JOptionPane.ERROR_MESSAGE);
                        }
                } catch (HeadlessException | NumberFormatException e) {
                        JOptionPane.showMessageDialog(this,
                                        "El número ingresado no es válido.",
                                        "ERROR",
                                        JOptionPane.ERROR_MESSAGE);
                }
        }

        private void btnApostarDoblesActionPerformed(java.awt.event.ActionEvent evt) {
                try {
                        String inputDado1 = JOptionPane.showInputDialog(this,
                                        "Adivina el número del primer dado (1-6):");
                        String inputDado2 = JOptionPane.showInputDialog(this,
                                        "Adivina el número del segundo dado (1-6):");

                        int numeroDado1 = Integer.parseInt(inputDado1);
                        int numeroDado2 = Integer.parseInt(inputDado2);

                        if ((numeroDado1 >= 1 && numeroDado1 <= 6) && (numeroDado2 >= 1 && numeroDado2 <= 6)) {
                                lanzarDados("dobles",
                                                Integer.parseInt(ObtenerIU.obtenerSeleccionCombo(cbValorApostado)),
                                                numeroDado1,
                                                numeroDado2);
                        } else {
                                JOptionPane.showMessageDialog(
                                                null,
                                                "Los números ingresados no son válidos (deben estar entre 1 y 6).",
                                                "ERROR",
                                                JOptionPane.ERROR_MESSAGE);
                        }
                } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(
                                        null,
                                        "Los valores ingresados no son válidos. Por favor ingresa números enteros entre 1 y 6.",
                                        "ERROR",
                                        JOptionPane.ERROR_MESSAGE);
                }

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
                EventQueue.invokeLater(() -> new Dados().setVisible(true));
        }

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btnApostarDobles;
        private javax.swing.JButton btnApostarImpar;
        private javax.swing.JButton btnApostarPar;
        private javax.swing.JButton btnApostarSuma;
        private javax.swing.JButton btnDepositar;
        private javax.swing.JComboBox<String> cbValorApostado;
        private javax.swing.JLabel imgVolver;
        private javax.swing.JLabel lbApuesta;
        private javax.swing.JLabel lbDado1;
        private javax.swing.JLabel lbDado2;
        private javax.swing.JLabel lbDados;
        private javax.swing.JLabel lbPonerFondos;
        private javax.swing.JPanel ventanaBlackJack;
        // End of variables declaration//GEN-END:variables
}
