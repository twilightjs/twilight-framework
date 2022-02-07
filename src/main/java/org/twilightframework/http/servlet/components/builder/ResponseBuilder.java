package org.twilightframework.http.servlet.components.builder;

import org.twilightframework.http.servlet.components.codes.*;
import org.twilightframework.http.servlet.components.methods.MethodsContent;

public abstract class ResponseBuilder {
    public interface HttpCodes extends CodesContent {
    }

    public interface HttpMethods extends MethodsContent {
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
