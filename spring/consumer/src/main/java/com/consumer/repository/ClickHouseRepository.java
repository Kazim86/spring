package com.consumer.repository;

import com.consumer.config.ClickHouseConfig;
import com.consumer.model.DataModel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Slf4j
public class ClickHouseRepository extends ClickHouseConfig {
    static final Logger logger = LoggerFactory.getLogger(ClickHouseRepository.class);

    public void insert(DataModel dataModel) throws SQLException {
        try {
            String SQL = "INSERT INTO main.test (id,value,time,date) VALUES (" +
                dataModel.getId() + "," +
                dataModel.getValue() + ",'" +
                dataModel.getTime() + "','" +
                dataModel.getDate() + "');";
            request(type.INSERT, SQL);
        } catch (SQLException e) {
            logger.error("ERROR (insert) SQLException " + e);
        } catch (Exception e) {
            logger.error("ERROR (insert) Exception " + e);
        }
    }
    public ArrayList<DataModel> select(String SQL) throws SQLException {
        try {
            ArrayList<DataModel> dataModelArrayList = new ArrayList<>();
            ResultSet rs = request(type.SELECT,SQL);
            while (rs.next()){
                DataModel dataModel = new DataModel();
                dataModel.setId(rs.getInt("id"));
                dataModel.setValue(rs.getDouble("value"));
                dataModel.setTime(rs.getString("time"));
                dataModel.setDate(rs.getString("date"));
                dataModelArrayList.add(dataModel);
            }
            return dataModelArrayList;
        } catch (SQLException e) {
            logger.error("ERROR (select) SQLException " + e);
            return null;
        } catch (Exception e) {
            logger.error("ERROR (select) Exception " + e);
            return null;
        }
    }
}