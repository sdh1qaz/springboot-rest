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
	 * 创建数据源
	 */
	@Bean(initMethod = "init", destroyMethod = "close")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		return new DruidDataSource();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(LanuchApplication.class, args);
	}
}
