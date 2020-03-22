package com.sprest.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.StatementInterceptor;
public class DBUtils {
	
	/**
     * 执行查询SQL语句
     */
    public static String executeQuery(DataSource dataSource, String querySql)  throws Exception{
        if (dataSource == null) {
            throw new RuntimeException("DataSource is null!");
        }
        if (querySql == null || querySql.trim().length() == 0) {
            throw new RuntimeException("Query SQL is empty!");
        }
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String res = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(querySql);
            res = resultSetToJson(resultSet);
        }finally {
        	statement.close();
        	connection.close();
        }
        return res;
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
    	} finally {
    		statement.close();
    		connection.close();
    	}
    	return executeUpdate;
    }
    
    /**
     * resultSet转JSON
     */
    public static String resultSetToJson(ResultSet rs) throws SQLException,JSONException
    {
       // json数组
       JSONArray array = new JSONArray();
       // 获取列名信息
       ResultSetMetaData metaData = rs.getMetaData();
       int columnCount = metaData.getColumnCount();
       // 遍历ResultSet中的每条数据
        while (rs.next()) {
            JSONObject jsonObj = new JSONObject();
            // 遍历每一列
            for (int i = 1; i <= columnCount; i++) {
                String columnName =metaData.getColumnLabel(i);
                String value = rs.getString(columnName);
                jsonObj.put(columnName, value);
            } 
            array.add(jsonObj); 
        }
       return array.toString();
    }
    
    /**
     * 判断sql的类型
     * 是查询返回true，不是返回false
     */
    public static boolean isSelectSql(String sql) {
    	String lowerCase = sql.toLowerCase();
    	return lowerCase.contains("select");
    }
    
    /**
     * 根据sql判断是否在操作甘肃的表
     */
    public static boolean isoperateGStable(String sql) {
    	String lowerCase = sql.toLowerCase();
    	return lowerCase.contains("t_tps_moulde");
    }
}
