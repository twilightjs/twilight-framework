package com.twilight.orm.session;

import com.twilight.orm.configuration.properties.Properties;

public interface Session {
    public void setUpSession(Object statement, Properties mapFields, Properties mapType);

    public void addData();

    public Object getData(String filter);

    public void updateData(String filter);

    public void deleteData(String filter);

    public void close();
}
