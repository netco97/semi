package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@ComponentScan(basePackages = "com.example.demo")
public class SemiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SemiApplication.class, args);
	}
	
	
//	@Bean
//	public ServletWebServerFactory serverFactort() {
//		TomcatServletWebServerFactory tomcatServletWebServerFactory
//		= new TomcatServletWebServerFactory();
//		
//		tomcatServletWebServerFactory.addAdditionalTomcatConnectors(createStandardConnector());
//		
//		return tomcatServletWebServerFactory;
//	}
//	
//
//	private Connector createStandardConnector() {
//		Connector connector = new Connector("org.apache.coyote.http11.http11NioProtocol");
//		connector.setPort(8089);
//		return connector;
//	}



}
