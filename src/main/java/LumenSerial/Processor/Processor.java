package LumenSerial.Processor;

import LumenSerial.Connector.Serial;
import LumenSerial.Model.Response;

import java.util.Queue;

//Serial Processor class that awaits any response from the microbit.
public class Processor {
    private Serial serial;
    private boolean waitingForResponse = false;

    public Processor(Serial serial) {
        this.serial = serial;
    }

    public synchronized Response send(String message) {
        while (waitingForResponse) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.waitingForResponse = true;

        this.serial.sendMessage(message);

        return this.receive();
    }

    private synchronized Response receive() {
        while (!waitingForResponse) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.waitingForResponse = false;

        return this.readResponse();
    }

    private synchronized Response readResponse() {
        boolean acknowledged = false;
        String  message = "";

        for (int i = 0; i < 10; i++) {
            Queue<String> buffer = this.serial.readBuffer();
            while (!buffer.isEmpty()) {
                message = buffer.remove();
                if (acknowledged) {
                    return new Response("200", "OK", message);
                }
                if (message.contains("ACK")) {
                    acknowledged = true;
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return new Response("500", "ERROR", "No response from device");
    }

    //Make sure that all strings don't get Escape stringed, so that all the information gets through.
    public static String unEscapeString(String s){
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<s.length(); i++)
            switch (s.charAt(i)){
                case '\n': sb.append("\\n"); break;
                case '\t': sb.append("\\t"); break;
                // ... rest of escape characters
                default: sb.append(s.charAt(i));
            }
        return sb.toString();
    }
}
