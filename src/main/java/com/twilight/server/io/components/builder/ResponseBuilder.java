package com.twilight.server.io.components.builder;

import com.twilight.server.io.components.codes.*;
import com.twilight.server.io.components.methods.MethodsImpl;

public abstract class ResponseBuilder {
    public interface HttpCodes extends Informational, Success, Redirection, ClientError, ServerError {
    }

    public interface HttpMethods extends MethodsImpl {
    }

    protected String response = "HTTP/1.1 ";
    protected String statusCode = "";
    protected String headers = "";
    protected byte[] body;

    public abstract ResponseBuilder setStatusCode(int statusCode, String explanatoryPhrase);

    public abstract ResponseBuilder setHeader(String parameter, String value);

    public abstract ResponseBuilder setMessageBody(String messageBody);

    public abstract ResponseBuilder setFile(String path);

    public abstract ResponseBuilder build();

    public String getResponse() {
        return response;
    }

    public byte[] getBody() {
        return body;
    }
}
