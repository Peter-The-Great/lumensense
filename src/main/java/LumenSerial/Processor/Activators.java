package LumenSerial.Processor;

import LumenSerial.Connector.Serial;
import LumenSerial.Constants.Messages;
import LumenSerial.Model.Response;
import LumenSerial.Parser.Parser;

public class Activators extends Processor {
    private Parser parser;
    public Activators(Serial serial) {
        super(serial);
        this.parser = new Parser();
    }

    public Response read() {
        String message = Messages.GetActivators();
        return this.parser.parseResponse(this.send(message));
    }

    public Response set(String[] uuids) {
        StringBuilder payload = new StringBuilder("");
        for (String uuid : uuids) {
            payload.append(uuid).append(",");
        }
        String message = Messages.SetActivators(payload.toString());
        return this.parser.parseResponse(this.send(message));
    }
}
