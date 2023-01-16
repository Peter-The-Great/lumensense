import com.example.demo.utils.ConnectionDB;
public class TestDatabase {
    public static void main(String[] args) {
        testupdateLampStatus();
    }
    public static void testupdateLampStatus() {
        ConnectionDB db = new ConnectionDB();
        boolean result = db.updateLampStatus("check", "check");
        System.out.println(result);
    }
}
