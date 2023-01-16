package LumenSerial.Model;

public class Log {
    private String type = null;
    private LogEntry[] entries = null;

    public String getType() {
        return this.type;
    }

    public LogEntry[] getEntries() {
        return this.entries;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEntries(LogEntry[] entries) {
        this.entries = entries;
    }
}
