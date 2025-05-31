package hotel.management.system.utils.db_utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;

import hotel.management.system.utils.billing_utils.Transaction;

public class DatabaseManager {
    private static DatabaseManager databaseManager;
    private static Connection connection;
    private static String username;
    private static String password;

    private DatabaseManager() {
    }

    public static DatabaseManager getDatabaseManagerInstance() {
        if (databaseManager == null) {
            databaseManager = new DatabaseManager();
            Path credentials = Paths.get("./src/main/java/hotel/management/system/assets/login-creds.txt");
            try {
                username = Files.readAllLines(credentials).get(0);
                password = Files.readAllLines(credentials).get(1);
                connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/hms", username, password);
                System.out.println("Database is connected successfully!");
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
        return databaseManager;
    }

    public void storeTransaction(Transaction transaction) {
        String query = String.format(
                "INSERT INTO transaction (room_id, room_type, guest_list, total_balance, checked_in, checked_out) VALUES (%d, %d, '%s', %f, '%s', '%s')",
                transaction.getRoomId(), transaction.getRoomType().ordinal(), transaction.getGuestNames(),
                transaction.getTotalPayment(),
                transaction.getCheckedInTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                transaction.getCheckedOutTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        System.out.println(query);
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
