package servidorChatsCasinoRoyal;

import consultas.Actualizar;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ChatServer {

	private static final List<ClientHandler> clients = new ArrayList<>();
	private static VistaServidor vistaServidor;
	private static int juegoId;

	public ChatServer(int PORT, VistaServidor vistaServidor, int juegoIdPoner) {
		juegoId = juegoIdPoner;
		ChatServer.vistaServidor = vistaServidor;
		appendMessage("Servidor de chat iniciado...");

		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			while (true) {
				Socket clientSocket = serverSocket.accept();
				appendMessage("Cliente conectado: " + clientSocket.getInetAddress());
				ClientHandler clientHandler = new ClientHandler(clientSocket);
				clients.add(clientHandler);
				Actualizar.actualizarPersonasConectadas(juegoId, clients.size());

				new Thread(clientHandler).start();
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void appendMessage(String message) {
		vistaServidor.appendToChat(message);
	}

	public static void broadcast(String message, ClientHandler sender) {
		for (ClientHandler client : clients) {
			if (client != sender) {
				client.sendMessage(message);
			}
		}
		sender.getVistaServidor().appendToChat(message);
	}

	static class ClientHandler implements Runnable {

		private final Socket socket;
		private PrintWriter out;
		private BufferedReader in;

		public ClientHandler(Socket socket) {
			this.socket = socket;
		}

		public void sendMessage(String message) {
			out.println(message);
		}

		@Override
		public void run() {
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);

				String message;
				while ((message = in.readLine()) != null) {
					ChatServer.broadcast(message, this);
				}
			} catch (SocketException e) {
				if ("Connection reset".equals(e.getMessage())) {
					clients.remove(this);
				} else {
					JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR AL ESCRIBIR MENSAJE",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR AL ESCRIBIR MENSAJE",
						JOptionPane.ERROR_MESSAGE);
			} finally {
				try {
					socket.close();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR Al CERRAR CHAT",
							JOptionPane.ERROR_MESSAGE);
				}
				clients.remove(this);
				Actualizar.actualizarPersonasConectadas(juegoId, clients.size());
			}

		}

		public VistaServidor getVistaServidor() {
			return vistaServidor;
		}
	}
}
