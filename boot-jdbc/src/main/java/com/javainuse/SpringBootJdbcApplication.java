package com.javainuse;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/** Spring Boot Application
 * @author Kanav Singla
 *
 */
@SpringBootApplication
public class SpringBootJdbcApplication {

	private static final Logger logger = Logger.getLogger(SpringBootJdbcApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJdbcApplication.class, args);
		logger.info("Spring Boot Application Started");

	}
}