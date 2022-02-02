package com.twilight.tools.parsers.uri;

import java.util.HashMap;
import java.util.Map;
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

    @Override
    public Map<String, String> getParams() {
        Map<String, String> map = new HashMap<>();
        Matcher matcher = Pattern.compile("&\\w+=\\w+|\\?\\w+=\\w+").matcher(uri);
        while (matcher.find()) {
            map.put(matcher.group().replaceFirst("=\\w+", "")
                            .replaceFirst("[=?&]", ""),
                    matcher.group().replaceFirst("&\\w+=|\\?\\w+=", "")
                            .replaceFirst("[=?&]", ""));
        }
        return map;
    }

    public boolean isPathEquals(String path) {
        return getPath().equals(path);
    }
}
