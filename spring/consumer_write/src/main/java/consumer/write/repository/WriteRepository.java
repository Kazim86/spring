package consumer.write.repository;

import consumer.write.config.WriteDatabaseConfig;
import consumer.write.properties.WriteProperties;
import consumer.write.model.DataModel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

import static consumer.write.config.WriteDatabaseConfig.TypeSQL.INSERT;

@Slf4j
public class WriteRepository extends WriteDatabaseConfig {
    static final Logger logger = LoggerFactory.getLogger(WriteDatabaseConfig.class);

    public void insert(DataModel dataModel, WriteProperties properties) {
        try {
            request(INSERT, dataModel.insert(), properties);
        } catch (SQLException e) {
            logger.error("Error " + e);
        }
    }
}
