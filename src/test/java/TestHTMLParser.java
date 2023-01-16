
import java.util.HashMap;

import com.example.demo.utils.LogUtils;

public class TestHTMLParser {
    public static void main(String[] args) {
        HashMap<String, String> logs = LogUtils.getLogs();
        // list logs per type
        for (String type : logs.keySet()) {
            System.out.println(type);
            System.out.println(logs.get(type));
        }
    }
}
