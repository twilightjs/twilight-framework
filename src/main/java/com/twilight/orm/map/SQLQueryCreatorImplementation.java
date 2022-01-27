package com.twilight.orm.map;

import com.twilight.orm.configuration.properties.Properties;
import com.twilight.orm.converter.Converter;
import com.twilight.orm.converter.ConverterType;
import com.twilight.orm.parser.Parser;

import java.lang.reflect.Field;

public class SQLQueryCreatorImplementation {
    private Parser parser;
    private Properties mapType;
    private Converter converter;

    protected SQLQueryCreatorImplementation(Parser parser, Properties mapType) {
        this.parser = parser;
        this.mapType = mapType;
        this.converter = new ConverterType();
    }

    protected String doInsertQuery() {
        return String.valueOf(setInsertCommand(this.parser).append(setInsertValues(this.parser)));
    }

    private StringBuilder setInsertCommand(Parser parser) {
        return new StringBuilder("INSERT INTO ").append(this.parser.getTable()).append(" (").append(getFormatColumns(this.parser, ", ")).append(") ");
    }

    private StringBuilder setInsertValues(Parser parser) {
        StringBuilder sqlInsertQuery = new StringBuilder("VALUES (");
        Object[] column = this.parser.getColumns();
        int i = 0;
        for (String value : getFormatColumnNestedFields(parser)) {
            sqlInsertQuery.append(this.converter.convert(value, mapType.getPropertyValue((String) column[i]))).append(", ");
            i++;
        }
        return sqlInsertQuery.replace(sqlInsertQuery.length() - 2, sqlInsertQuery.length(), "").append(");");
    }

    protected String doSelectQuery(String filter) {
        return String.valueOf(new StringBuilder("SELECT ").append(getFormatColumns(this.parser, ", ")).append(" FROM ").append(this.parser.getTable()).append(" WHERE ").append(filter).append(";"));
    }

    protected String doUpdateQuery(String filter) {
        StringBuilder sqlUpdateQuery = new StringBuilder("UPDATE ").append(this.parser.getTable()).append("\n").append("SET ");
        Object[] column = this.parser.getColumns();
        int i = 0;
        for (String value : getFormatColumnNestedFields(this.parser)) {
            sqlUpdateQuery.append(column[i]).append(" = ").append(this.converter.convert(value, mapType.getPropertyValue((String) column[i]))).append(", ");
            i++;
        }
        return String.valueOf(sqlUpdateQuery.replace(sqlUpdateQuery.length() - 2, sqlUpdateQuery.length(), "")
                .append("\n").append("WHERE ").append(filter).append(";"));
    }

    protected String doDeleteQuery(String filter) {
        return String.valueOf(new StringBuilder("DELETE FROM ").append(this.parser.getTable()).append("\n").append("WHERE ").append(filter).append(";"));
    }

    private String getFormatColumns(Parser parser, String delimiter) {
        StringBuilder formatted = new StringBuilder();
        for (Object column : parser.getColumns()) {
            formatted.append(column).append(delimiter);
        }
        return String.valueOf(formatted.replace(formatted.length() - 2, formatted.length(), ""));
    }

    private String[] getFormatColumnNestedFields(Parser parser) {
        String[] values = new String[parser.getColumns().length];
        try {
            int i = 0;
            for (Object column : parser.getColumns()) {
                Field field = parser.getStatement().getClass().getDeclaredField(parser.getProperties().getPropertyKey(String.valueOf(column)));
                field.setAccessible(true);
                values[i] = String.valueOf(field.get(parser.getStatement()));
                i++;
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return values;
    }
}

//    protected String doInsertQuery(Parser parser) {
//        StringBuilder sqlInsertQuery = new StringBuilder("INSERT INTO ").append(parser.getTable()).append(" ").append("(");
//        try {
//            for (String column : parser.getColumns()) {
//                sqlInsertQuery.append(column).append(", ");
//            }
//            sqlInsertQuery
//                    .append("VALUES ")
//                    .replace(sqlInsertQuery.indexOf(", VALUES"), sqlInsertQuery.indexOf("VALUES"), ") ")
//                    .append("(");
//            for (String column : parser.getColumns()) {
//                Field field = parser.getStatement().getClass().getDeclaredField(parser.getProperties().getPropertyKey(column));
//                field.setAccessible(true);
//                sqlInsertQuery.append("'").append(field.get(parser.getStatement())).append("'").append(", ");
//                System.out.println(field.get(parser.getStatement()));
//            }
//            sqlInsertQuery
//                    .append(");")
//                    .replace(sqlInsertQuery.indexOf(", );"), sqlInsertQuery.indexOf(");"), "");
//        } catch (NoSuchFieldException | IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        return String.valueOf(sqlInsertQuery);
//    }