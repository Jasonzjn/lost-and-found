package com.example.lostandfound.DAO;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Query {
    public static int update(String sql,Object... params) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = Conn.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            setParameters(preparedStatement,params);
            return preparedStatement.executeUpdate();
        } finally {
            Conn.close(connection,preparedStatement);
        }
    }
    public static <T> T queryForObject(String sql, Class<T> clazz, Object... params) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = Conn.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            setParameters(preparedStatement,params);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getObject(1,clazz);
            }
            return null;
        }finally {
            Conn.close(connection,preparedStatement,resultSet);
        }
    }

    public static Map<String,Object> queryForMap(String sql, Object... params) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = Conn.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            setParameters(preparedStatement,params);
            resultSet=preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            if(resultSet.next()){
                Map<String,Object> map = new HashMap<>();
                for(int i=1;i<metaData.getColumnCount();i++){
                    map.put(metaData.getColumnLabel(i),resultSet.getObject(i));
                }
                return map;
            }
            return null;
        }finally {
            Conn.close(connection,preparedStatement,resultSet);
        }
    }
    public static <T> List<T> queryForList(String sql, Class<T> clazz, Object... params) throws Exception {
        Connection connection =null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = Conn.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            setParameters(preparedStatement,params);
            resultSet=preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            List<T> list=new ArrayList<>();
            while(resultSet.next()){
                if(clazz==Map.class){
                    Map<String,Object> map = new HashMap<>();
                    for(int i=1;i<metaData.getColumnCount();i++){
                        map.put(metaData.getColumnLabel(i),resultSet.getObject(i));
                    }
                    list.add((T)map);
                }else{
                    T obj = clazz.newInstance();
                    for(int i=1;i<metaData.getColumnCount();i++){
                        setFieldValue(obj,metaData.getColumnLabel(i),resultSet.getObject(i));
                    }
                    list.add(obj);
                }
            }
            return list;
        }finally {
            Conn.close(connection,preparedStatement,resultSet);
        }
    }
    private static void setFieldValue(Object obj, String fieldName, Object value) throws Exception {
        if (value == null) return;

        Class<?> clazz = obj.getClass();

        // 尝试直接设置字段
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);

            // 类型转换
            value = convertValue(value, field.getType());
            field.set(obj, value);
        } catch (NoSuchFieldException e) {
            // 如果字段不存在，尝试setter方法
            String setterName = "set" +
                    fieldName.substring(0, 1).toUpperCase() +
                    fieldName.substring(1);

            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().equals(setterName) &&
                        method.getParameterCount() == 1) {
                    value = convertValue(value, method.getParameterTypes()[0]);
                    method.invoke(obj, value);
                    break;
                }
            }
        }
    }
    private static Object convertValue(Object value, Class<?> targetType) {
        if (value == null) return null;

        if (targetType.isInstance(value)) {
            return value;
        }

        String strValue = value.toString();

        if (targetType == Integer.class || targetType == int.class) {
            return Integer.parseInt(strValue);
        } else if (targetType == Long.class || targetType == long.class) {
            return Long.parseLong(strValue);
        } else if (targetType == Double.class || targetType == double.class) {
            return Double.parseDouble(strValue);
        } else if (targetType == Float.class || targetType == float.class) {
            return Float.parseFloat(strValue);
        } else if (targetType == Boolean.class || targetType == boolean.class) {
            return Boolean.parseBoolean(strValue);
        } else if (targetType == Date.class) {
            return java.sql.Date.valueOf(strValue);
        } else if (targetType == Timestamp.class) {
            return Timestamp.valueOf(strValue);
        } else if (targetType == String.class) {
            return strValue;
        }

        return value;
    }
    private static void setParameters(PreparedStatement preparedStatement,Object...params) throws SQLException {
        if(params!=null&&params.length>0){
            for(int i=0;i< params.length;i++){
                preparedStatement.setObject(i+1,params[i]);
            }
        }
    }
}
