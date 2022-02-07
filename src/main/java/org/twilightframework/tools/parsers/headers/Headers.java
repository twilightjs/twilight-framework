package org.twilightframework.tools.parsers.headers;

public interface Headers {
    void put(String name, String value);

    void set(String name, String value);

    String getValue(String name);

    String getName(String value);

    Header[] getHeaders();
}
