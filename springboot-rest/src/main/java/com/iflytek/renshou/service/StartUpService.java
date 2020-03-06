package com.iflytek.renshou.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

/**
 * 小书包运行前启动项
 * @author 苏登辉
 */
@Service
public class StartUpService implements CommandLineRunner{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StartUpService.class);
	
	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("启动时任务开始...");
		//做任务
		LOGGER.info("启动时任务结束...");
	}
	
}
