package com.twilight.tools.parsers.http;

import com.twilight.tools.parsers.headers.Header;
import com.twilight.tools.parsers.uri.URI;

public interface HttpParserM {
    public String getRequest();

    public String getMethod();

    public URI getURI();

    public String getVersion();

    public Header[] getHeaders();

    public String getBody();
}
