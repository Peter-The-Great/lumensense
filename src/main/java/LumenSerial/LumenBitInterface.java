package LumenSerial;

import LumenSerial.Connector.Serial;
import LumenSerial.Processor.*;


public class LumenBitInterface {
    public Time time;
    public UUID uuid;
    public Status status;
    public Activations activations;
    public Activators activators;
    public boolean connected = false;

    public LumenBitInterface() {
        this.connect();
    }

    public boolean connect() {
        if (this.connected) {
            return true;
        }
        Serial serial = new Serial();

        if (serial.connect()) {
            System.out.println("Connected to device");
            this.setProcessors(serial);
            this.connected = true;
            return true;
        } else {
            System.out.println("Failed to connect to device");
            return false;
        }
    }

    public void setProcessors(Serial serial) {
        this.time = new Time(serial);
        this.uuid = new UUID(serial);
        this.status = new Status(serial);
        this.activations = new Activations(serial);
        this.activators = new Activators(serial);
    }
}
