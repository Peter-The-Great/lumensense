package com.example.demo.model;

import java.sql.Date;

//This is the stats model where all necessary data is defined. You can get or set specific data. Including diffrent types of activation.
public class Stats {

    int ta;
    String id;

    Date date;



    //Initialize the stats
    public Stats(String id,int ta, Date date) {
        this.id = id;

        this.ta = ta;


        this.date = date;
    }

    public String  getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTa() {
        return ta;
    }

    public void setTa(int ta) {
        this.ta = ta;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = (Date) date;
    }
}
