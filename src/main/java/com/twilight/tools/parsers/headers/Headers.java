package com.twilight.tools.parsers.headers;

public interface Headers {
    public void put(String name, String value);

    public void set(String name, String value);

    public String getValue(String name);

    public String getName(String value);

    public Header[] getHeaders();
}
