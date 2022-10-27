package consumer.write;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WriteApplication {
	static final Logger logger = LoggerFactory.getLogger(WriteApplication.class);
	public static void main(String[] args) {
		logger.info("Start \"Spring Write in DataBase\"");
		SpringApplication.run(WriteApplication.class, args);
	}
}
