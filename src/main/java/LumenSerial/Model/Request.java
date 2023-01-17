package LumenSerial.Model;

//Request model with all necessary data and functions included
public class Request {
    private String target = null;
    private String method = null;
    private String action = null;
    private String data   = null;

    public String getTarget() {
        return this.target;
    }

    public String getMethod() {
        return this.method;
    }

    public String getAction() {
        return this.action;
    }

    public String getData() {
        return this.data;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String toString() {
        return "Request: " + this.target + " " + this.method + " " + this.action + " " + this.data;
    }
}
