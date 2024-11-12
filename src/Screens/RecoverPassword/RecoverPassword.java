/*
	cSpell:ignore desencriptar operacion verificacion boton codigo tahoma  conexion
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Screens.RecoverPassword;

import Code.Conexion;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.sql.Connection;

import Code.OperacionCRUD;
import Code.VerificarDato;
import Code.Desencriptar;
import Code.EnviarCodigoVerificacion;
import Code.GenerarCodigo;
import Screens.Custom.ObtenerIU;
import Screens.Login.Login;

/**
 *
 * @author tutaa
 */
public class RecoverPassword extends javax.swing.JFrame {

	private boolean correoVerificado = false;
	private EnviarCodigoVerificacion enviarCodigo;
	private boolean correoEnviado = false;
	private boolean botonesActivos = true;

	/**
	 * Creates new form RecoverPassword
	 */
	public RecoverPassword() {
		initComponents();

		this.setTitle("Recuperar Contraseña");
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		this.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(getClass().getResource("/img/icon.png")));
		desactivarBotonConfirmar();
		desactivarBotonEnviarCodigo();
		desactivarBotonVerificarCodigo();
		desactivarCamposContraseña();
		mostrarErrores();
	}

	private void desactivarBotonConfirmar() {
		btnConfirmar.setEnabled((correoVerificado
				&& (VerificarDato.correoValido(ObtenerIU.obtenerTextoCampo(tfCorreo)))
				&& (VerificarDato.contraseñaValida(Desencriptar
						.desencriptarContra(ObtenerIU.obtenerContraseña(pfContraseña))))
				&& (VerificarDato.confirmarContraseñaValida(
						Desencriptar.desencriptarContra(
								ObtenerIU.obtenerContraseña(pfContraseña)),
						Desencriptar.desencriptarContra(
								ObtenerIU.obtenerContraseña(pfConfirmarContraseña))))));
	}

	private void desactivarBotonEnviarCodigo() {
		if (botonesActivos) {
			btnEnviarCodigo.setEnabled(VerificarDato.correoValido(ObtenerIU.obtenerTextoCampo(tfCorreo)));
		}
	}

	private void desactivarBotonVerificarCodigo() {
		if (botonesActivos) {
			btnVerificarCodigo.setEnabled(correoEnviado
					&& VerificarDato.codigoValido(ObtenerIU.obtenerTextoCampo(
							tfRecibirCodigo))
					&& VerificarDato.correoValido(ObtenerIU.obtenerTextoCampo(tfCorreo)));
		}
	}

	private void activarCamposContraseña() {
		pfContraseña.setEnabled(true);
		pfConfirmarContraseña.setEnabled(true);
	}

	private void desactivarCamposContraseña() {
		pfContraseña.setEnabled(false);
		pfConfirmarContraseña.setEnabled(false);
	}

	private void verificarCodigo() {

		if (ObtenerIU.obtenerTextoCampo(tfRecibirCodigo).equals(enviarCodigo.getCodigo())
				&& enviarCodigo.getIntentos() > 0) {
			JOptionPane.showMessageDialog(null, "EL CÓDIGO ES CORRECTO.");
			pfContraseña.setEnabled(true);
			pfConfirmarContraseña.setEnabled(true);
			enviarCodigo.setIntentos(3);
			correoVerificado = true;
			activarCamposContraseña();

			tfCorreo.setEditable(false);
			tfRecibirCodigo.setEditable(false);
			btnEnviarCodigo.setEnabled(false);
			btnVerificarCodigo.setEnabled(false);
			botonesActivos = false;
		} else if (enviarCodigo.getIntentos() == 0) {
			JOptionPane.showMessageDialog(rootPane, "NO TIENE MÁS INTENTOS.");
			tfRecibirCodigo.setEnabled(false);
			tfCorreo.setEnabled(false);
			btnEnviarCodigo.setEnabled(false);
			btnVerificarCodigo.setEnabled(false);
		} else {
			enviarCodigo.setIntentos(enviarCodigo.getIntentos() - 1);
			JOptionPane.showMessageDialog(null,
					"EL CÓDIGO NO ES CORRECTO.\nTIENE " + enviarCodigo.getIntentos()
							+ " INTENTOS.");
		}
	}

	private boolean correoEstaRegistrado(String correo) throws SQLException {
		ArrayList<ArrayList<Object>> datosUsuarioRegistrado;

		try (Connection conn = Conexion.conectar()) {
			datosUsuarioRegistrado = OperacionCRUD.seleccionar(
					conn,
					String.format("SELECT * FROM jugadores WHERE correo_jugador = '%s'", correo),
					new String[] { "correo_jugador" });

			return datosUsuarioRegistrado.size() == 1;
		} catch (SQLException e) {
			System.out.println("Error al verificar el correo: " + e.getMessage());
			throw e;
		}
	}

	private void enviarCodigo() throws HeadlessException, SQLException {
		String correo = ObtenerIU.obtenerTextoCampo(tfCorreo).toLowerCase();
		String codigo = GenerarCodigo.getCodigo();

		if (correoEstaRegistrado(correo)) {
			String listaCodigo[] = codigo.split(""), text = "";

			for (int i = 0; i < listaCodigo.length; i++) {
				if (i != listaCodigo.length - 1) {
					text += listaCodigo[i] + "-";
				} else {
					text += listaCodigo[i];
				}
			}

			String asunto = "Restablecer tu contraseña en Casino Royal.";
			String mensaje = "&#x1F44B; Hola, " + obtenerNombre(ObtenerIU.obtenerTextoCampo(tfCorreo))
					+ ".<br><br>"
					+ "Has recibido este correo electrónico porque has solicitado restablecer tu contraseña en Casino Royal. Para continuar, utiliza el siguiente código de verificación:<br><br>"
					+ "&#128273; <strong style=\"font-size: 24px;\">" + text + "</strong><br><br>"
					+ "Por favor, ingresa este código en la página de restablecimiento de contraseña y sigue las instrucciones para crear una nueva contraseña segura.<br><br>"
					+ "Si no has solicitado el restablecimiento de tu contraseña, por favor ignora este correo electrónico y asegúrate de proteger tu cuenta.<br><br>"
					+ "Si tienes alguna pregunta o necesitas ayuda, no dudes en contactar a nuestro equipo de soporte. &#128516;<br><br>"
					+ "¡Que tengas un excelente día! &#128077;<br><br>"
					+ "Atentamente,<br>"
					+ "El equipo de Casino Royal. &#128170;";

			enviarCodigo = new EnviarCodigoVerificacion(correo, asunto, mensaje, codigo);
			tfCorreo.setEditable(false);
			correoEnviado = true;

		} else {
			JOptionPane.showMessageDialog(null, "NO EXISTE UNA CUENTA CON ESTE CORREO.");
		}
	}

	private String obtenerNombre(String correo) throws SQLException {
		ArrayList<ArrayList<Object>> datos;

		try (Connection conn = Conexion.conectar()) {
			datos = OperacionCRUD.seleccionar(
					conn,
					String.format("SELECT nombre_usuario FROM jugadores WHERE correo_jugador = '%s'", correo),
					new String[] { "nombre_usuario" });

			if (!datos.isEmpty()) {
				return (String) datos.get(0).get(0);
			} else {
				return "";
			}
		} catch (SQLException e) {
			System.out.println("Error al obtener el nombre: " + e.getMessage());
			throw e;
		}
	}

	private void actualizarContraseña() throws SQLException {
		String correo = ObtenerIU.obtenerTextoCampo(tfCorreo).toLowerCase();
		String contraseña = Desencriptar.desencriptarContra(ObtenerIU.obtenerContraseña(pfConfirmarContraseña));

		try (Connection conn = Conexion.conectar()) {
			OperacionCRUD.actualizar(
					conn,
					String.format("UPDATE jugadores SET password_jugador = '%s' WHERE correo_jugador = '%s'",
							contraseña, correo));
			JOptionPane.showMessageDialog(this, "¡CONTRASEÑA ACTUALIZADA!", "¡AVISO!",
					javax.swing.JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			System.out.println("Error al actualizar la contraseña: " + e.getMessage());
			JOptionPane.showMessageDialog(this, "Error al actualizar la contraseña", "¡ERROR!",
					javax.swing.JOptionPane.ERROR_MESSAGE);
			throw e;
		}
	}

	private void mostrarErrores() {

		// pfContraseña
		VerificarDato.verificarCampo(
				!VerificarDato.contraseñaValida(
						Desencriptar.desencriptarContra(ObtenerIU.obtenerContraseña(
								pfContraseña))),
				lbErrorContraseña, "La contraseña tiene mínimo 8 caracteres.",
				"La contraseña debe tener mínimo 8 caracteres.");

		// pfConfContraseña
		VerificarDato.verificarCampo(
				(!VerificarDato.confirmarContraseñaValida(
						Desencriptar.desencriptarContra(ObtenerIU.obtenerContraseña(
								pfContraseña)),
						Desencriptar.desencriptarContra(
								ObtenerIU.obtenerContraseña(pfConfirmarContraseña)))),
				lbErrorConfContraseña, "Las contraseñas son iguales.",
				"Las contraseñas deben ser iguales.");
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
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		ventanaSignup = new javax.swing.JPanel();
		lbErrorContraseña = new javax.swing.JLabel();
		lbErrorConfContraseña = new javax.swing.JLabel();
		lbRegistro = new javax.swing.JLabel();
		lbCorreo = new javax.swing.JLabel();
		tfCorreo = new javax.swing.JTextField();
		btnEnviarCodigo = new javax.swing.JButton();
		tfRecibirCodigo = new javax.swing.JTextField();
		btnVerificarCodigo = new javax.swing.JButton();
		lbConfContraseña = new javax.swing.JLabel();
		pfConfirmarContraseña = new javax.swing.JPasswordField();
		lbNuevaContraseña = new javax.swing.JLabel();
		pfContraseña = new javax.swing.JPasswordField();
		btnConfirmar = new javax.swing.JButton();
		btnRegresar = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		ventanaSignup.setBackground(new java.awt.Color(27, 9, 5));
		ventanaSignup.setForeground(new java.awt.Color(224, 195, 102));
		ventanaSignup.setPreferredSize(new java.awt.Dimension(1080, 720));
		ventanaSignup.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
		ventanaSignup.add(lbErrorContraseña,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 340, 25, 25));
		ventanaSignup.add(lbErrorConfContraseña,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 420, 25, 25));

		lbRegistro.setFont(new java.awt.Font("Crabs", 1, 70)); // NOI18N
		lbRegistro.setForeground(new java.awt.Color(227, 199, 104));
		lbRegistro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lbRegistro.setText("Recuperar Contraseña");
		ventanaSignup.add(lbRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 760, -1));

		lbCorreo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
		lbCorreo.setForeground(new java.awt.Color(224, 195, 102));
		lbCorreo.setText("CORREO ELECTRÓNICO: ");
		ventanaSignup.add(lbCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, -1, -1));

		tfCorreo.setBackground(new java.awt.Color(27, 9, 5));
		tfCorreo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		tfCorreo.setForeground(new java.awt.Color(255, 255, 255));
		tfCorreo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
		tfCorreo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				tfCorreoActionPerformed(evt);
			}
		});
		tfCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				tfCorreoKeyReleased(evt);
			}
		});
		ventanaSignup.add(tfCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 200, 310, 30));

		btnEnviarCodigo.setBackground(new java.awt.Color(147, 128, 67));
		btnEnviarCodigo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		btnEnviarCodigo.setForeground(new java.awt.Color(255, 255, 254));
		btnEnviarCodigo.setText("Enviar Código");
		btnEnviarCodigo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnEnviarCodigo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					btnEnviarCodigoActionPerformed(evt);
				} catch (HeadlessException | SQLException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		ventanaSignup.add(btnEnviarCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, -1, -1));

		tfRecibirCodigo.setBackground(new java.awt.Color(27, 9, 5));
		tfRecibirCodigo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		tfRecibirCodigo.setForeground(new java.awt.Color(255, 255, 255));
		tfRecibirCodigo.setBorder(
				new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
		tfRecibirCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				tfRecibirCodigoKeyReleased(evt);
			}
		});
		ventanaSignup.add(tfRecibirCodigo,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 250, 180, 30));

		btnVerificarCodigo.setBackground(new java.awt.Color(147, 128, 67));
		btnVerificarCodigo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		btnVerificarCodigo.setForeground(new java.awt.Color(255, 255, 254));
		btnVerificarCodigo.setText("Verificar");
		btnVerificarCodigo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnVerificarCodigo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnVerificarCodigoActionPerformed(evt);
			}
		});
		ventanaSignup.add(btnVerificarCodigo,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 250, -1, -1));

		lbConfContraseña.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
		lbConfContraseña.setForeground(new java.awt.Color(224, 195, 102));
		lbConfContraseña.setText("CONFIRMAR CONTRASEÑA: ");
		ventanaSignup.add(lbConfContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 420, -1, -1));

		pfConfirmarContraseña.setBackground(new java.awt.Color(27, 9, 5));
		pfConfirmarContraseña.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		pfConfirmarContraseña.setForeground(new java.awt.Color(255, 255, 255));
		pfConfirmarContraseña.setBorder(
				new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
		pfConfirmarContraseña.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				pfConfirmarContraseñaKeyReleased(evt);
			}
		});
		ventanaSignup.add(pfConfirmarContraseña,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 420, 310, 30));

		lbNuevaContraseña.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
		lbNuevaContraseña.setForeground(new java.awt.Color(224, 195, 102));
		lbNuevaContraseña.setText("NUEVA CONTRASEÑA: ");
		ventanaSignup.add(lbNuevaContraseña,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, -1, -1));

		pfContraseña.setBackground(new java.awt.Color(27, 9, 5));
		pfContraseña.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		pfContraseña.setForeground(new java.awt.Color(255, 255, 255));
		pfContraseña.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
		pfContraseña.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				pfContraseñaKeyReleased(evt);
			}
		});
		ventanaSignup.add(pfContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 340, 310, 30));

		btnConfirmar.setBackground(new java.awt.Color(147, 128, 67));
		btnConfirmar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		btnConfirmar.setForeground(new java.awt.Color(255, 255, 254));
		btnConfirmar.setText("Confirmar");
		btnConfirmar.setActionCommand("Ingresar");
		btnConfirmar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					btnConfirmarActionPerformed(evt);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		ventanaSignup.add(btnConfirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 550, -1, -1));

		btnRegresar.setBackground(new java.awt.Color(147, 128, 67));
		btnRegresar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		btnRegresar.setForeground(new java.awt.Color(255, 255, 254));
		btnRegresar.setText("Regresar");
		btnRegresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnRegresar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnRegresarActionPerformed(evt);
			}
		});
		ventanaSignup.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 550, -1, -1));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(ventanaSignup, javax.swing.GroupLayout.PREFERRED_SIZE,
								758, javax.swing.GroupLayout.PREFERRED_SIZE));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(ventanaSignup, javax.swing.GroupLayout.PREFERRED_SIZE,
								625, javax.swing.GroupLayout.PREFERRED_SIZE));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void tfCorreoActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private void pfContraseñaKeyReleased(java.awt.event.KeyEvent evt) {
		desactivarBotonConfirmar();
		mostrarErrores();
	}

	private void pfConfirmarContraseñaKeyReleased(java.awt.event.KeyEvent evt) {
		desactivarBotonConfirmar();
		mostrarErrores();
	}

	private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {
		actualizarContraseña();

		Login.correoGuardar = ObtenerIU.obtenerTextoCampo(tfCorreo);
		Login login = new Login();
		login.setVisible(true);
		this.setVisible(false);
	}

	private void btnVerificarCodigoActionPerformed(java.awt.event.ActionEvent evt) {
		verificarCodigo();
	}

	private void tfRecibirCodigoKeyReleased(java.awt.event.KeyEvent evt) {
		desactivarBotonVerificarCodigo();
		mostrarErrores();
	}

	private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {
		Login login = new Login();
		login.setVisible(true);
		this.setVisible(false);

	}

	private void tfCorreoKeyReleased(java.awt.event.KeyEvent evt) {
		desactivarBotonConfirmar();
		desactivarBotonEnviarCodigo();
		desactivarBotonVerificarCodigo();
		mostrarErrores();
	}

	private void btnEnviarCodigoActionPerformed(java.awt.event.ActionEvent evt)
			throws HeadlessException, SQLException {
		enviarCodigo();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		FlatMacDarkLaf.setup();
		EventQueue.invokeLater(() -> new RecoverPassword().setVisible(true));
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnConfirmar;
	private javax.swing.JButton btnEnviarCodigo;
	private javax.swing.JButton btnRegresar;
	private javax.swing.JButton btnVerificarCodigo;
	private javax.swing.JLabel lbConfContraseña;
	private javax.swing.JLabel lbCorreo;
	private javax.swing.JLabel lbErrorConfContraseña;
	private javax.swing.JLabel lbErrorContraseña;
	private javax.swing.JLabel lbNuevaContraseña;
	private javax.swing.JLabel lbRegistro;
	private javax.swing.JPasswordField pfConfirmarContraseña;
	private javax.swing.JPasswordField pfContraseña;
	private javax.swing.JTextField tfCorreo;
	private javax.swing.JTextField tfRecibirCodigo;
	private javax.swing.JPanel ventanaSignup;
	// End of variables declaration//GEN-END:variables
}
