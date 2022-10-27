package producer.tcpserver.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import producer.tcpserver.model.DataModel;

@Service
public class MessageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    private KafkaTemplate<String, DataModel> kafkaTemplate;

    public byte[] processMessage(byte[] message) {
        LOGGER.info("Receive message: " + new String(message) + " Send message: " + "ok!");
        DataModel dataModel = new DataModel(1,10.10);
        kafkaTemplate.send("write", dataModel);
        return dataModel.toString().getBytes();
    }
}
