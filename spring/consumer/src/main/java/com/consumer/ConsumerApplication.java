package com.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class ConsumerApplication {
	static final Logger logger = LoggerFactory.getLogger(ConsumerApplication.class);
	public static void main(String[] args) throws Exception {
		logger.info("Start String Consumer");
		SpringApplication.run(ConsumerApplication.class, args);
	}
}
