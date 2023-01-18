package LumenSerial.Parser;

import LumenSerial.Model.Message;
import LumenSerial.Model.Response;

//A parser class that parses message through the application with data send via the microbit.
public class Parser {
    //This will parse a message into an array.
    public Message parseMessage(Response response) {
        if (response.getStatus().equals("200")) {
            String data    = (String) response.getData();
            String[] parts = data.split(";");

            try {
                String method  = parts[0];
                String action  = parts[1];
                String payload = "";
                if (parts.length > 2) {
                    payload = parts[2];
                }
                return new Message(
                        method,
                        action,
                        payload
                );
            } catch (ArrayIndexOutOfBoundsException e) {
                return null;
            }
        }

        return null;
    }

    //This will parse a response back to the application in an array.
    public Response parseResponse(Response response) {
        if (!response.getStatus().equals("200")) {
            return this.error(response.getMessage());
        }
        Message message = this.parseMessage(response);
        if (message == null) {
            return this.error("Response is empty");
        }

        if (message.getPayload().toLowerCase().contains("error")) {
            return this.error(message.getPayload());
        }

        String data = message.getPayload();
        try {
            return this.success("Data parsed successfully", data);
        } catch (NumberFormatException e) {
            return this.error(data);
        }
    }

    //Checks if any of the Requests and Reponses have actually succeeded or not.
    public Response success(Object data) {
        return new Response("200", "OK", data);
    }

    public Response success(String message, Object data) {
        return new Response("200", message, data);
    }

    public Response error(String message) {
        return new Response("500", message, "");
    }

    public Response error(String message, Object data) {
        return new Response("500", message, data);
    }
}
