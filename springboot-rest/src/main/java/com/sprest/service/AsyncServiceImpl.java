package com.sprest.service;

import java.io.IOException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 异步方法接口实现类
 * @author 苏登辉
 */

@Service
public class AsyncServiceImpl implements IAsyncService{
	
	/**
	 * 异步方法，5秒后打开一个记事本
	 * @throws IOException 
	 */
	@Override
	@Async
	public void openCmd() throws Exception {
		Thread.sleep(5000);
		Runtime.getRuntime().exec("notepad.exe");
	}
}
