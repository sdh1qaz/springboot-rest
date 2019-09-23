package com.sprest.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import com.sprest.util.JsonUtil;

@Component
public class RedisServiceImpl implements IRedisService{

	@Autowired
	private StringRedisTemplate redisClient;
	
	//日志记录器
	private static final Logger logger = Logger.getLogger(RedisServiceImpl.class);
	
	/**
	 * 设置普通的Key-Value
	 */
	public void setString(String key,String val) {
		logger.info("写入redis，key=" + key + ",value=" + val);
		redisClient.opsForValue().set(key, val);
	}
	
	/**
	 * 获取普通的Key-Value
	 */
	public String getString(String key) {
		String val = redisClient.opsForValue().get(key);
		logger.info("读取redis，key=" + key + ",value=" + val);
		return val;
	}
	
	/**
	 * 设置普通的Key-object
	 */
	public void setObject(String key,Object val) {
		setString(key, JsonUtil.bean2JsonStr(val));
	}
	
	/**
	 * 根据key取得对象
	 */
	public <T> T getObject(String key,Class<T> clazz) {
		T result;
		String val = getString(key);
		result = JsonUtil.jsonStr2Bean(val, clazz);
		return result;
	}
	
	
}
