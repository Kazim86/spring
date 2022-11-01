package rest.r1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;
import rest.r1.auth.EtcAuth;
import rest.r1.auth.TokenUser;

@Controller
public class R1Controller {

    @Autowired
    private final WebClient webClient;

    public R1Controller(WebClient webClient) {
        this.webClient = webClient;
    }

    @EtcAuth(roles = {"ADMINS","USERS"})
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, path = "/get", produces = "application/json")
    public String get(Model model, TokenUser user) {
        String response = webClient
                .get()
                .uri("http://elvis.sib.rual.ru:9988/getUserGraphs?room=1&user=shakhmat&pswd=vv")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return response;
    }

    @EtcAuth(roles = {"ADMINS","USERS"})
    @RequestMapping(method = RequestMethod.POST, path = "/post", produces = "application/json")
    public String post(Model model, TokenUser user) {
        System.out.println(user);
        /*
        String json = "";
        String response = webClient
                .post()
                .uri("")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(BodyInserters.fromValue(json))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println(response);

         */
        return "index";
    }
}
