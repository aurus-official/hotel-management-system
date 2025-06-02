package hotel.management.system;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.swing.JFrame;

import hotel.management.system.ui.CustomFont;
import hotel.management.system.ui.LoadingPanel;
import hotel.management.system.ui.MainPanel;
import hotel.management.system.utils.billing_utils.Transaction;
import hotel.management.system.utils.db_utils.DatabaseManager;
import hotel.management.system.utils.room_utils.RoomManager;
import hotel.management.system.utils.room_utils.RoomType;

/**
 *
 * @author aururus
 */

public class HotelManagementSystem {
    private static JFrame mainFrame;
    private static DatabaseManager databaseManager;
    private static RoomManager roomManager;

    public static void main(String[] args) throws InterruptedException {
        HotelManagementSystem.registerDriver();
        HotelManagementSystem.setupDBConnection();
        HotelManagementSystem.setupRoomManager();

        Transaction transaction = new Transaction();
        transaction.setRoomId(10);
        transaction.setGuestNames("Russel, Cassandra");
        transaction.setTotalPayment(3000.00f);
        transaction.setCheckedInTime(ZonedDateTime.now(ZoneId.of("Z")));
        transaction.setCheckedOutTime(ZonedDateTime.now(ZoneId.of("Z")));
        transaction.setRoomType(RoomType.VIPRoom);
        databaseManager.storeTransaction(transaction);

        CustomFont.setup();
        HotelManagementSystem.setupMainFrame();
        HotelManagementSystem.startLoadingPanel();
        Thread.sleep(3000);
        HotelManagementSystem.startMainPanel();
    }

    private static void setupMainFrame() {
        mainFrame = new JFrame();
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
    }

    private static void startLoadingPanel() throws InterruptedException {
        mainFrame.getContentPane().add(LoadingPanel.setup());
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        LoadingPanel.setupBackend();
        mainFrame.getContentPane().remove(LoadingPanel.setup());
        mainFrame.setVisible(false);
        mainFrame.setLocation(0, 0);
    }

    private static void startMainPanel() {
        mainFrame.getContentPane().add(MainPanel.setup());
        mainFrame.setVisible(true);
        mainFrame.pack();
    }

    private static void registerDriver() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Registration of driver to driver manager failed!");
        }
    }

    private static void setupDBConnection() {
        databaseManager = DatabaseManager.getDatabaseManagerInstance();
    }

    private static void setupRoomManager() {
        roomManager = RoomManager.getRoomManagerInstance();
    }
}
