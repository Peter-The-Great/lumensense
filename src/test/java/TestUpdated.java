import com.example.demo.utils.*;
public class TestUpdated {

    public static void main(String[] args) {
        Updater updater = new Updater();
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    updater.updateFast(true);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(10000);
                    updater.updateSlow(true);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
