package com.twilight.orm.configuration.properties;

public interface Properties {
    public void putProperty(String key, String value);

    public void setProperty(String key, String value);

    public String getPropertyKey(String value);

    public String getPropertyValue(String key);

    public Dictionary[] getProperties();
}
