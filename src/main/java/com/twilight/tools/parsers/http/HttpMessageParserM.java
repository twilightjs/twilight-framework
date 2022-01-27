package com.twilight.tools.parsers.http;

import com.twilight.tools.parsers.headers.Header;
import com.twilight.tools.parsers.uri.URI;

public class HttpMessageParserM extends HttpMessageParserImplementation implements HttpParserM {

    protected HttpMessageParserM(String request) {
        super(request);
    }

    public static HttpParserM getHttpParser(String request) {
        return new HttpMessageParserM(request);
    }

    @Override
    public String getRequest() {
        return super.getRequest();
    }

    @Override
    public String getMethod() {
        return super.getMethod();
    }

    @Override
    public URI getURI() {
        return super.getURI();
    }

    @Override
    public String getVersion() {
        return super.getVersion();
    }

    @Override
    public Header[] getHeaders() {
        return super.getHeaders();
    }

    @Override
    public String getBody() {
        return super.getBody();
    }
}
