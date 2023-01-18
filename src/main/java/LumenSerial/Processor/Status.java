package LumenSerial.Processor;

import LumenSerial.Connector.Serial;
import LumenSerial.Constants.Messages;
import LumenSerial.Model.Response;
import LumenSerial.Parser.Parser;

//Serial Status class
public class Status extends Processor {
    private Parser parser;

    public Status(Serial serial) {
        super(serial);
        this.parser = new Parser();
    }

    public Response read() {
        String message = Messages.GetStatusses();
        return this.parser.parseResponse(this.send(message));
    }
}
