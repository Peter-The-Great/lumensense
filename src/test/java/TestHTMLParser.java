
import java.util.HashMap;

import com.example.demo.utils.LogUtils;

public class TestHTMLParser {
    public static void main(String[] args) {
        LogUtils logUtils = new LogUtils();
        HashMap<String, String> logs = logUtils.getLogs();
        // list logs per type
        for (String type : logs.keySet()) {
            System.out.println(type);
            System.out.println(logs.get(type));
        }
    }
}
