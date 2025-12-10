package com.example.lostandfound.DAO;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class SQLBuilder {
    public StringBuilder sql = new StringBuilder();
    public void buildInsertSQL(String table, List<String> columns){
        String placeholder = columns.stream().map(c->"?").collect(Collectors.joining(", "));
        sql.append("INSERT INTO ");
        sql.append(table).append(" ");
        sql.append("(").append(String.join(", ",columns)).append(") ");
        sql.append("VALUES (").append(placeholder).append(")");
    }
    public void buildSelectSQL(String table, List<String> columns){
        sql.append("SELECT DISTINCT ");
        if(columns.isEmpty()){
            sql.append("* ");
        }else{
            sql.append(String.join(", ",columns)).append(" ");
        }
        sql.append("FROM ").append(table);
    }
    public void buildWhereSQL(String left, String condition, String right){
        sql.append(" WHERE ").append(left).append(" ").append(condition).append(" ?").append(right);
    }

    public String getSQL(){
        return sql.toString();
    }
}
