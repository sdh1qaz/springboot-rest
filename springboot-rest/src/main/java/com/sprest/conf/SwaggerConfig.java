package com.sprest.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Value("${rest.env}")
	private String env;
	
	@Value("${rest.server.addr}") 
	private String serverAddr;
	
	//@Value("${rest.server.port}")
	private String serverPort;
	
	
	@Bean
	public Docket configSpringfoxDocketForAll() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).
				select().apis(RequestHandlerSelectors.basePackage("com.sprest"))
				.paths(PathSelectors.any()).build();
	}
	
	@SuppressWarnings("deprecation")
	private ApiInfo apiInfo() {
		serverPort = System.getProperty("Server.port");
		return new ApiInfo("springboot-rest接口", 
				"部署信息"+serverAddr+":"+serverPort, "1.0.0", "", "苏登辉", "", "");
	}

}
