package com.maiboroda;

import java.io.IOException;
import java.io.OutputStream;

public class ResponseWriter {

    public static void writeResponseToClientSuccess(byte[] content, OutputStream outputStream) throws IOException {
        String headers = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html\r\n" +
                "Content-Length: " + content.length + "\r\n\r\n";

        outputStream.write(headers.getBytes());
        outputStream.write(content);
        outputStream.flush();
    }
}
