package com.sprest.service;

import java.io.IOException;

/**
 * 异步方法接口
 * @author 苏登辉
 */
public interface IAsyncService {
	
	/**
	 * 异步方法，5秒后打开一个记事本
	 * @throws IOException 
	 * @throws Exception 
	 */
	void openCmd() throws Exception ;
}
