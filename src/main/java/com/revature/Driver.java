package com.revature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Driver {
	private static final Logger appLog = LoggerFactory.getLogger(Driver.class);
	
	public static void main(String[] args) {
		SpringApplication.run(Driver.class, args);
	}
}
