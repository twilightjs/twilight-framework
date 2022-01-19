package com.twilight.tools.parsers.uri;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URIImpl implements URI {
    private static String uri;

    public URIImpl(String uri) {
        URIImpl.uri = uri;
    }

    public String getPath() {
        Matcher matcher = Pattern.compile("/[a-zA-Z]+[.][a-zA-Z]+|/[a-zA-Z]+").matcher(uri);
        StringBuilder path = new StringBuilder();
        while (matcher.find()) path.append(matcher.group());
        if (path.length() == 0) path = new StringBuilder("/");
        return path.toString();
    }

    public boolean isPathEquals(String path) {
        return getPath().equals(path);
    }
}
