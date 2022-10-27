package producer.tcpclient.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import producer.tcpclient.gateway.TcpClientGateway;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class MessageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);

    private TcpClientGateway tcpClientGateway;

    @Autowired
    public MessageService(TcpClientGateway tcpClientGateway) {
        this.tcpClientGateway = tcpClientGateway;
    }

    public void sendMessage() {
        String send = new SimpleDateFormat("HH:mm:ss:SS").format(new Date());
        byte[] resp = tcpClientGateway.send(send.getBytes());
        LOGGER.info("Send message: " + send + " Receive response: " + new String(resp));
    }
}
