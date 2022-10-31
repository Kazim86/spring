package consumer.analysis.service;

import consumer.analysis.model.DataModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

@Slf4j
@Service
public class AnalysisService {
    static final Logger logger = LoggerFactory.getLogger(AnalysisService.class);

    @Autowired
    private KafkaTemplate<String, DataModel> kafkaTemplate;

    @KafkaListener(topics="write")
    public void listener (ConsumerRecord<String, DataModel> record) {
        DataModel dataModel = record.value();
        dataModel.setValue(dataModel.getValue()+10);
        kafkaTemplate.send("write_new", dataModel);
        System.out.println(record.value());
    }
}
