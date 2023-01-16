package LumenSerial.Processor;

import LumenSerial.Connector.Serial;
import LumenSerial.Constants.Messages;
import LumenSerial.Model.Response;
import LumenSerial.Parser.Parser;


public class Activations extends Processor {
    private Parser parser;

    public Activations(Serial serial) {
        super(serial);
        this.parser = new Parser();
    }

    public Response read() {
        String message = Messages.GetActivations();
        return this.parser.parseResponse(this.send(message));
    }
}
