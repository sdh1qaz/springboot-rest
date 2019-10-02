package com.sprest.conf;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorEventType;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZkConfig {
	
	@Value("${zk.cluster.addr}")
	private String zkUrl;

	// 返回一个ZK客户端对象，并注册为spring的bean
	@Bean
	public CuratorFramework getCuratorFramework() throws Exception {
		// CuratorFramework是一个线程安全的类，可以用它完成所有的zk功能
		// RetryPolicy用于重连策略，当某种原因导致zk不可用的时候进行重连尝试的策略
		// ExponentialBackoffRetry是一种重连策略，每次重连的间隔会越来越长，1000毛秒是初始化的间隔时间，3代表重连次数
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

		// CuratorFramework通过zkUrl和retryPolicy构造
		// zkUrl是ZooKeeper的连接地址，在application.properties中
		CuratorFramework client = CuratorFrameworkFactory.newClient(zkUrl, retryPolicy);
		client.getCuratorListenable().addListener(new CuratorListener() {
			public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {
				CuratorEventType type = event.getType();
				if (type == CuratorEventType.WATCHED) {
					WatchedEvent we = event.getWatchedEvent();
					EventType et = we.getType();
					if (we.getPath() != null) {
						client.checkExists().watched().forPath(we.getPath());
					}
				}
			}
		});
		// 调用start开始链接ZooKeeper
		client.start();
		return client;

	}
}