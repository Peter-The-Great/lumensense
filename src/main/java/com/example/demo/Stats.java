package com.example.demo;

import javafx.beans.property.SimpleStringProperty;

public class Stats {
    public SimpleStringProperty id, dActivations, iActivations, uptime;

    public Stats(String id, String dActivations, String iActivations, String uptime) {
        this.id = new SimpleStringProperty(id);
        this.dActivations = new  SimpleStringProperty (dActivations);
        this.iActivations = new  SimpleStringProperty (iActivations);
        //this.triggered = new  SimpleStringProperty (triggered);
        this.uptime =  new  SimpleStringProperty (uptime);
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

//   public String getTriggered() {
//        return triggered.get();
//    }
//
//    public SimpleStringProperty triggeredProperty() {
//        return triggered;
//    }
//
//    public void setTriggered(String triggered) {
//        this.triggered.set(triggered);
//    }

    public String getUptime() {
        return uptime.get();
    }

    public SimpleStringProperty uptimeProperty() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime.set(uptime);
    }

}
