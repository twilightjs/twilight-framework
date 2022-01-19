package com.twilight.tools.parsers.headers;

public class MapHeaders extends MapHeadersImplementation implements Headers {
    @Override
    public void put(String name, String value) {
        super.put(name, value);
    }

    @Override
    public void set(String name, String value) {
        super.set(name, value);
    }

    @Override
    public String getValue(String name) {
        return super.getValue(name);
    }

    @Override
    public String getName(String value) {
        return super.getName(value);
    }

    @Override
    public Header[] getHeaders() {
        return super.getHeaders();
    }
}
