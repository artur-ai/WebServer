package com.maiboroda;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ResourceReader {
    private String webPath;

    public ResourceReader(String webPath) {
        this.webPath = webPath;
    }

    public byte[] readResource(String uri) throws IOException {
        if (uri.contains("index")) {
            return Files.readAllBytes(Paths.get(webPath,   "index.html"));
        } else if (uri.contains("css")) {
            return Files.readAllBytes(Paths.get(webPath,   "css", "style.css"));
        } else {
            return "<h1>404 Not Found</h1>".getBytes();
        }
    }
}
