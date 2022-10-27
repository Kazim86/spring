package com.consumer.controller;

import com.consumer.model.DataModel;
import com.consumer.repository.ClickHouseRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.ArrayList;

@Slf4j
@Controller
public class ConsumerController {
    static final Logger logger = LoggerFactory.getLogger(ConsumerController.class);

    @RequestMapping(value = "/", method = {RequestMethod.GET})
    public String home(Model model, @RequestParam(value = "select", required = false) String select) throws SQLException {
        try {
            if (select != null && select.length() > 1) {
                ClickHouseRepository repository = new ClickHouseRepository();
                ArrayList<DataModel> dataModels = repository.select(select);
                model.addAttribute("select", select);
                model.addAttribute("dataModels", dataModels);
            }
        } catch (Exception e) {
            logger.error("ERROR " + e);
        }
        return "index";
    }
}
