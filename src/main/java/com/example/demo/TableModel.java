package com.example.demo;

import java.sql.Date;

public class TableModel {

    String id,da,ia,ut, date;

    public TableModel(String id, String da, String ia, String ut, Date date) {
        this.id = id;
        this.da = da;
        this.ia = ia;
        this.ut = ut;
        this.date = String.valueOf(date);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDa() {
        return da;
    }

    public void setDa(String da) {
        this.da = da;
    }

    public String getIa() {
        return ia;
    }

    public void setIa(String ia) {
        this.ia = ia;
    }

    public String getUt() {
        return ut;
    }

    public void setUt(String ut) {
        this.ut = ut;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
