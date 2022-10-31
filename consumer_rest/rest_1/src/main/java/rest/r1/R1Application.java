package rest.r1;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import etcauth.EtcAuth;


@SpringBootApplication
public class R1Application {
	public static void main(String[] args) throws {
		//SpringApplication.run(R1Application.class, args);

		//&user=shakhmat&pswd=vv
		WebClient webClient = WebClient.create("http://elvis.sib.rual.ru:9988/getUserGraphs?room=1");
		String out = webClient
				.post()
				.uri("")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.retrieve()
				.bodyToMono(String.class)
				.block();
		System.out.println(out);
	}
}
