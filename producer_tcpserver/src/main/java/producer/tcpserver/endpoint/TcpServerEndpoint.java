package producer.tcpserver.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import producer.tcpserver.service.MessageService;

@MessageEndpoint
public class TcpServerEndpoint {
    private MessageService messageService;

    @Autowired
    public TcpServerEndpoint(MessageService messageService) {
        this.messageService = messageService;
    }

    /*
    @Transformer(inputChannel="inboundChannel", outputChannel="toEcho")
    public String convert(byte[] bytes) {
        System.out.println(new String(bytes));
        return new String(bytes);
    }

    @ServiceActivator(inputChannel="toEcho")
    public String upCase(String in) {
        System.out.println(in);
        return in.toUpperCase();
    }
    */
    @ServiceActivator(inputChannel = "inboundChannel")
    public byte[] process(byte[] message) {
        return messageService.processMessage(message);
    }
}
