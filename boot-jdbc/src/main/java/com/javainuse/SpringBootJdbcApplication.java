package com.javainuse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootJdbcApplication {

	private static final Logger logger = Logger.getLogger(SpringBootJdbcApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJdbcApplication.class, args);

	}
}