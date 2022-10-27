package com.consumer.service;

import com.consumer.model.DataModel;
import com.consumer.repository.ClickHouseRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Slf4j
@Service
public class ConsumerService {
    static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    @Autowired
    SimpMessagingTemplate template;

    @KafkaListener(topics="write")
    public void listener (ConsumerRecord < String, DataModel > record) throws SQLException {
        try {
            DataModel dataModel = record.value();
            //ClickHouseRepository repository = new ClickHouseRepository();
            //repository.insert(dataModel);
            sendWebSocket(dataModel);
        } catch (Exception e) {
            logger.error("ERROR " + e);
        }
    }
    private void sendWebSocket(DataModel dataModel){
        template.convertAndSend("/topic/message", dataModel.toJson());
    }
}
