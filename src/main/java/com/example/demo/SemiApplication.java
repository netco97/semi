package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.demo")
public class SemiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SemiApplication.class, args);
	}
	



}
