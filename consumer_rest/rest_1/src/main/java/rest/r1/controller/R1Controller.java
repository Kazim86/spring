package rest.r1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Collections;

@Controller
@RequestMapping(path = "/", produces = "application/json")
public class R1Controller {
    @Autowired
    private final WebClient webClient;

    public R1Controller(WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping(value = "get")
    public String get(Model model, @RequestParam(value = "data_get", required = false) String data_get){
        String out = webClient
                .post()
                .uri(data_get)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        model.addAttribute("data_get", out);
        return "index";
    }

    @PostMapping(value = "post")
    public String post(Model model, @RequestParam(value = "data_post", required = false) String data_post){
        String out = webClient
                .post()
                .uri(data_post)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        model.addAttribute("data_post", out);
        return "index";
    }
}
