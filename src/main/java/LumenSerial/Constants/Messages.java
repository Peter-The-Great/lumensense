package LumenSerial.Constants;

import java.text.SimpleDateFormat;

public class Messages {
    public static String GetTime() {
        return Get( "TIME");
    }

    public static String SetTime() {
        String timestamp = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss").format(new java.util.Date());
        return Post("TIME", timestamp);
    }

    public static String GetUUID() {
        return Get("UUID");
    }

    public static String SetUUID(String uuid) {
        return Post("UUID", uuid);
    }

    public static String GetActivations() {
        return Get("ACTIVATIONS");
    }

    public static String GetActivators() {
        return Get("ACTIVATORS");
    }

    public static String SetActivators(String payload) {
        return Post("ACTIVATORS", payload);
    }

    public static String GetStatusses() {
        return Get("STATUSSES");
    }

    public static String GetTestConnection() {
        return Get("TestConnection");
    }

    public static String Post(String action, String payload) {
        return Message("POST", action, payload);
    }

    public static String Get(String action) {
        return Message("GET",  action);
    }

    public static String Message(String method, String action) {
        return method + ";" + action + ";\n";
    }

    public static String Message(String method, String action, String payload) {
        return  method + ";" + action + ";" + payload + ";\n";
    }
}
