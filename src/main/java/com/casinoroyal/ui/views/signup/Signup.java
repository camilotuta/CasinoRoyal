/*
 cspell:ignore Boton cedula codigo biografia desencriptar operacion tahoma verificacion  conexion
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main.java.com.casinoRoyal.ui.views.signup;

import main.java.com.casinoRoyal.core.database.Conexion;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import java.time.LocalDate;
import java.util.ArrayList;

import main.java.com.casinoRoyal.core.database.OperacionCRUD;
import main.java.com.casinoRoyal.core.security.VerificarDato;
import main.java.com.casinoRoyal.service.communication.EnviarCodigoVerificacion;
import main.java.com.casinoRoyal.service.communication.GenerarCodigo;
import main.java.com.casinoRoyal.core.security.Desencriptar;
import main.java.com.casinoRoyal.ui.utils.ObtenerIU;
import main.java.com.casinoRoyal.ui.views.login.Login;
import net.miginfocom.swing.MigLayout;
import raven.datetime.component.date.DateEvent;
import raven.datetime.component.date.DatePicker;
import raven.datetime.component.date.DateSelectionListener;
import java.sql.Connection;

import javax.swing.JFormattedTextField;

/**
 *
 * @author tutaa
 */
public class Signup extends javax.swing.JFrame {

	private EnviarCodigoVerificacion enviarCodigo;
	private boolean correoVerificado = false;
	public static boolean fechaValida = false;
	private boolean correoEnviado = false;

	/**
	 * Creates new form Signup
	 */
	public Signup() {
		initComponents();

		this.setTitle("Registrarse");
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		this.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(getClass().getResource("/main/resources/assets/img/icon.png")));
		desactivarBotonRegistrarse();
		desactivarBotonEnviarCodigo();
		desactivarBotonVerificarCodigo();
		desactivarCamposContraseña();
		mostrarErrores();
		eventoRegistrar();
	}

	private void eventoRegistrar() {

		pfContraseña.addActionListener(e -> {
			registrarUsuario();
		});

		pfConfirmarContraseña.addActionListener(e -> {
			registrarUsuario();
		});
	}

	private void desactivarBotonRegistrarse() {
		btnRegistrarse.setEnabled((fechaValida && correoVerificado
				&& (VerificarDato.nombreValido(ObtenerIU.obtenerTextoCampo(tfNombre)))
				&& (VerificarDato.correoValido(ObtenerIU.obtenerTextoCampo(tfCorreo)))
				&& (VerificarDato.cedulaValida(ObtenerIU.obtenerTextoCampo(tfCedula)))
				&& (VerificarDato.contraseñaValida(Desencriptar
						.desencriptarContra(ObtenerIU.obtenerContraseña(pfContraseña))))
				&& (VerificarDato.confirmarContraseñaValida(
						Desencriptar.desencriptarContra(
								ObtenerIU.obtenerContraseña(pfContraseña)),
						Desencriptar.desencriptarContra(
								ObtenerIU.obtenerContraseña(pfConfirmarContraseña))))));
	}

	private void desactivarBotonEnviarCodigo() {
		btnEnviarCodigo.setEnabled(VerificarDato.nombreValido(ObtenerIU.obtenerTextoCampo(tfNombre))
				&& VerificarDato.correoValido(ObtenerIU.obtenerTextoCampo(tfCorreo)));
	}

	private void desactivarBotonVerificarCodigo() {
		btnVerificarCodigo.setEnabled(correoEnviado
				&& VerificarDato.codigoValido(ObtenerIU.obtenerTextoCampo(
						tfRecibirCodigo))
				&& VerificarDato.correoValido(ObtenerIU.obtenerTextoCampo(tfCorreo)));
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
			JOptionPane.showMessageDialog(this, "EL CÓDIGO ES CORRECTO.");
			pfContraseña.setEnabled(true);
			pfConfirmarContraseña.setEnabled(true);
			enviarCodigo.setIntentos(3);
			correoVerificado = true;
			activarCamposContraseña();
			btnEnviarCodigo.setEnabled(false);
			btnVerificarCodigo.setEnabled(false);
		} else if (enviarCodigo.getIntentos() == 0) {
			JOptionPane.showMessageDialog(rootPane, "NO TIENE MÁS INTENTOS.");
			tfRecibirCodigo.setEnabled(false);
			tfCorreo.setEnabled(false);
			btnEnviarCodigo.setEnabled(false);
			btnVerificarCodigo.setEnabled(false);
		} else {
			enviarCodigo.setIntentos(enviarCodigo.getIntentos() - 1);
			JOptionPane.showMessageDialog(this,
					"EL CÓDIGO NO ES CORRECTO.\nTIENE " + enviarCodigo.getIntentos()
							+ " INTENTOS.");
		}
	}

	private boolean correoEstaRegistrado(String correo) {
		ArrayList<ArrayList<Object>> datosUsuarioRegistrado;

		try (Connection conn = Conexion.conectar()) {

			datosUsuarioRegistrado = OperacionCRUD.seleccionar(
					conn,
					String.format("SELECT correo_jugador FROM jugadores WHERE correo_jugador = '%s'", correo),
					new String[] { "correo_jugador" });

			return datosUsuarioRegistrado.size() == 1;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}

	private boolean IdEstaRegistrado(String idUsuario) {
		ArrayList<ArrayList<Object>> datosUsuarioRegistrado;

		try (Connection conn = Conexion.conectar()) {

			datosUsuarioRegistrado = OperacionCRUD.seleccionar(
					conn,
					String.format("SELECT jugador_id FROM jugadores WHERE jugador_id = %s", idUsuario),
					new String[] { "jugador_id" });

			return datosUsuarioRegistrado.size() == 1;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}

	private void enviarCodigo() throws HeadlessException, SQLException {
		String correo = ObtenerIU.obtenerTextoCampo(tfCorreo).toLowerCase();
		String nombre = ObtenerIU.obtenerTextoCampo(tfNombre);
		String codigo = GenerarCodigo.getCodigo();

		if (!correoEstaRegistrado(correo)) {

			String listaCodigo[] = codigo.split(""), text = "";

			for (int i = 0; i < listaCodigo.length; i++) {
				if (i != listaCodigo.length - 1) {
					text += listaCodigo[i] + "-";
				} else {
					text += listaCodigo[i];
				}
			}

			String asunto = "Verificación de correo electrónico en Casino Royal";
			String mensaje = "&#x1F44B; Hola, " + nombre + ".<br><br>"
					+ "¡Bienvenido/a a Casino Royal! Antes de que puedas comenzar a utilizar tu cuenta, necesitamos verificar que tu correo electrónico sea válido. "
					+ "Para ello, utiliza el siguiente código de verificación:<br><br>"
					+ "&#128273; <strong style=\"font-size: 24px;\">" + text + "</strong><br><br>"
					+ "Por favor, ingresa este código en la página de verificación de correo electrónico y sigue las instrucciones para verificar tu cuenta.<br><br>"
					+ "Si no has creado una cuenta en Casino Royal, por favor ignora este correo electrónico y asegúrate de proteger tu cuenta.<br><br>"
					+ "Si tienes alguna pregunta o necesitas ayuda, no dudes en contactar a nuestro equipo de soporte. &#128516;<br><br>"
					+ "¡Que tengas un excelente día! &#128077;<br><br>"
					+ "Atentamente,<br>"
					+ "El equipo de Casino Royal. &#128170;";

			enviarCodigo = new EnviarCodigoVerificacion(correo, asunto, mensaje, codigo);
			tfNombre.setEditable(false);
			tfCorreo.setEditable(false);
			correoEnviado = true;

		} else {
			JOptionPane.showMessageDialog(this, "YA EXISTE UNA CUENTA CON ESTE CORREO.");
		}
	}

	private void registrarUsuario() {
		if (btnRegistrarse.isEnabled()) {

			String cedula = ObtenerIU.obtenerTextoCampo(tfCedula);

			if (!IdEstaRegistrado(cedula)) {

				String correo = ObtenerIU.obtenerTextoCampo(tfCorreo).toLowerCase();
				String contraseña = Desencriptar.desencriptarContra(ObtenerIU.obtenerContraseña(pfContraseña));

				String nombre = ObtenerIU.obtenerTextoCampo(tfNombre);
				String fechaNacimiento = ObtenerIU.obtenerFechaSeleccionada(datePicker);

				String fechaNacimientoSQL = String.format("'%s'", fechaNacimiento);

				try (Connection conn = Conexion.conectar()) {
					OperacionCRUD.registrar(
							conn,
							String.format(
									"INSERT INTO jugadores (jugador_id, nombre_usuario, correo_jugador, password_jugador, fondos_jugador, biografia, fecha_nacimiento, juego_id) "
											+ "VALUES ('%s', '%s', '%s', '%s', 0.0, ' ', %s, NULL);",
									cedula, nombre, correo, contraseña, fechaNacimientoSQL));

					JOptionPane.showMessageDialog(this, "¡REGISTRO EXITOSO!", "¡AVISO!",
							JOptionPane.INFORMATION_MESSAGE);

					Login.correoGuardar = correo;
					Login login = new Login();
					login.setVisible(true);
					dispose();

				} catch (SQLException e) {

					JOptionPane.showMessageDialog(this, "Error al registrar el usuario: " + e.getMessage(), "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}

			} else {

				JOptionPane.showMessageDialog(this, "YA EXISTE UNA CUENTA CON ESTA CÉDULA.");
			}
		}
	}

	private void mostrarErrores() {

		// tfNombre
		VerificarDato.verificarCampo(!VerificarDato.nombreValido(ObtenerIU.obtenerTextoCampo(
				tfNombre)),
				lbErrorNombre, "El nombre tiene mínimo 8 caracteres.",
				"El nombre debe tener mínimo 8 caracteres.");

		// fechaNacimiento
		VerificarDato.verificarFechaNacimiento(ftFechaNacimiento.getText().equals("--/--/----"),
				lbErrorEdad, "Ha seleccionado una fecha.", "Debe ser mayor de edad.");

		// tfCorreo
		VerificarDato.verificarCampo(!VerificarDato.correoValido(ObtenerIU.obtenerTextoCampo(
				tfCorreo)),
				lbErrorCorreo, "El correo es válido.", "El correo no es válido.");

		// tfCedula
		VerificarDato.verificarCampo(!VerificarDato.cedulaValida(ObtenerIU.obtenerTextoCampo(
				tfCedula)),
				lbErrorCedula, "La cedula es válida.",
				"Ingrese un numero de cédula válido.");

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

		datePicker = new DatePicker();
		ventanaSignup = new javax.swing.JPanel();
		lbRegistro = new javax.swing.JLabel();
		lbNombre = new javax.swing.JLabel();
		tfNombre = new javax.swing.JTextField();
		lbErrorNombre = new javax.swing.JLabel();
		lbEdad = new javax.swing.JLabel();
		ftFechaNacimiento = new JFormattedTextField();
		lbErrorEdad = new javax.swing.JLabel();
		lbCedula = new javax.swing.JLabel();
		tfCedula = new javax.swing.JTextField();
		lbErrorCedula = new javax.swing.JLabel();
		lbCorreo = new javax.swing.JLabel();
		tfCorreo = new javax.swing.JTextField();
		lbErrorCorreo = new javax.swing.JLabel();
		btnEnviarCodigo = new javax.swing.JButton();
		tfRecibirCodigo = new javax.swing.JTextField();
		btnVerificarCodigo = new javax.swing.JButton();
		lbContraseña = new javax.swing.JLabel();
		pfContraseña = new javax.swing.JPasswordField();
		lbErrorContraseña = new javax.swing.JLabel();
		lbConfContraseña = new javax.swing.JLabel();
		pfConfirmarContraseña = new javax.swing.JPasswordField();
		lbErrorConfContraseña = new javax.swing.JLabel();
		btnRegresar = new javax.swing.JButton();
		btnRegistrarse = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		ventanaSignup.setBackground(new java.awt.Color(27, 9, 5));
		ventanaSignup.setForeground(new java.awt.Color(224, 195, 102));
		ventanaSignup.setPreferredSize(new java.awt.Dimension(1080, 720));
		ventanaSignup.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		lbRegistro.setFont(new java.awt.Font("Crabs", 1, 100)); // NOI18N
		lbRegistro.setForeground(new java.awt.Color(227, 199, 104));
		lbRegistro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lbRegistro.setText("Registro");
		ventanaSignup.add(lbRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1080, -1));

		lbNombre.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
		lbNombre.setForeground(new java.awt.Color(224, 195, 102));
		lbNombre.setText("NOMBRE COMPLETO: ");
		ventanaSignup.add(lbNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, -1, -1));

		tfNombre.setBackground(new java.awt.Color(27, 9, 5));
		tfNombre.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		tfNombre.setForeground(new java.awt.Color(148, 161, 178));
		tfNombre.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
		tfNombre.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				tfNombreKeyReleased(evt);
			}
		});
		ventanaSignup.add(tfNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 200, 310, 30));
		ventanaSignup.add(lbErrorNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 200, 25, 25));

		lbEdad.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
		lbEdad.setForeground(new java.awt.Color(224, 195, 102));
		lbEdad.setText("FECHA NACIMIENTO:");
		ventanaSignup.add(lbEdad, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, -1, -1));

		datePicker.setDateSelectionMode(DatePicker.DateSelectionMode.SINGLE_DATE_SELECTED);

		datePicker.setEditor(ftFechaNacimiento);
		setLayout(new MigLayout());

		ftFechaNacimiento.setBackground(new java.awt.Color(36, 38, 41));
		ftFechaNacimiento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		ftFechaNacimiento.setForeground(new java.awt.Color(255, 255, 254));
		ventanaSignup.add(ftFechaNacimiento,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 250, 170, 30));

		datePicker.setDateSelectionAble(localDate -> !localDate.isAfter(LocalDate.now()));
		datePicker.addDateSelectionListener(new DateSelectionListener() {
			@Override
			public void dateSelected(DateEvent dateEvent) {
				mostrarErrores();
				desactivarBotonRegistrarse();
			}
		});
		ventanaSignup.add(lbErrorEdad, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 250, 25, 25));

		lbCedula.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
		lbCedula.setForeground(new java.awt.Color(224, 195, 102));
		lbCedula.setText("CÉDULA:");
		ventanaSignup.add(lbCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, -1, -1));

		tfCedula.setBackground(new java.awt.Color(27, 9, 5));
		tfCedula.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		tfCedula.setForeground(new java.awt.Color(148, 161, 178));
		tfCedula.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
		tfCedula.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				tfCedulaKeyReleased(evt);
			}
		});
		ventanaSignup.add(tfCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 300, 310, 30));
		ventanaSignup.add(lbErrorCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 300, 25, 25));

		lbCorreo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
		lbCorreo.setForeground(new java.awt.Color(224, 195, 102));
		lbCorreo.setText("CORREO ELECTRÓNICO: ");
		ventanaSignup.add(lbCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 350, -1, -1));

		tfCorreo.setBackground(new java.awt.Color(27, 9, 5));
		tfCorreo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		tfCorreo.setForeground(new java.awt.Color(148, 161, 178));
		tfCorreo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
		tfCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				tfCorreoKeyReleased(evt);
			}
		});
		ventanaSignup.add(tfCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 350, 310, 30));
		ventanaSignup.add(lbErrorCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 350, 25, 25));

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
					JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		ventanaSignup.add(btnEnviarCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 400, -1, -1));

		tfRecibirCodigo.setBackground(new java.awt.Color(27, 9, 5));
		tfRecibirCodigo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		tfRecibirCodigo.setForeground(new java.awt.Color(148, 161, 178));
		tfRecibirCodigo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
		tfRecibirCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				tfRecibirCodigoKeyReleased(evt);
			}
		});
		ventanaSignup.add(tfRecibirCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 400, 180, 30));

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
		ventanaSignup.add(btnVerificarCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 400, -1, -1));

		lbContraseña.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
		lbContraseña.setForeground(new java.awt.Color(224, 195, 102));
		lbContraseña.setText("CONTRASEÑA: ");
		ventanaSignup.add(lbContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 460, -1, -1));

		pfContraseña.setBackground(new java.awt.Color(27, 9, 5));
		pfContraseña.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		pfContraseña.setForeground(new java.awt.Color(148, 161, 178));
		pfContraseña.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
		pfContraseña.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				pfContraseñaKeyReleased(evt);
			}
		});
		ventanaSignup.add(pfContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 460, 310, 30));
		ventanaSignup.add(lbErrorContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 460, 25, 25));

		lbConfContraseña.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
		lbConfContraseña.setForeground(new java.awt.Color(224, 195, 102));
		lbConfContraseña.setText("CONFIRMAR CONTRASEÑA: ");
		ventanaSignup.add(lbConfContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 520, -1, -1));

		pfConfirmarContraseña.setBackground(new java.awt.Color(27, 9, 5));
		pfConfirmarContraseña.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		pfConfirmarContraseña.setForeground(new java.awt.Color(148, 161, 178));
		pfConfirmarContraseña.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
		pfConfirmarContraseña.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				pfConfirmarContraseñaKeyReleased(evt);
			}
		});
		ventanaSignup.add(pfConfirmarContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 520, 310, 30));
		ventanaSignup.add(lbErrorConfContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 520, 25, 25));

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
		ventanaSignup.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 580, -1, -1));

		btnRegistrarse.setBackground(new java.awt.Color(147, 128, 67));
		btnRegistrarse.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		btnRegistrarse.setForeground(new java.awt.Color(255, 255, 254));
		btnRegistrarse.setText("Registrarse");
		btnRegistrarse.setActionCommand("Ingresar");
		btnRegistrarse.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnRegistrarse.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnRegistrarseActionPerformed(evt);

			}
		});
		ventanaSignup.add(btnRegistrarse, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 580, -1, -1));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(ventanaSignup, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(ventanaSignup, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void tfCedulaKeyReleased(java.awt.event.KeyEvent evt) {
		desactivarBotonRegistrarse();
		mostrarErrores();
	}

	private void pfContraseñaKeyReleased(java.awt.event.KeyEvent evt) {
		desactivarBotonRegistrarse();
		mostrarErrores();
	}

	private void pfConfirmarContraseñaKeyReleased(java.awt.event.KeyEvent evt) {
		desactivarBotonRegistrarse();
		mostrarErrores();
	}

	private void btnRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {
		registrarUsuario();
	}

	private void btnVerificarCodigoActionPerformed(java.awt.event.ActionEvent evt) {
		verificarCodigo();
	}

	private void tfRecibirCodigoKeyReleased(java.awt.event.KeyEvent evt) {
		desactivarBotonVerificarCodigo();
		mostrarErrores();
	}

	private void tfNombreKeyReleased(java.awt.event.KeyEvent evt) {
		desactivarBotonRegistrarse();
		desactivarBotonEnviarCodigo();
		mostrarErrores();
	}

	private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {
		Login login = new Login();
		login.setVisible(true);
		dispose();
	}

	private void tfCorreoKeyReleased(java.awt.event.KeyEvent evt) {
		desactivarBotonRegistrarse();
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
		EventQueue.invokeLater(() -> new Signup().setVisible(true));
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnEnviarCodigo;
	private javax.swing.JButton btnRegistrarse;
	private javax.swing.JButton btnRegresar;
	private javax.swing.JButton btnVerificarCodigo;
	private javax.swing.JLabel lbCedula;
	public static DatePicker datePicker;
	private javax.swing.JLabel lbConfContraseña;
	private javax.swing.JLabel lbContraseña;
	private javax.swing.JLabel lbCorreo;
	private javax.swing.JLabel lbEdad;
	private javax.swing.JLabel lbErrorCedula;
	private javax.swing.JLabel lbErrorConfContraseña;
	private javax.swing.JLabel lbErrorContraseña;
	private javax.swing.JLabel lbErrorCorreo;
	private javax.swing.JLabel lbErrorEdad;
	private javax.swing.JLabel lbErrorNombre;
	private javax.swing.JLabel lbNombre;
	private javax.swing.JLabel lbRegistro;
	private javax.swing.JPasswordField pfConfirmarContraseña;
	private javax.swing.JPasswordField pfContraseña;
	private javax.swing.JTextField tfCedula;
	private javax.swing.JTextField tfCorreo;
	private javax.swing.JTextField tfNombre;
	private javax.swing.JTextField tfRecibirCodigo;
	private javax.swing.JPanel ventanaSignup;
	private JFormattedTextField ftFechaNacimiento;
	// End of variables declaration//GEN-END:variables
}
