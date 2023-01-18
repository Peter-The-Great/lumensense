package LumenSerial.Processor;

import LumenSerial.Connector.Serial;
import LumenSerial.Model.Response;

//Serial Log class
public class Log {
    private Serial serial;

    public void Log(Serial serial) {
        this.serial = serial;
    }

    public Response readById(String id) {
        serial.sendMessage("readById " + id);
        return null;
    }

    public Response readAll() {
        serial.sendMessage("readAll");
        return null;
    }

    public Response setById(String id, String value) {
        serial.sendMessage("setById " + id + " " + value);
        return null;
    }

    public Response setAll(String value) {
        serial.sendMessage("setAll " + value);
        return null;
    }
}
