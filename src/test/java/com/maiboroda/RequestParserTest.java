package com.maiboroda;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

public class RequestParserTest {
    RequestParser requestParser = new RequestParser();

    @Test
    void testValidGetRequest() throws IOException {
        String clientRequest =
                "GET /index.html HTTP/1.1\r\n" +
                        "Host: localhost\r\n" +
                        "Connection: close\r\n" +
                        "\r\n";

        BufferedReader bufferedReader = new BufferedReader(new StringReader(clientRequest));
        Request request = requestParser.parse(bufferedReader);

        assertNotNull(request);
        assertEquals("/index.html", request.getUri());
        assertEquals(HttpMethod.GET, request.getMethod());
    }

    @Test
    void testInvalidRequestLine() throws IOException {
        String clientRequest =
                "POST /index.html HTTP/1.1\r\n" +
                        "Host: localhost\r\n" +
                        "\r\n";

        BufferedReader reader = new BufferedReader(new StringReader(clientRequest));

        Request request = requestParser.parse(reader);

        assertNotNull(request);
        assertNull(request.getUri());
        assertNull(request.getMethod());
    }

    @Test
    void testEmptyRequest() throws IOException {
        String clientRequest = "";

        BufferedReader reader = new BufferedReader(new StringReader(clientRequest));


        Request request = requestParser.parse(reader);

        assertNotNull(request);
        assertNull(request.getUri());
        assertNull(request.getMethod());
    }

}
