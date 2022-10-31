package consumer.write.service;

import consumer.write.properties.WriteProperties;
import consumer.write.model.DataModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

@Slf4j
@Service
public class WriteService {
    static final Logger logger = LoggerFactory.getLogger(WriteService.class);
    private ArrayList<DataModel> writeModelArrayList = new ArrayList<>();

    @Autowired
    private WriteProperties properties;

    @KafkaListener(topics="write")
    public void listener_write (ConsumerRecord<String, DataModel> record) throws SQLException {
        System.out.println("WRITE");
        System.out.println(record.value());
        /*
        writeModelArrayList.add(record.value());
        Iterator<DataModel> iterator = writeModelArrayList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }
         */
        //new WriteRepository().insert(record.value(), properties);
    }
    @KafkaListener(topics="write_new")
    public void listener_write_new (ConsumerRecord<String, DataModel> record) throws SQLException {
        System.out.println("WRITE_NEW");
        System.out.println(record.value());
    }
}
