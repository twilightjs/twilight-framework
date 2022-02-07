package org.twilightframework.tools.parsers.uri;

import java.util.Map;

public interface URI {
    String getPath();

    Map<String, String> getParams();

    boolean isPathEquals(String path);
}
