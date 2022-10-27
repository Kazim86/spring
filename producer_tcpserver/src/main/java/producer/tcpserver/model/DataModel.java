package producer.tcpserver.model;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class DataModel {
    private int id;
    private double value;
    private String time = new SimpleDateFormat("HH:mm:ss:SS").format(new Date());
    private String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

    public DataModel(int id, double value){
        this.id = id;
        this.value = value;
    }

}
