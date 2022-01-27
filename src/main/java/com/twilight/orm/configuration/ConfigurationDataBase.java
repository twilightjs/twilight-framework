package com.twilight.orm.configuration;

import com.twilight.orm.map.Map;
import com.twilight.orm.session.Session;
import com.twilight.orm.configuration.properties.Properties;

import java.sql.SQLException;

public class ConfigurationDataBase extends ConfigurationDataBaseImplementation implements Configuration {
    protected ConfigurationDataBase(Properties properties) {
        super(properties);
    }

    public static Configuration configure(Properties properties) {
        return ConfigurationDataBaseImplementation.configure(properties);
    }

    @Override
    public Map getMap() {
        return null;
    }

    @Override
    public Session getSession() throws SQLException {
        return super.getSession();
    }
}
