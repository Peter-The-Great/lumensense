import at.favre.lib.crypto.bcrypt.BCrypt;
import com.example.demo.utils.ConnectionDB;

import java.util.Scanner;

public class TestAddUser {
    public static void main(String[] args) {
        testAddUser();
    }

    public static void testAddUser() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the name of the user");
        String username = sc.nextLine();
        System.out.println("Enter the password of the user");
        String password = sc.nextLine();



        // hash password with bcrypt
        String hashedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        System.out.println("username: " + username);
        System.out.println("password: " + password);
        System.out.println(hashedPassword);
        ConnectionDB db = new ConnectionDB();
        boolean added = db.addUser(username, hashedPassword);
        if (added) {
            System.out.println("User added successfully");
        } else {
            System.out.println("User could not be added");
        }
    }
}
