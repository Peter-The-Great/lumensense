package com.example.demo;

import javafx.beans.property.SimpleStringProperty;

public class Stats {
    public SimpleStringProperty id, dActivations, iActivations, uptime, date;

    public Stats(String id, String dActivations, String iActivations,String uptime, String date) {
        this.id = new SimpleStringProperty(id);
        this.dActivations = new  SimpleStringProperty (dActivations);
        this.iActivations = new  SimpleStringProperty (iActivations);
        this.uptime =  new  SimpleStringProperty (uptime);
        this.date = new  SimpleStringProperty (date);
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setID(String id) {
        this.id.set(id);
    }

    public String getdActivations() {        return dActivations.get();
    }

    public SimpleStringProperty dActivationsProperty() {
        return dActivations;
    }

    public void setdActivations(String dActivations) {
        this.dActivations.set(dActivations);
    }

    public String getiActivations() {
        return iActivations.get();
    }

    public SimpleStringProperty iActivationsProperty() {
        return iActivations;
    }

    public void setiActivations(String iActivations) {
        this.iActivations.set(iActivations);
    }

    public String getUptime() {
        return uptime.get();
    }

    public SimpleStringProperty uptimeProperty() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime.set(uptime);
    }
    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

}
