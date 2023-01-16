package LumenSerial.Model;

public class Response {
    private String status  = null;
    private String message = null;
    private Object data    = null;

    public Response(String status, String message, Object data) {
        this.status  = status;
        this.message = message;
        this.data    = data;
    }

    public String getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public Object getData() {
        return this.data;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String toString() {
        String string = "[Response] => [\n";
        string += "    [status]  => " + this.status + ",\n";
        string += "    [message] => " + this.message + ",\n";
        string += "    [data]    => " + this.data + "\n]";
        return string;
    }
}
