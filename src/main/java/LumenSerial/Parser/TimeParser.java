package LumenSerial.Parser;

import LumenSerial.Model.Message;
import LumenSerial.Model.Response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//A time parser that gets the time from the microbit.
public class TimeParser extends Parser {
    public Response parse_time(Response response) {
        if (!response.getStatus().equals("200")) {
            return this.error(response.getMessage());
        }
        Message message = this.parseMessage(response);
        if (message == null) {
            return this.error("Response is empty");
        }

        String data = message.getPayload();
        try {
            if (message.getMethod().equals("GET")) {
                Date datetime = this.parseDate(data);
                return this.success("Time parsed successfully", datetime);
            } else if (message.getMethod().equals("POST")) {
                if (data.equals("success")) {
                    return this.success("Time set successfully");
                }
            }
        } catch (NumberFormatException | ParseException e) {
            return this.error(data);
        }

        return this.error("Unknown error");
    }

    private Date parseDate(String data) throws ParseException {

        return (Date) new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss").parse(data);
    }
}
