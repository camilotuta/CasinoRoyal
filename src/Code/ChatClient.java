package Code;

import java.io.*;
import java.net.*;
import javax.swing.*;

public class ChatClient {

	private static final String SERVER_ADDRESS = "localhost";
	private Socket socket = null;
	private JTextArea taChat;
	private JTextArea taMensaje;
	private JLabel imgEnviar;

	public ChatClient(String nombreCliente, JTextArea taChatJFrame, JTextArea taMensajeJFrame, JLabel imgEnviarJFrame,
			int PORT) {
		this.taChat = taChatJFrame;
		this.taMensaje = taMensajeJFrame;
		this.imgEnviar = imgEnviarJFrame;

		try {

			socket = new Socket(SERVER_ADDRESS, PORT);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			taChat.append("Conectado al chat como " + nombreCliente + "\n");

			Thread listenerThread = new Thread(() -> {
				String serverMessage;
				try {
					while ((serverMessage = in.readLine()) != null) {
						taChat.append(serverMessage + "\n");
					}
				} catch (IOException e) {
					taChat.append("Conexión cerrada por el servidor.\n");
				}
			});
			listenerThread.start();

			imgEnviar.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent evt) {
					enviarMensaje(nombreCliente);
				}
			});

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void enviarMensaje(String nombreCliente) {
		String userMessage = taMensaje.getText().trim();
		if (!userMessage.isEmpty()) {
			taChat.append(nombreCliente + ": " + userMessage + "\n");
			taMensaje.setText("");
			try {

				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				out.println(nombreCliente + ": " + userMessage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
