package org.twilightframework.tools.parsers.uri;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URIParser {
    private static String uri;

    public URIParser(String uri) {
        URIParser.uri = uri;
    }

    public String getPath() {
        Matcher matcher = Pattern.compile("/[a-zA-Z]+[.][a-zA-Z]+|/[a-zA-Z]+").matcher(uri);
        StringBuilder path = new StringBuilder();
        while (matcher.find()) path.append(matcher.group());
        if (path.length() == 0) path = new StringBuilder("/");
        return path.toString();
    }
    
    public Map<String, String> getParams() {
        Map<String, String> map = new HashMap<>();
        Matcher matcher = Pattern.compile("&\\w+=\\S+|\\?\\w+=\\S+").matcher(uri);
        while (matcher.find()) {
            System.out.println(matcher.group());
            map.put(matcher.group().replaceFirst("=\\S+", "")
                            .replaceFirst("[=?&]|#\\S+", ""),
                    matcher.group().replaceFirst("&\\S+=|\\?\\S+=", "")
                            .replaceFirst("[=?&]|#\\S+", ""));
        }
        return map;
    }

    public boolean isPathEquals(String path) {
        return getPath().equals(path);
    }
}
