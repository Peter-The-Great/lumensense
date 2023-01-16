package LumenSerial.Processor;

import LumenSerial.Connector.Serial;
import LumenSerial.Constants.Messages;
import LumenSerial.Model.Response;
import LumenSerial.Parser.Parser;

public class UUID extends Processor {
    private Parser parser;

    public UUID(Serial serial) {
        super(serial);
        this.parser = new Parser();
    }

    public Response read() {
        String message = Messages.GetUUID();
        return this.parser.parseResponse(this.send(message));
    }

    public Response set(String uuid) {
        String message = Messages.SetUUID(uuid);
        return this.parser.parseResponse(this.send(message));
    }
}
