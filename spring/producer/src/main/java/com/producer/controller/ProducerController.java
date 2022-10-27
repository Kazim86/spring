package com.producer.controller;

import com.producer.model.DataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@Controller
public class ProducerController {

    static final Logger logger = LoggerFactory.getLogger(ProducerController.class);

    @Autowired
    private KafkaTemplate<String, DataModel> kafkaTemplate;

    @RequestMapping(value = "/", method = {RequestMethod.GET})
    public String home(){
        return "index";
    }

    @RequestMapping(value = "/gen", method = RequestMethod.GET)
    public String gen(Model model, @RequestParam(value = "col", required = false, defaultValue = "1") int col) throws IOException {
        ArrayList<DataModel> dataModelArrayList = new ArrayList<>();
        for(int i = 0; i < col; i++){
            DataModel dataModel = new DataModel(i,(Math.round(Math.random() * 100)/10.0));
            dataModelArrayList.add(dataModel);
            kafkaTemplate.send("write", dataModel);
        }
        model.addAttribute("data",dataModelArrayList);
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/kafka/{event}", method = {RequestMethod.POST})
    public ResponseEntity<DataModel> producer(@PathVariable String event, @RequestBody DataModel DataModel){
        kafkaTemplate.send(event, "1", DataModel);
        return new ResponseEntity<DataModel>(DataModel, HttpStatus.OK);
    }
}
