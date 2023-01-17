package com.example.demo.utils;

import LumenSerial.LumenBitInterface;
import LumenSerial.Model.Response;

import java.sql.Connection;
import java.util.HashMap;

public class Updater {
    private ConnectionDB db;
    private Connection conn;
    public LumenBitInterface lumenBit;
    private LogUtils logUtils;
    private int errors = 0;

    public Updater() {
        this.db       = new ConnectionDB();
        this.conn     = this.db.conn;
        this.lumenBit = new LumenBitInterface();
        this.logUtils = new LogUtils();
    }

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

    public void updateSlow() {
        if (this.lumenBit.isConnected()) {
            this.updateTime();
            this.updateLogs();
        } else {
            System.out.println("No connection to device");
            this.lumenBit.connect();
        }
    }

    public int updateStatus() {
        Response response = this.lumenBit.status.read();

        if (response.getStatus().equals("200")) {
            String lamps = (String) response.getData();
            for (String lamp : lamps.split(",")) {
                String[] statusSplit = lamp.split("=");
                String lampId = statusSplit[0];
                String status = statusSplit[1].equals("1") ? "true" : "false";
                boolean result = this.db.updateLampStatus(status, lampId);
                System.out.println("Update status: " + (result ? "success" : "failed") );
            }
            return 0;
        } else {
            System.out.println("Update status failed, incorrect response: " + response.getMessage() + ", data: " + response.getData());
            return 1;
        }
    }

    public int updateActivations() {
        Response response = this.lumenBit.activations.read();

        if (response.getStatus().equals("200")) {
            String lampsActivations = (String) response.getData();
            for (String lampActivation : lampsActivations.split(",")) {
                String[] lampActivationSplit = lampActivation.split("=");
                String   lamp_id = lampActivationSplit[0];

                String[] activationSplit = lampActivationSplit[1].split("\\|");
                String   directs         = activationSplit[0];
                String   indirects       = activationSplit[1];

                boolean result = this.db.updateActivations(lamp_id, directs, indirects);
                System.out.println("Update activation: " + (result ? "success" : "failed"));
            }
            return 0;
        } else {
            System.out.println("Update activations failed, incorrect response: " + response.getMessage() + ", data: " + response.getData());
            return 1;
        }
    }

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
