package consumer.write.controller;

import consumer.write.properties.WriteProperties;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
public class HomeController {
    static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private WriteProperties properties;

    @RequestMapping(value = "/", method = {RequestMethod.GET})
    public String index() {
        return "index";
    }
}
