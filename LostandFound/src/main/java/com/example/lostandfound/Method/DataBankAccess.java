package com.example.lostandfound.Method;

import com.example.lostandfound.DAO.Query;
import com.example.lostandfound.DAO.SQLBuilder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataBankAccess {
    public void input(String table, Object...values) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<String> columns = new ArrayList<>();
        Object[] args = new Object[values.length];
        int i = 0;
        for(Object value:values){
            columns.add(value.getClass().toString());
            args[i]=value;
            i++;
        }
        SQLBuilder sqlBuilder = new SQLBuilder();
        sqlBuilder.buildInsertSQL(table,columns);
        String sql = sqlBuilder.getSQL();
        Method method = Query.class.getMethod("update", String.class, Object[].class);
        Query query = new Query();
        method.invoke(query,sql,args);
    }

    public <T> List<T> query(String table, boolean cSwitch, String l, String condition, String r, Object...values) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<String> columns = new ArrayList<>();
        Object[] argsV = new Object[values.length];
        int i = 0;
        for(Object value:values){
            columns.add(value.getClass().toString());
            argsV[i]=value;
            i++;
        }
        SQLBuilder sqlBuilder = new SQLBuilder();
        sqlBuilder.buildSelectSQL(table,columns);
        if(cSwitch){
            sqlBuilder.buildWhereSQL(l,condition,r);
        }
        String sql = sqlBuilder.getSQL();
        Method method = Query.class.getMethod("queryForList", String.class, Class.class, Object[].class);
        Query query = new Query();
        return (List<T>) method.invoke(query,sql,Map.class,argsV);
    }
}
