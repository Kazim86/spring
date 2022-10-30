package rest.r2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class R2Controller {

    @RequestMapping(value = "/", method = {RequestMethod.GET})
    public String indexGET() {
        System.out.println("GET!!!");
        return "{\"id\":\"12345\"}";
    }

    @RequestMapping(value = "/", method = {RequestMethod.POST})
    public String indexPOST() {
        System.out.println("POST!!!");
        return "{\"id\":\"12345\"}";
    }
}
