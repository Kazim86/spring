package rest.r1.controller;

import rest.r1.auth.EtcAuth;
import rest.r1.auth.TokenUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
public class R1Controller {

    @Autowired
    private final WebClient webClient;

    public R1Controller(WebClient webClient) {
        this.webClient = webClient;
    }

    @EtcAuth(roles = {"ADMINS","USERS"})
    @RequestMapping(method = RequestMethod.GET, path = "/get", produces = "application/json")
    public String get(TokenUser user) {
        System.out.println(user.toString());
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
}
