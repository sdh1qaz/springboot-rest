package com.sprest;

import javax.sql.DataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import com.alibaba.druid.pool.DruidDataSource;

@SpringBootApplication
public class LanuchApplication {
	
	/**
	 * 创建默认数据源
	 * 数据库信息由配置文件制定
	 */
	@Bean(name="defaultDataSource1",initMethod="init", destroyMethod="close")
	@ConfigurationProperties(prefix="default1.datasource")
	public DataSource getDefaultDataSource1() {
		return new DruidDataSource();
	}
	
	/**
	 * 创建默认数据源
	 * 数据库信息由配置文件制定
	 */
	@Bean(name="defaultDataSource2",initMethod="init", destroyMethod="close")
	@ConfigurationProperties(prefix="default2.datasource")
	public DataSource getDefaultDataSource2() {
		return new DruidDataSource();
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(LanuchApplication.class, args);
	}
}
