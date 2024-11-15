/*
 cspell:ignore beleño sahagún desencriptar ubaque operacion boton nathalya tahoma conexion
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Screens.Login;

import Code.Conexion;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;

import javax.swing.JOptionPane;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import Code.Dates;
import Code.DatosUsuario;
import Code.Desencriptar;
import Code.OperacionCRUD;
import Code.VerificarDato;
import Screens.Custom.CambiarIU;
import Screens.Custom.ObtenerIU;
import Screens.Principal.Principal;
import Screens.RecoverPassword.RecoverPassword;
import Screens.Signup.Signup;

/**
 *
 * @author tutaa
 */
public class Login extends javax.swing.JFrame {

	public static int idUsuarioGuardar;
	public static String correoGuardar = "";

	/**
	 * Creates new form Login
	 */
	public Login() {
		initComponents();

		this.setTitle("Ingresar");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(getClass().getResource("/img/icon.png")));
		CambiarIU.ponerTextoEtiqueta(txtMostrarCopy,
				"©" + Dates.obtenerAño() + " Casino Royal . Todos los derechos reservados.");
		tfCorreo.requestFocus();
		CambiarIU.ponerTextoCampo(tfCorreo, correoGuardar);

		cargarDatos();

		desactivarBotonIngresar();
		eventoIngresar();
	}

	private void eventoIngresar() {

		tfCorreo.addActionListener(e -> {
			try {
				ingresarUsuario();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		});

		pfContraseña.addActionListener(e -> {
			try {
				ingresarUsuario();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		});
	}

	private void desactivarBotonIngresar() {
		btnIngresar.setEnabled(
				VerificarDato.correoValido((ObtenerIU.obtenerTextoCampo(tfCorreo))) && (VerificarDato
						.contraseñaValida(Desencriptar.desencriptarContra(
								ObtenerIU.obtenerContraseña(pfContraseña)))));
	}

	private void guardarDatos() {

		new DatosUsuario().guardarInfo(ObtenerIU.obtenerTextoCampo(tfCorreo), Desencriptar.desencriptarContra(
				ObtenerIU.obtenerContraseña(pfContraseña)));

	}

	private void cargarDatos() {
		String[] datos = new DatosUsuario().obtenerInfo();
		CambiarIU.ponerTextoCampo(tfCorreo, datos[0]);
		CambiarIU.ponerTextoCampoContraseña(pfContraseña, datos[1]);

	}

	private boolean usuarioEstaRegistrado(String correo, String contraseña) throws SQLException {
		ArrayList<ArrayList<Object>> datosUsuarioRegistrado;

		try (Connection conn = Conexion.conectar()) {
			if (conn == null) {
				throw new SQLException("No se pudo establecer la conexión a la base de datos.");
			}

			datosUsuarioRegistrado = OperacionCRUD.seleccionar(
					conn,
					String.format("SELECT * FROM jugadores WHERE correo_jugador = '%s' AND password_jugador = '%s'",
							correo, contraseña),
					new String[] { "jugador_id", "correo_jugador", "password_jugador" });

			if (!datosUsuarioRegistrado.isEmpty() && datosUsuarioRegistrado.get(0).size() >= 1) {
				idUsuarioGuardar = Integer.parseInt(datosUsuarioRegistrado.get(0).get(0).toString());
				return true;
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			throw e;
		}

		return false;
	}

	private void ingresarUsuario() throws SQLException {
		if (btnIngresar.isEnabled()) {
			String correo = ObtenerIU.obtenerTextoCampo(tfCorreo).toLowerCase();
			String contraseña = Desencriptar.desencriptarContra(ObtenerIU.obtenerContraseña(pfContraseña));

			if (usuarioEstaRegistrado(correo, contraseña)) {
				correoGuardar = correo;

				guardarDatos();

				Principal pantallaPrincipal = new Principal();
				pantallaPrincipal.setVisible(true);
				this.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(this, "CORREO O CONTRASEÑA NO VALIDOS \n", "AVISO!",
						javax.swing.JOptionPane.INFORMATION_MESSAGE);

				CambiarIU.ponerTextoCampoContraseña(pfContraseña, "");
				pfContraseña.requestFocus();
				desactivarBotonIngresar();
			}
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
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		ventanaLogIn = new javax.swing.JPanel();
		lbTitulo = new javax.swing.JLabel();
		btnErroresComunes = new javax.swing.JButton();
		lbCorreo = new javax.swing.JLabel();
		tfCorreo = new javax.swing.JTextField();
		lbContraseña = new javax.swing.JLabel();
		pfContraseña = new javax.swing.JPasswordField();
		lbCambiarContraseña = new javax.swing.JLabel();
		btnRegistrarse = new javax.swing.JButton();
		btnIngresar = new javax.swing.JButton();
		txtMostrarCopy = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		ventanaLogIn.setBackground(new java.awt.Color(27, 9, 5));
		ventanaLogIn.setForeground(new java.awt.Color(255, 255, 255));
		ventanaLogIn.setPreferredSize(new java.awt.Dimension(1080, 720));
		ventanaLogIn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		lbTitulo.setFont(new java.awt.Font("Crabs", 1, 100)); // NOI18N
		lbTitulo.setForeground(new java.awt.Color(255, 255, 254));
		lbTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lbTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logoPequeño.png"))); // NOI18N
		ventanaLogIn.add(lbTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 1080, 320));

		btnErroresComunes.setBackground(new java.awt.Color(147, 128, 67));
		btnErroresComunes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		btnErroresComunes.setForeground(new java.awt.Color(255, 255, 254));
		btnErroresComunes.setText("?");
		btnErroresComunes.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnErroresComunesActionPerformed(evt);
			}
		});
		ventanaLogIn.add(btnErroresComunes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 40, 30));

		lbCorreo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
		lbCorreo.setForeground(new java.awt.Color(224, 195, 102));
		lbCorreo.setText("CORREO: ");
		ventanaLogIn.add(lbCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 360, -1, -1));

		tfCorreo.setBackground(new java.awt.Color(27, 9, 5));
		tfCorreo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		tfCorreo.setForeground(new java.awt.Color(255, 255, 255));
		tfCorreo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
		tfCorreo.setOpaque(true);
		tfCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				tfCorreoKeyReleased(evt);
			}
		});
		ventanaLogIn.add(tfCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 360, 310, 30));

		lbContraseña.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
		lbContraseña.setForeground(new java.awt.Color(224, 195, 102));
		lbContraseña.setText("CONTRASEÑA: ");
		ventanaLogIn.add(lbContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 440, -1, -1));

		pfContraseña.setBackground(new java.awt.Color(27, 9, 5));
		pfContraseña.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		pfContraseña.setForeground(new java.awt.Color(255, 255, 255));
		pfContraseña.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
		pfContraseña.setOpaque(true);
		pfContraseña.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				pfContraseñaKeyReleased(evt);
			}
		});
		ventanaLogIn.add(pfContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 440, 310, 30));

		lbCambiarContraseña.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
		lbCambiarContraseña.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/help.png"))); // NOI18N
		lbCambiarContraseña.setToolTipText("¿Deseas recuperar o cambiar tu contraseña? Click aquí.");
		lbCambiarContraseña.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		lbCambiarContraseña.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lbCambiarContraseñaMouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				lbCambiarContraseñaMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				lbCambiarContraseñaMouseExited(evt);
			}
		});
		ventanaLogIn.add(lbCambiarContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 440, 30, 30));

		btnRegistrarse.setBackground(new java.awt.Color(147, 128, 67));
		btnRegistrarse.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		btnRegistrarse.setForeground(new java.awt.Color(255, 255, 255));
		btnRegistrarse.setText("Registrarse");
		btnRegistrarse.setActionCommand("Ingresar");
		btnRegistrarse.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnRegistrarse.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnRegistrarseActionPerformed(evt);
			}
		});
		ventanaLogIn.add(btnRegistrarse, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 510, -1, -1));

		btnIngresar.setBackground(new java.awt.Color(147, 128, 67));
		btnIngresar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		btnIngresar.setForeground(new java.awt.Color(255, 255, 255));
		btnIngresar.setText("Ingresar");
		btnIngresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnIngresar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					btnIngresarActionPerformed(evt);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		ventanaLogIn.add(btnIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 510, -1, -1));

		txtMostrarCopy.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		txtMostrarCopy.setForeground(new java.awt.Color(255, 255, 255));
		txtMostrarCopy.setText("©");
		ventanaLogIn.add(txtMostrarCopy, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 690, -1, -1));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(ventanaLogIn, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(ventanaLogIn, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void lbCambiarContraseñaMouseClicked(java.awt.event.MouseEvent evt) {
		RecoverPassword recoverPassword = new RecoverPassword();
		recoverPassword.setVisible(true);
		this.setVisible(false);
	}

	private void lbCambiarContraseñaMouseEntered(java.awt.event.MouseEvent evt) {
		CambiarIU.setImageLabel(lbCambiarContraseña, "src/img/helpHover.png");
	}

	private void lbCambiarContraseñaMouseExited(java.awt.event.MouseEvent evt) {
		CambiarIU.setImageLabel(lbCambiarContraseña, "src/img/help.png");
	}

	private void pfContraseñaKeyReleased(java.awt.event.KeyEvent evt) {
		desactivarBotonIngresar();
	}

	private void btnRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {
		Signup pantallaRegistro = new Signup();
		pantallaRegistro.setVisible(true);
		this.setVisible(false);
	}

	private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {
		ingresarUsuario();
	}

	private void tfCorreoKeyReleased(java.awt.event.KeyEvent evt) {
		desactivarBotonIngresar();
	}

	private void btnErroresComunesActionPerformed(java.awt.event.ActionEvent evt) {
		String texto = """
				\u00a1Hola! Aqu\u00ed te dejo un apartado de ayuda con algunos errores comunes y sus
				posibles soluciones:

				No puedes ingresar:
				Verifica tus credenciales de inicio de sesi\u00f3n.
				Restablece tu contrase\u00f1a si la has olvidado.
				Verifica tu conexi\u00f3n a internet.
				Si el problema persiste, contacta al soporte t\u00e9cnico de la plataforma.

				No puedes registrarte:
				Verifica que completaste todos los campos requeridos y que usaste una
				direcci\u00f3n de correo institucional v\u00e1lida.
				Si el problema persiste, intenta utilizar una direcci\u00f3n de correo electr\u00f3nico
				diferente o contacta al soporte t\u00e9cnico de la plataforma.

				Error al actualizar biograf\u00eda:
				Aseg\u00farate de seguir los requisitos de longitud y formato para la biograf\u00eda.
				Si el problema persiste, intenta actualizar tu biograf\u00eda desde otro
				dispositivo o navegador o contacta al soporte t\u00e9cnico de la plataforma.

				Error al agendar tutor\u00eda:
				Verifica que seleccionaste la fecha y hora correctas.
				Verifica que tienes los permisos necesarios para agendar una tutor\u00eda.
				Si el problema persiste, intenta utilizar otro dispositivo o navegador o
				contacta al soporte t\u00e9cnico de la plataforma.

				Error al actualizar lista de tareas:
				Aseg\u00farate de seguir los requisitos de longitud y formato para cada tarea en
				la lista.
				Verifica que tienes los permisos necesarios para actualizar la lista de
				tareas en la plataforma.

				Si necesitas ayuda adicional, por favor env\u00eda un correo especificando tu problema a alguno
				de los siguientes correos de contacto:

				Adri\u00e1n Camilo Tuta Cort\u00e9s: adriantuta.cc@academia.umb.edu.co
				Laura Nathalya Abril Velasquez: lauraabril.nv@academia.umb.edu.co""";

		JOptionPane.showMessageDialog(this, texto, "AYUDA", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		FlatMacDarkLaf.setup();
		EventQueue.invokeLater(() -> new Login().setVisible(true));
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnErroresComunes;
	private javax.swing.JButton btnIngresar;
	private javax.swing.JButton btnRegistrarse;
	private javax.swing.JLabel lbCambiarContraseña;
	private javax.swing.JLabel lbContraseña;
	private javax.swing.JLabel lbCorreo;
	private javax.swing.JLabel lbTitulo;
	private javax.swing.JPasswordField pfContraseña;
	private javax.swing.JTextField tfCorreo;
	private javax.swing.JLabel txtMostrarCopy;
	private javax.swing.JPanel ventanaLogIn;
	// End of variables declaration//GEN-END:variables
}
