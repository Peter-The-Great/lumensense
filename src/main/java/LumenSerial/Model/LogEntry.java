package LumenSerial.Model;

import java.util.Date;

public class LogEntry  {
    private Date time    = null;
    private String message = null;
    private String data    = null;

    public Date getTime() {
        return this.time;
    }

    public String getMessage() {
        return this.message;
    }

    public String getData() {
        return this.data;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(String data) {
        this.data = data;
    }
}
