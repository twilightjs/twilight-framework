package com.twilight.orm.configuration;

import com.twilight.orm.map.Map;
import com.twilight.orm.session.Session;

import java.sql.SQLException;

public interface Configuration {
    public Map getMap();


    public Session getSession() throws SQLException;
}
