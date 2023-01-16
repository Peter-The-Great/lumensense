package LumenSerial.Connector;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortMessageListener;

import java.util.ArrayList;

public class Listener implements SerialPortMessageListener {

    public StringBuilder message = new StringBuilder();
    public ArrayList<String> messages = new ArrayList<>();

    @Override
    public int getListeningEvents() {
        return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        byte[] newData = event.getReceivedData();
        try {
            for (int i = 0; i < newData.length; ++i) {
                if (newData[i] == '\n') {
                    messages.add(message.toString());
                    message = new StringBuilder();
                } else {
                    message.append((char) newData[i]);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public byte[] getMessageDelimiter() {
        return new byte[0];
    }

    @Override
    public boolean delimiterIndicatesEndOfMessage() {
        return false;
    }

    public String[] getMessages() {
        String[] messagesArray = new String[messages.size()];
        messagesArray          = messages.toArray(messagesArray);

        messages.clear();
        return messagesArray;
    }
}
