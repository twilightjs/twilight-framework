package com.twilight.orm.parser;

import com.twilight.orm.configuration.properties.Properties;

public interface Parser {
    public Properties getProperties();

    public Object getStatement();

    public String getTable();

    public String getColumn();

    public Object[] getColumns();

    public String getTable(Properties properties, Object statement);

    public String getColumn(Properties properties, Object statement);

    public String[] getColumns(Properties properties, Object statement);
}
