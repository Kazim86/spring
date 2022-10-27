package consumer.analysis.service;

import consumer.analysis.model.DataModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

@Slf4j
@Service
public class AnalysisService {
    static final Logger logger = LoggerFactory.getLogger(AnalysisService.class);
    private ArrayList<DataModel> writeModelArrayList = new ArrayList<>();

    @KafkaListener(topics="write")
    public void listener (ConsumerRecord<String, DataModel> record) {
        System.out.println(record.value());
        /*
        writeModelArrayList.add(record.value());
        Iterator<DataModel> iterator = writeModelArrayList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }
         */
    }
}
