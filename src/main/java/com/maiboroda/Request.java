package com.maiboroda;

import java.util.Map;

public class Request {
    private String uri;
    private HttpMethod method;
    private Map<String, String> headers;

    public Request(String uri, HttpMethod method) {
        this.uri = uri;
        this.method = method;
    }

    public String getUri() { return uri; }
    public HttpMethod getMethod() { return method; }
}
