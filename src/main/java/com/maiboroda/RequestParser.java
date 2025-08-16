package com.maiboroda;

import java.io.BufferedReader;
import java.io.IOException;

public class RequestParser {

    public Request parse(BufferedReader reader) throws IOException {
        String requestLine = reader.readLine();
        if (requestLine == null || !requestLine.startsWith("GET")) {
            return new Request(null, null);
        }

        String uri = requestLine.split(" ")[1];

        while (!(reader.readLine()).isEmpty()) {
        }

        return new Request(uri, HttpMethod.GET);
    }
}
