import LumenSerial.LumenBitInterface;
import LumenSerial.Model.Response;

public class TestSerialInterface {
    public static void main(String[] args) {
        LumenBitInterface lumenBit = new LumenBitInterface();

        if (lumenBit.connected) {
            testReadTime(lumenBit);
            testReadUUID(lumenBit);
            testReadStatus(lumenBit);
            testReadActivations(lumenBit);
            testReadActivators(lumenBit);

            testSetTime(lumenBit);
            testSetUUID(lumenBit);
            testSetActivators(lumenBit);
        } else {
            System.out.println("Failed to connect to device");
        }
    }

    public static boolean testReadTime(LumenBitInterface lumenBit) {
        Response response = null;

        System.out.println("\n\nTesting Time\n\n");
        response = lumenBit.time.read();
        System.out.println(response.toString());

        return true;
    }

    public static boolean testReadUUID(LumenBitInterface lumenBit) {
        Response response = null;

        System.out.println("\n\nTest UUID\n\n");
        response = lumenBit.uuid.read();
        System.out.println(response.toString());

        return true;
    }

    public static boolean testReadStatus(LumenBitInterface lumenBit) {
        Response response = null;

        System.out.println("\n\nTest Status\n\n");
        response = lumenBit.status.read();
        System.out.println(response.toString());

        return true;
    }

    public static boolean testReadActivations(LumenBitInterface lumenBit) {
        Response response = null;

        System.out.println("\n\nTest Activations\n\n");
        response = lumenBit.activations.read();
        System.out.println(response.toString());

        return true;
    }

    public static boolean testReadActivators(LumenBitInterface lumenBit) {
        Response response = null;

        System.out.println("\n\nTest Activators\n\n");
        response = lumenBit.activators.read();
        System.out.println(response.toString());

        return true;
    }

    public static boolean testSetTime(LumenBitInterface lumenBit) {
        Response response = null;

        System.out.println("\n\nTesting Time\n\n");
        response = lumenBit.time.set();
        System.out.println(response.toString());

        return true;
    }

    public static boolean testSetUUID(LumenBitInterface lumenBit) {
        Response response = null;

        System.out.println("\n\nTest UUID\n\n");
        response = lumenBit.uuid.set("RED");
        System.out.println(response.toString());

        return true;
    }

    public static boolean testSetActivators(LumenBitInterface lumenBit) {
        Response response = null;

        System.out.println("\n\nTest Activators\n\n");
        response = lumenBit.activators.set(new String[]{"GREEN", "BLACK"});
        System.out.println(response.toString());

        return true;
    }
}
