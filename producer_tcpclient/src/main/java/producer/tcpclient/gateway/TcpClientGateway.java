package producer.tcpclient.gateway;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.stereotype.Component;

@Component
@MessagingGateway(defaultRequestChannel = "toTcp")
public interface TcpClientGateway {
    byte[] send(byte[] message);
}
