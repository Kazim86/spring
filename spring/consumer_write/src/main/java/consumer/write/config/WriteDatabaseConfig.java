package consumer.write.config;

import consumer.write.properties.WriteProperties;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import ru.yandex.clickhouse.ClickHouseDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

@Slf4j
@Configuration
public class WriteDatabaseConfig {
    static final Logger logger = LoggerFactory.getLogger(WriteDatabaseConfig.class);

    public enum TypeSQL {SELECT,INSERT,UPDATE,DELETE};

    public ResultSet request(@NonNull TypeSQL type, @NonNull String SQL, WriteProperties properties) throws SQLException {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        logger.info("type: " + type + " ,SQL: "+SQL);
        try {
            String URL = "jdbc:clickhouse://" + properties.getIp() + ":" + properties.getPort();
            ClickHouseDataSource dataSource = new ClickHouseDataSource(URL, new Properties());
            con = dataSource.getConnection(properties.getUser(), properties.getPass());
            st = con.createStatement();
            switch (type) {
                case SELECT: rs = st.executeQuery(SQL); break;
                case INSERT:
                case UPDATE:
                case DELETE: st.executeUpdate(SQL); break;
                default: break;
            }
        } catch (SQLException e){
            logger.error("ERROR SQLException " + e);
            rs = null;
        } finally {
            if(con != null) {
                try{
                    con.close();
                } catch (SQLException e) {
                    logger.error("ERROR SQLException " + e);
                }
            }
        }
        return rs;
    }
}
