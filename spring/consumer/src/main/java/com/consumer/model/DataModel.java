package com.consumer.model;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
public class DataModel {
    private int id;
    private double value;
    private String time = new SimpleDateFormat("HH:mm:ss:SS").format(new Date());
    private String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

    public String toJson() {
        return "{\"id\":"+id+",\"value\":"+value+",\"time\":\""+time+"\",\"date\":\""+date+"\"}";
    }
}

