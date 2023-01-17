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
    private Serial serial = null;

    public LumenBitInterface() {
        this.connect();
    }

    public boolean isConnected() {
        return this.serial != null && this.serial.isConnected();
    }

    public void disconnect() {
        this.serial.port.closePort();
        this.connected = false;
    }

    public boolean connect() {
        this.serial = new Serial();

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
