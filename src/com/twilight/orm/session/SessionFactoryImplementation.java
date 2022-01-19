package com.twilight.orm.session;

import com.twilight.orm.map.SQLQuery;
import com.twilight.orm.map.SQLQueryCreator;
import com.twilight.orm.parser.Parser;
import com.twilight.orm.parser.ParserObject;
import com.twilight.orm.configuration.properties.Properties;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SessionFactoryImplementation {
    private Statement statementQuery;
    private Properties mapFields;
    private Object statement;
    private Parser parser;
    private SQLQuery sqlQuery;


    protected SessionFactoryImplementation(Connection connection) {
        try {
            this.statementQuery = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void setUpSession(Object statement, Properties mapFields, Properties mapType) {
        this.statement = statement;
        this.mapFields = mapFields;
        this.parser = new ParserObject(mapFields, statement);
        this.sqlQuery = new SQLQueryCreator(this.parser, mapType);
    }

    protected void addData() {
//        System.out.println(this.sqlQuery.doInsertQuery());
        executeUpdate(this.sqlQuery.doInsertQuery());
    }

    protected Object getData(String filter) {
        return getDataMapping(filter);
    }


    private ResultSet getDataExecuteQuery(String filter) {
        System.out.println(this.sqlQuery.doSelectQuery(filter));
        ResultSet resultQuery = null;
        try {
            resultQuery = this.statementQuery.executeQuery(this.sqlQuery.doSelectQuery(filter));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultQuery;
    }

    private Object getDataMapping(String filter) {
        try {
            ResultSet resultQuery = getDataExecuteQuery(filter);
            while (resultQuery.next()) {
                for (Object column : this.parser.getColumns()) {
                    Field field = this.statement.getClass().getDeclaredField(this.mapFields.getPropertyKey(String.valueOf(column)));
//                    System.out.println(field.getName());
                    field.setAccessible(true);
                    field.set(this.statement, resultQuery.getObject(this.mapFields.getPropertyKey(String.valueOf(column))));
//                    System.out.println(field.get(this.statement));
//                    System.out.println();
                }
            }
        } catch (SQLException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return this.statement;
    }


    protected void updateData(String filter) {
//        System.out.println(this.sqlQuery.doUpdateQuery(filter));
        executeUpdate(this.sqlQuery.doUpdateQuery(filter));
    }


    protected void deleteData(String filter) {
//        System.out.println(this.sqlQuery.doDeleteQuery(filter));
        executeUpdate(this.sqlQuery.doDeleteQuery(filter));
    }

    private void executeUpdate(String sql) {
        try {
            this.statementQuery.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void close() {
        try {
            this.statementQuery.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

//        StringBuilder sqlQuery = new StringBuilder(query.doInsertQuery(this.parser)).append("(");
//        try {
//            for (String column : this.parser.getColumns()) {
//                Field field = this.statement.getClass().getDeclaredField(this.properties.getPropertyKey(column));
//                field.setAccessible(true);
//                sqlQuery.append("'").append(field.get(this.statement)).append("'").append(", ");
//                System.out.println(field.get(this.statement));
//            }
//            System.out.println(sqlQuery.append(");").replace(sqlQuery.indexOf(", );"), sqlQuery.indexOf(");"), ""));
//        } catch (NoSuchFieldException | IllegalAccessException e) {
//            e.printStackTrace();
//        }

//        Parser parser = new ParserObject(this.properties, this.statement);
//        SQLQuery query = new SQLQueryCreator();
//        try {
//            ResultSet resultQuery = this.statementQuery.executeQuery(query.doSelect(parser) + " " + filter + ";");
////            ResultSet resultQuery = this.statementQuery.executeQuery("SELECT login, password, number FROM users_catalog " + filter+  ";");
//            while (resultQuery.next()) {
//                for (String column : parser.getColumns()) {
//                    Field field = this.statement.getClass().getDeclaredField(this.properties.getPropertyKey(column));
//                    System.out.println(field.getName());
//                    field.setAccessible(true);
//                    field.set(this.statement, resultQuery.getString(this.properties.getPropertyKey(column)));
//                    System.out.println(field.get(this.statement));
//                    System.out.println();
//                }
//            }
//        } catch (SQLException | NoSuchFieldException | IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        return this.statement;
