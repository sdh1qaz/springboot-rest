package com.sprest.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
public class DBUtils {
	
	/**
     * 执行查询SQL语句
     */
    public static ResultSet executeQuery(DataSource dataSource, String querySql)  throws Exception{
        if (dataSource == null) {
            throw new RuntimeException("DataSource is null!");
        }
        if (querySql == null || querySql.trim().length() == 0) {
            throw new RuntimeException("Query SQL is empty!");
        }
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(querySql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	statement.close();
        	connection.close();
        }
        return resultSet;
    }
    
    /**
     * 执行增、删、改SQL语句
     */
    public static int executeUpdate(DataSource dataSource, String sql)  throws Exception{
    	if (dataSource == null) {
    		throw new RuntimeException("DataSource is null!");
    	}
    	if (sql == null || sql.trim().length() == 0) {
    		throw new RuntimeException("Query SQL is empty!");
    	}
    	Connection connection = null;
    	Statement statement = null;
    	int executeUpdate = 0;
    	try {
    		connection = dataSource.getConnection();
    		statement = connection.createStatement();
    		executeUpdate = statement.executeUpdate(sql);
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		statement.close();
    		connection.close();
    	}
    	return executeUpdate;
    }
}
