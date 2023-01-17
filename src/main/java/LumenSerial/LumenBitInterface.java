package LumenSerial;

import LumenSerial.Connector.Serial;
import LumenSerial.Processor.*;

//The whole lumensense interface
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

    //Check if the microbit is connected.
    public boolean isConnected() {
        return this.serial != null && this.serial.isConnected();
    }

    //Disconnect from the port.
    public void disconnect() {
        this.serial.port.closePort();
        this.connected = false;
    }

    //Connect with the microbit via serial.
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

    //Setting up the processors so when data comes in that there is data like current time
    // and uuid that can be generated when new data arrives.
    public void setProcessors(Serial serial) {
        this.time = new Time(serial);
        this.uuid = new UUID(serial);
        this.status = new Status(serial);
        this.activations = new Activations(serial);
        this.activators = new Activators(serial);
    }
}
