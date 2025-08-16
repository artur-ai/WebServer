package com.maiboroda;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    private int port;
    private String webPath;
    private static final Logger logger = Logger.getLogger(Server.class.getName());

    public Server(int port, String webPath) {
        this.port = port;
        this.webPath = webPath;
    }

    public static void main(String[] args) {
        new Server(3000, "src/resources").start();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("com.maiboroda.Server started on port " + port + ", waiting for users...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                logger.info("Client connected " + clientSocket.getInetAddress());
                new Thread(new RequestHandler(clientSocket, webPath)).start();
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error with server", e);
        }
    }
}
