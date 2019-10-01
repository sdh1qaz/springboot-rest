package com.sprest.service;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * @Author: 苏登辉
 * @Description: 定时任务
 */
@Component
@Configurable
@EnableScheduling
public class SchedulService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SchedulService.class);
	
	/**
	 * 每1分钟做的任务
	 */
	@Scheduled(cron = "0 0/1 * * * ? ")
	public void updateQueue() {
		LOGGER.info("每1分钟做的任务开始...");
		//做任务
		LOGGER.info("每1分钟做的任务结束...");
	}
	
	/**
	 * 每天22:00做的任务
	 * @throws IOException 
	 */
	@Scheduled(cron = "0 0 22 * * ?")
	public void backupDBdaily() throws IOException {
		
		LOGGER.info("每天22:00做的任务开始...");
		//做任务
		LOGGER.info("每天22:00做的任务结束...");
	}
	
	/**
	 * 每天09:20做的任务
	 * @throws IOException 
	 */
	@Scheduled(cron = "0 20 9 * * ?")
	public void sendItemsByQQMail() throws IOException {
		
		LOGGER.info("每天09:20做的任务开始...");
		//做任务
		LOGGER.info("每天09:20做的任务结束...");
	}
	
}
