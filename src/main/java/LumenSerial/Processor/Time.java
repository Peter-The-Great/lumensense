package LumenSerial.Processor;

import LumenSerial.Connector.Serial;
import LumenSerial.Constants.Messages;
import LumenSerial.Model.Response;
import LumenSerial.Parser.TimeParser;

//Serial Time class
public class Time extends Processor {
    private TimeParser parser;
    public Time(Serial serial) {
        super(serial);
        this.parser = new TimeParser();
    }

    public Response read() {
        Response response = this.send(Messages.GetTime());
        return this.parser.parse_time(response);
    }

    public Response set() {
        Response response = this.send(Messages.SetTime());
        return this.parser.parse_time(response);
    }
}
