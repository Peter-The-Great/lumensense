package com.example.demo.utils;

import LumenSerial.LumenBitInterface;
import LumenSerial.Model.Response;

import java.sql.Connection;
import java.util.HashMap;

//A database updater tha updates either fast or slow depending on if there are any errors detected during communication.
public class Updater {
    private ConnectionDB db;
    private Connection conn;
    public LumenBitInterface lumenBit;
    private LogUtils logUtils;
    private int errors = 0;

    //Initialize
    public Updater() {
        this.db       = new ConnectionDB();
        this.conn     = this.db.conn;
        this.lumenBit = new LumenBitInterface();
        this.logUtils = new LogUtils();
    }

    //Choose the fast update if there are more than 4 errors.
    public void updateFast() {
        if (this.errors > 4) {
            this.lumenBit.disconnect();
            this.errors = 0;
        }
        if (this.lumenBit.isConnected()) {
            this.errors += this.updateStatus();
            this.errors += this.updateActivations();
        } else {
            System.out.println("No connection to device");
            this.lumenBit.connect();
        }
    }

    //Choose the fast update if there are few errors.
    public void updateSlow() {
        if (this.lumenBit.isConnected()) {
            this.updateTime();
            this.updateLogs();
        } else {
            System.out.println("No connection to device");
            this.lumenBit.connect();
        }
    }

    //Updates the status given from the microbit to the database
    public int updateStatus() {
        Response response = this.lumenBit.status.read();

        if (response.getStatus().equals("200")) {
            String lamps = (String) response.getData();
            System.out.println("Update status micro response: " + lamps + " - " + response.getMessage());
            for (String lamp : lamps.split(",")) {
                String[] statusSplit = lamp.split("=");
                String lampId = statusSplit[0];
                String status = statusSplit[1].equals("1") ? "ON" : "OFF";
                boolean result = this.db.updateLampStatus(status, lampId);
                System.out.println("Update status: " + lampId + " = " + status);
            }
            return 0;
        } else {
            System.out.println("Update status failed, incorrect response: " + response.getMessage() + ", data: " + response.getData());
            return 1;
        }
    }

    //Updates the activations given from the microbit to the database
    public int updateActivations() {
        Response response = this.lumenBit.activations.read();

        if (response.getStatus().equals("200")) {
            String lampsActivations = (String) response.getData();
            for (String lampActivation : lampsActivations.split(",")) {
                String[] lampActivationSplit = lampActivation.split("=");
                String   lamp_id     = lampActivationSplit[0];
                String   activations = lampActivationSplit[1];

                System.out.println("Update activations: " + lamp_id + " = " + activations);

                boolean result = this.db.updateActivations(lamp_id, activations);
            }
            return 0;
        } else {
            System.out.println("Update activations failed, incorrect response: " + response.getMessage() + ", data: " + response.getData());
            return 1;
        }
    }

    //Updates the logs given from the microbit to the database
    public void updateLogs() {
        try {
            HashMap<String, String> logs = this.logUtils.getLogs();
            for (String type : logs.keySet()) {
                String content = logs.get(type);
                boolean result = this.db.updateLog(content, type);
                System.out.printf("Update log (%s): %s\n", type, (result ? "success" : "failed") );
            }
        } catch (RuntimeException e) {
            System.out.println("Update logs failed: " + e.getMessage() );
        }
    }

    //Updates the time to the current time the microbit is giving.
    public void updateTime() {
        this.lumenBit.time.set();
    }

    public String getUUID() {
        Response response = this.lumenBit.uuid.read();
        if (response.getStatus().equals("200")) {
            return (String) response.getData();
        }

        return null;
    }
}
