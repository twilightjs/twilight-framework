package com.twilight.orm.configuration.properties;

public class MapProperties extends MapPropertiesImplementation implements Properties {
    @Override
    public void putProperty(String key, String value) {
        super.putProperty(key, value);
    }

    @Override
    public void setProperty(String key, String value) {
        super.setProperty(key, value);
    }

    @Override
    public String getPropertyKey(String value) {
        return super.getPropertyKey(value);
    }

    @Override
    public String getPropertyValue(String key) {
        return super.getPropertyValue(key);
    }

    @Override
    public Dictionary[] getProperties() {
        return super.getProperties();
    }
}
