package com.twilight.server.io.components.builder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ResponseBuilderImplementation extends ResponseBuilder {
    @Override
    public ResponseBuilder setStatusCode(int statusCode, String explanatoryPhrase) {
        this.statusCode = String.join(" ", String.valueOf(statusCode), explanatoryPhrase) + "\n" + "Server: Twilight";
        return this;
    }

    @Override
    public ResponseBuilder setHeader(String parameter, String value) {
        this.headers += String.join(": ", parameter, value) + "\n";
        return this;
    }

    @Override
    public ResponseBuilder setMessageBody(String messageBody) {
        this.body = messageBody.getBytes(StandardCharsets.UTF_8);
        return this;
    }

    @Override
    public ResponseBuilder setFile(String path) {
        this.body = triedSetFile(path);
        return this;
    }

    private byte[] triedSetFile(String path) {
        byte[] fileBytes = new byte[0];
        try {
            fileBytes = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileBytes;
    }

    @Override
    public ResponseBuilder build() {
        this.response += String.join("\n", this.statusCode, this.headers, "");
        return this;
    }
}
