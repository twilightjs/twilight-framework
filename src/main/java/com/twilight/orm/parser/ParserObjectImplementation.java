package com.twilight.orm.parser;

import com.twilight.orm.configuration.properties.Properties;

import java.lang.reflect.Field;

public abstract class ParserObjectImplementation {
    private Properties properties;
    private Object statement;

    protected ParserObjectImplementation() {

    }

    protected ParserObjectImplementation(Properties properties, Object statement) {
        this.properties = properties;
        this.statement = statement;
    }
    protected Properties getProperties(){
        return this.properties;
    }

    protected Object getStatement() {
        return this.statement;
    }

    protected String getTable() {
        String[] splitTable = this.statement.getClass().getName().split("\\.");
        return this.properties.getPropertyValue(splitTable[splitTable.length - 1]);
    }


    protected String getColumn() {
        return null;
    }


    protected Object[] getColumns() {
        Object[] columns = new Object[this.statement.getClass().getDeclaredFields().length];
        Field[] fields = this.statement.getClass().getDeclaredFields();
        for (int i = 0; i < this.statement.getClass().getDeclaredFields().length; i++) {
            columns[i] = this.properties.getPropertyValue(fields[i].getName());
        }
        return columns;
    }

    protected String getTable(Properties properties, Object statement) {
        String[] splitTable = statement.getClass().getName().split("\\.");
        return properties.getPropertyValue(splitTable[splitTable.length - 1]);
    }


    protected String getColumn(Properties properties, Object statement) {
        return null;
    }


    protected String[] getColumns(Properties properties, Object statement) {
        String[] columns = new String[statement.getClass().getDeclaredFields().length];
        Field[] fields = statement.getClass().getDeclaredFields();
        for (int i = 0; i < statement.getClass().getDeclaredFields().length; i++) {
            columns[i] = properties.getPropertyValue(fields[i].getName());
        }
        return columns;
    }
}
