package com.example.demo;

import java.sql.Date;

public class Stats {

    int da,ia, ta;
    String id;

    Date date;



    public Stats(String id, int da, int ia,int ta, Date date) {
        this.id = id;
        this.da = da;
        this.ia = ia;
        this.ta = ta;


        this.date = date;
    }

    public String  getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDa() {
        return da;
    }

    public void setDa(int da) {
        this.da = da;
    }

    public int getIa() {
        return ia;
    }

    public void setIa(int ia) {
        this.ia = (int) ia;
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
