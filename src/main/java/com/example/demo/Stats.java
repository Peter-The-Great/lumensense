package com.example.demo;

import javafx.beans.property.SimpleStringProperty;

public class Stats {
    public SimpleStringProperty name, id, status, triggered,uptime;

    public Stats(String name, String id, String status, String triggered, String uptime) {
        this.name = new SimpleStringProperty(name);
        this.id = new  SimpleStringProperty (id);
        this.status = new  SimpleStringProperty (status);
        this.triggered = new  SimpleStringProperty (triggered);
        this.uptime =  new  SimpleStringProperty (uptime);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getTriggered() {
        return triggered.get();
    }

    public SimpleStringProperty triggeredProperty() {
        return triggered;
    }

    public void setTriggered(String triggered) {
        this.triggered.set(triggered);
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

}
