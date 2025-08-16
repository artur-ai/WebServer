package com.maiboroda;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ResponseWriterTest {

    @Test
    void testWriteResponseToClientSuccess() throws IOException {

        String body = "<html><body>Hello World</body></html>";
        byte[] content = body.getBytes();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ResponseWriter.writeResponseToClientSuccess(content, outputStream);

        String result = outputStream.toString();

        assertTrue(result.contains("HTTP/1.1 200 OK"));
        assertTrue(result.contains("Content-Type: text/html"));
        assertTrue(result.contains("Content-Length: " + content.length));

        assertTrue(result.endsWith(body));
    }

    @Test
    void testEmptyContent() throws IOException {
        byte[] content = new byte[0];
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ResponseWriter.writeResponseToClientSuccess(content, outputStream);

        String result = outputStream.toString();

        assertTrue(result.contains("HTTP/1.1 200 OK"));
        assertTrue(result.contains("Content-Length: 0"));
        assertTrue(result.endsWith(""));
    }

    @Test
    void testResponse() throws IOException {
        byte[] content = "Test".getBytes();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ResponseWriter.writeResponseToClientSuccess(content, outputStream);

        assertEquals(outputStream.size(),
                ("HTTP/1.1 200 OK\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Content-Length: " + content.length + "\r\n\r\n" +
                        "Test").getBytes().length);
    }
}

