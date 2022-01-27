package com.twilight.orm.session;

import com.twilight.orm.configuration.properties.Properties;

import java.sql.Connection;

public class SessionFactory extends SessionFactoryImplementation implements Session{

    public SessionFactory(Connection connection) {
        super(connection);
    }


    @Override
    public void setUpSession(Object statement, Properties mapFields, Properties mapType) {
        super.setUpSession(statement, mapFields, mapType);
    }

    @Override
    public void addData() {
        super.addData();
    }

    @Override
    public Object getData(String filter) {
        return super.getData(filter);
    }

    @Override
    public void updateData(String filter) {
        super.updateData(filter);
    }

    @Override
    public void deleteData(String filter) {
        super.deleteData(filter);
    }

    @Override
    public void close() {
        super.close();
    }
}
