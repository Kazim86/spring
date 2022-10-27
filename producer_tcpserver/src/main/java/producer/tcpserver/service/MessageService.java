package producer.tcpserver.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);

    public byte[] processMessage(byte[] message) {
        LOGGER.info("Receive message: {}" , new String(message));
        return "Ok!".getBytes();
    }
}
