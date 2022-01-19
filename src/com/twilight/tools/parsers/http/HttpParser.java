package com.twilight.tools.parsers.http;

import com.twilight.tools.parsers.headers.Header;
import com.twilight.tools.parsers.headers.Headers;
import com.twilight.tools.parsers.headers.MapHeaders;
import com.twilight.tools.parsers.uri.URI;
import com.twilight.tools.parsers.uri.URIImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpParser {
    private static String request;

    public HttpParser(String request) {
        HttpParser.request = request;
    }

    public String getRequest() {
        return request;
    }

    public String getMethod() {
        final Matcher matcher = Pattern.compile("[A-Z]+").matcher(request);
        String method = " ";
        if (matcher.find()) method = matcher.group().replace(" ", "");
        return method;
    }

    public URI getURI() {
        return new URIImpl(findURI());
    }

    private String findURI() {
        String uri = "/?#";
        final Matcher matcher = Pattern.compile(getMethod() + ".+" + getVersion()).matcher(request);
        if (matcher.find())
            uri = matcher.group()
                    .replaceFirst(getMethod(), "")
                    .replaceFirst(getVersion(), "");
        return uri.trim();
    }

    public String getVersion() {
        final Matcher matcher = Pattern.compile(" [A-Z]+/[0-9].[0-9]|[A-Z]+/[0-9].[0-9]").matcher(request);
        String version = " ";
        if (matcher.find()) version = matcher.group().replace(" ", "");
        return version;
    }

    public Map getParams() {

        return new HashMap();
    }


    public Header[] getHeaders() {
        Headers headers = new MapHeaders();
        final Matcher matcher = Pattern.compile("\\S+: .+|\n", Pattern.MULTILINE).matcher(request);
        int count = 0;
        while (matcher.find()) {
            if (matcher.group().equals("\n")) count++;
            if (!matcher.group().equals("\n")) {
                count = 0;
                headers.put(matcher.group().replaceFirst(": .+|\n", ""), matcher.group().replaceFirst("\\S+: ", ""));
            }
            if (count == 2) break;
        }
        return headers.getHeaders();
    }

    public String getBody() {
        final Matcher matcher = Pattern.compile(".+|\n", Pattern.MULTILINE).matcher(request);
        StringBuilder body = new StringBuilder();
        int count = 0;
        boolean messageBody = false;
        while (matcher.find()) {
            if (matcher.group().equals("\n") && !messageBody) count++;
            if (!matcher.group().equals("\n") && !messageBody) count = 0;
            if (count == 2) {
                messageBody = true;
                body.append(matcher.group());
            }
        }
        return isEmpty(body.toString());
    }

    private String isEmpty(String body) {
        String checkedBody = null;
        if (!body.isEmpty()) checkedBody = body;
        return checkedBody;
    }
}
