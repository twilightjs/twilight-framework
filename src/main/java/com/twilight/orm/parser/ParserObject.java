package com.twilight.orm.parser;

import com.twilight.orm.configuration.properties.Properties;

public class ParserObject extends ParserObjectImplementation implements Parser {

    public ParserObject() {

    }

    public ParserObject(Properties properties, Object statement) {
        super(properties, statement);
    }

    @Override
    public Properties getProperties() {
        return super.getProperties();
    }

    @Override
    public Object getStatement() {
        return super.getStatement();
    }

    @Override
    public String getTable() {
        return super.getTable();
    }

    @Override
    public String getColumn() {
        return super.getColumn();
    }

    @Override
    public Object[] getColumns() {
        return super.getColumns();
    }

    @Override
    public String getTable(Properties properties, Object statement) {
        return super.getTable(properties, statement);
    }

    @Override
    public String getColumn(Properties properties, Object statement) {
        return super.getColumn(properties, statement);
    }

    @Override
    public String[] getColumns(Properties properties, Object statement) {
        return super.getColumns(properties, statement);
    }
}
