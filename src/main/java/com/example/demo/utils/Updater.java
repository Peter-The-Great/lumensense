package com.example.demo.utils;

import LumenSerial.LumenBitInterface;
import LumenSerial.Model.Response;

import java.sql.Connection;
import java.util.HashMap;

public class Updater {
    private ConnectionDB db;
    private Connection conn;
    private LumenBitInterface lumenBit;
    private LogUtils logUtils;

    public Updater() {
        this.db       = new ConnectionDB();
        this.conn     = this.db.conn;
        this.lumenBit = new LumenBitInterface();
        this.logUtils = new LogUtils();
    }
    public void update() {
        if (this.lumenBit.connect()) {
            this.updateStatus();
        } else {
            System.out.println("No connection to device");
        }
    }

    public void updateStatus() {
        Response response = this.lumenBit.status.read();

        if (response.getStatus().equals("200")) {
            String lamps = (String) response.getData();
            for (String lamp : lamps.split(",")) {
                String[] statusSplit = lamp.split("=");
                String lampId = statusSplit[0];
                String status = statusSplit[1].equals("1") ? "true" : "false";
                this.db.updateLampStatus(status, lampId);
            }
        }

    }

    public String getUUID() {
        Response response = this.lumenBit.uuid.read();
        if (response.getStatus().equals("200")) {
            return (String) response.getData();
        }

        return null;
    }
}
