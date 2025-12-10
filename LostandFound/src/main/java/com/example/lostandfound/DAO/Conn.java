package com.example.lostandfound.DAO;

import java.sql.*;
import java.util.Arrays;

public class Conn {
    final static String driver = "com.sql.jdbc.Driver";
    final static String url = "jdbc:mysql://";
    final static String username = "root";
    final static String password = "Aa@951413";

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }
    public static void close(Connection connection,Statement statement){
        closeAll(connection,statement);
    }
    public static void close(Connection connection, Statement statement, ResultSet resultSet){
        closeAll(connection,statement,resultSet);
    }
    private static void closeAll(AutoCloseable ... resources){
        if(resources!=null&&resources.length>0){
            Arrays.stream(resources).forEach(source->{
                if(source!=null){
                    try {
                        source.close();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }
}
