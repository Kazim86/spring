package producer.tcpserver.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import producer.tcpserver.model.DataModel;


@Service
@Controller
public class MessageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    private KafkaTemplate<String, DataModel> kafkaTemplate;

    @RequestMapping(value = "/", method = {RequestMethod.GET})
    public String home(){return "index";}

    public byte[] processMessage(byte[] message) {
        LOGGER.info("Receive message: {}" , new String(message));
        DataModel dataModel = new DataModel(99, 99.99);
        kafkaTemplate.send("write", "1", dataModel);
        return "Ok!".getBytes();
    }
}
