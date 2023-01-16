import com.example.demo.utils.*;
public class TestUpdated {

    public static void main(String[] args) {
        new Thread(() -> {
            Updater updater = new Updater();
            while (true) {
                try {
                    Thread.sleep(1000);
                    updater.update();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
