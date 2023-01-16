package LumenSerial.Model;

public class Message {
    private String method;
    private String action;
    private String payload;

    public Message(String method, String action, String payload) {
        this.method = method;
        this.action = action;
        this.payload = payload;
    }

    public String getMethod() {
        return method;
    }

    public String getAction() {
        return action;
    }

    public String getPayload() {
        return payload;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "Message{" + "method=" + method + ", action=" + action + ", payload=" + payload + '}';
    }
}
