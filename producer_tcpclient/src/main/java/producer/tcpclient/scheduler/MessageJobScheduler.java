package producer.tcpclient.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import producer.tcpclient.service.MessageService;

@Component
public class MessageJobScheduler {

    private MessageService messageService;

    @Autowired
    public MessageJobScheduler(MessageService messageService) {
        this.messageService = messageService;
    }

    @Scheduled(fixedDelay = 2000)
    public void sendMessageJob() {
        messageService.sendMessage();
    }
}
