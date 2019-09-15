package com.sprest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisServiceImpl implements IRedisService{

	@Autowired
	private StringRedisTemplate redisClient;
	
	/**
	 * 设置普通的Key-Value
	 */
	public void setString(String key,String val) {
		redisClient.opsForValue().set(key, val);
	}
	
	/**
	 * 获取普通的Key-Value
	 */
	public String getString(String key) {
		return redisClient.opsForValue().get("testenv");
	}
	
	
}
