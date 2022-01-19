package com.twilight.orm.map;

public interface SQLQuery {
    public String doInsertQuery();

    public String doSelectQuery(String filter);

    public String doUpdateQuery(String filter);

    public String doDeleteQuery(String filter);
}
