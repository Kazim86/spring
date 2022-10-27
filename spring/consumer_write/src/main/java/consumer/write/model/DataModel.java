package consumer.write.model;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class DataModel {
    private int id;
    private double value;
    private String time = new SimpleDateFormat("HH:mm:ss:SS").format(new Date());
    private String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

    public String insert() {
        return "INSERT INTO main.test (id,value,time,date) VALUES (" + id + "," + value + ",'" + time + "','" + date + "') ";
    }
    public String select() {
        return "SELECT id,value,time,date FROM main.test ";
    }
    public String toJson() {
        return "{\"id\":"+id+",\"value\":"+value+",\"time\":\""+time+"\",\"date\":\""+date+"\"}";
    }
}

