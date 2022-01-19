package com.twilight.orm.configuration;

import com.twilight.orm.map.Map;
import com.twilight.orm.session.Session;
import com.twilight.orm.session.SessionFactory;
import com.twilight.orm.configuration.properties.Properties;

import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class ConfigurationDataBaseImplementation {
    private Properties properties;

    protected ConfigurationDataBaseImplementation(Properties properties) {
        this.properties = properties;
    }

    protected static Configuration configure(Properties properties) {
        return new ConfigurationDataBase(properties);
    }

    protected Map getMap() {
        return null;
    }

    protected Session getSession() throws SQLException {
        return new SessionFactory(DriverManager.getConnection(properties.getPropertyValue("url"), properties.getPropertyValue("user"), properties.getPropertyValue("password")));
    }
}
