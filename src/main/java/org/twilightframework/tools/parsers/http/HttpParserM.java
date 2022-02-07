package org.twilightframework.tools.parsers.http;

import org.twilightframework.tools.parsers.headers.Header;
import org.twilightframework.tools.parsers.uri.URI;

public interface HttpParserM {
    String getRequest();

    String getMethod();

    URI getURI();

    String getVersion();

    Header[] getHeaders();

    String getBody();
}
