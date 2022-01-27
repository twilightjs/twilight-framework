package com.twilight.orm.map;

import com.twilight.orm.configuration.properties.Properties;
import com.twilight.orm.parser.Parser;


public class SQLQueryCreator extends SQLQueryCreatorImplementation implements SQLQuery {

    public SQLQueryCreator(Parser parser, Properties mapType) {
        super(parser, mapType);
    }

    @Override
    public String doInsertQuery() {
        return super.doInsertQuery();
    }

    @Override
    public String doSelectQuery(String filter) {
        return super.doSelectQuery(filter);
    }

    @Override
    public String doUpdateQuery(String filter) {
        return super.doUpdateQuery(filter);
    }

    @Override
    public String doDeleteQuery(String filter) {
        return super.doDeleteQuery(filter);
    }
}
