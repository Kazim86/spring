package rest.r1.controller;

import com.rusal.springauth.EtcAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping(path = "/", produces = "application/json")
public class R1Controller {

    @Autowired
    private final WebClient webClient;

    public R1Controller(WebClient webClient) {
        this.webClient = webClient;
    }

    @EtcAuth
    @GetMapping(value = "get")
    public String getPots() {
        /*
        String out = webClient
                .get()
                .uri("http://elvis.sib.rual.ru:9988/getUserGraphs?room=1")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println(out);
        */

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
