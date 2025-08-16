package com.maiboroda;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestHandler implements Runnable {
    private Socket clientSocket;
    private String webPath;
    private static final Logger logger = Logger.getLogger(RequestHandler.class.getName());

    public RequestHandler(Socket clientSocket, String webPath) {
        this.clientSocket = clientSocket;
        this.webPath = webPath;
    }

    @Override
    public void run() {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                OutputStream outputStream = clientSocket.getOutputStream()
        ) {
            RequestParser parser = new RequestParser();
            Request request = parser.parse(reader);

            ResourceReader resourceReader = new ResourceReader(webPath);
            byte[] content = resourceReader.readResource(request.getUri());

            ResponseWriter.writeResponseToClientSuccess(content, outputStream);

        } catch (IOException exception) {
            logger.log(Level.SEVERE, "Client connection error", exception);
        }
    }
}
