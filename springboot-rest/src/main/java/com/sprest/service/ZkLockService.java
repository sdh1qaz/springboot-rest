package com.sprest.service;

import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 使用Curator实现分布式锁
 * 
 * @author 苏登辉
 */
@Service
public class ZkLockService {

	// 导入zk客户端
	@Autowired
	CuratorFramework zkClient;
	// 锁的路径
	String lockPath = "/lock";

	/**
	 * 抢锁
	 */
	public String getLock() {
		try {
			// InterProcessMutex实现了分布式锁
			InterProcessMutex lock = new InterProcessMutex(zkClient, lockPath);
			// 使用acquire方法获取锁，50毫秒抢锁时间
			if (lock.acquire(50, TimeUnit.MILLISECONDS)) {
				try {
					// 模拟业务处理用时5秒
					Thread.sleep(1000 * 2);
					return "抢锁成功";
				} finally {
					lock.release();// 释放锁
				}
			}
			return "抢锁失败";
		} catch (Exception ex) {
			// zk异常
			ex.printStackTrace();
			return "抢锁异常";
		}
		
	}
}
