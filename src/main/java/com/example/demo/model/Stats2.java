package com.example.demo.model;

import java.sql.Date;

//This is the stats model where all necessary data is defined. You can get or set specific data. Including diffrent types of activation.
public class Stats2 {

    int  totalA;
    String id1;





    //Initialize the stats
    public Stats2(String id1,int totalA) {
        this.id1 = id1;

        this.totalA = totalA;



    }

    public String  getId1() {
        return id1;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public int gettotalA() {
        return totalA;
    }

    public void settotalA(int totalA) {
        this.totalA = totalA;
    }

}
