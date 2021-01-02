package de.stuttgart.hft.bookapp.communication;

public class StartServer {
	public static void main(String[] args) {
		BookServer server = new BookServer();
		server.startServer(8080, "/api");
	}
}