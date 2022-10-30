package rest.r1;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@SpringBootApplication
public class R1Application {
	public static void main(String[] args) {
		//SpringApplication.run(R1Application.class, args);
		WebClient client = WebClient.create();

		String responseSpec = client.get()
				.uri("http://localhost:9081/")
			//	.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(String.class)
				.block();

	}
}
