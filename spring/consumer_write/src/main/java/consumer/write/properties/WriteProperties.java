package consumer.write.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "consumer.properties.database")
public class WriteProperties {
    private String ip;
    private String port;
    private String user;
    private String pass;
}


