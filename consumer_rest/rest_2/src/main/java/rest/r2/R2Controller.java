package rest.r2;

import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.Map;

@RestController
@RequestMapping(path = "/", produces = "application/json")
public class R2Controller {

    @GetMapping
    public ResponseEntity<String> indexGET(@RequestHeader Map<String, String> header, @NonNull @RequestBody String body) {
        for (Map.Entry<String, String> entry : header.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        System.out.println(body);
        return new ResponseEntity<String>(body, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> indexPOST(@RequestHeader Map<String, String> header, @RequestBody UserModel body) {
        for (Map.Entry<String, String> entry : header.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        System.out.println(body);
        return new ResponseEntity<String>(body.toString(), HttpStatus.OK);
    }
}
