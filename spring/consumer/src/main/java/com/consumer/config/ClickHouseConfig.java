package com.consumer.config;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import ru.yandex.clickhouse.ClickHouseDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
@Slf4j
@Configuration
@Service
public class ClickHouseConfig {
    static final Logger logger = LoggerFactory.getLogger(ClickHouseConfig.class);

    public enum type {SELECT,INSERT,UPDATE,DELETE}

    private final String URL = "jdbc:clickhouse://localhost:8123";
    //private final String URL = "jdbc:clickhouse://192.168.0.2:8123";
    private final String USERNAME = "admin";
    private final String PASSWORD = "admin";

    private ClickHouseDataSource dataSource = new ClickHouseDataSource(URL, new Properties());

    public ResultSet request(type type, String SQL) throws SQLException {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        logger.info("type: " + type + " ,SQL: "+SQL);
        try {
            con = dataSource.getConnection(USERNAME, PASSWORD);
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
            return null;
        } catch (Exception e) {
            logger.error("ERROR Exception " + e);
            return null;
        } finally {
            if(con != null) {
                con.close();
            }
            return rs;
        }
    }
}
