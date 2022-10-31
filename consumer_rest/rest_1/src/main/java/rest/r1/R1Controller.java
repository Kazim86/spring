package rest.r1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Collections;

@RestController
@RequestMapping(path = "/", produces = "application/json")
public class R1Controller {
    @Autowired
    private final WebClient webClient;

    public R1Controller(WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping
    public String index(@RequestParam(name = "id") String id, @RequestParam(name = "value") String value){
        WebClient client = WebClient.builder()
                .baseUrl("http://localhost:9081")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
                .build();
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client
                .method(HttpMethod.POST);
        WebClient.RequestBodySpec bodySpec = uriSpec
                .uri("/");
        WebClient.RequestHeadersSpec<?> headersSpec = bodySpec
                .body(BodyInserters.fromValue("{\"id\":"+id+",\"value\":"+value+"}"));
        WebClient.ResponseSpec responseSpec = headersSpec
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .ifNoneMatch("*")
                .ifModifiedSince(ZonedDateTime.now())
                .retrieve();
        Mono<String> response = responseSpec
                .bodyToMono(String.class);
        return response.block();
    }
}
