package com.sprest.service;

public interface IRedisService {

	/**
	 * 设置普通的Key-Value
	 */
	void setString(String key,String val);
	
	/**
	 * 获取普通的Key-Value
	 */
	String getString(String key);
	
	/**
	 * 设置普通的Key-object
	 */
	void setObject(String key,Object val);
	
	/**
	 * 根据key取得对象
	 */
	<T> T getObject(String key,Class<T> clazz);
	
}
