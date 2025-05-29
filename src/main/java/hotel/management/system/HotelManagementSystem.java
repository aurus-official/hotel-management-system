package hotel.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;

import hotel.management.system.ui.CustomFont;
import hotel.management.system.ui.LoadingPanel;
import hotel.management.system.ui.MainPanel;

/**
 *
 * @author aururus
 */

public class HotelManagementSystem {
    private static JFrame mainFrame;

    public static void main(String[] args) throws InterruptedException {
        HotelManagementSystem.registerDriver();
        HotelManagementSystem.setupDBConnection();
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
        try {
            String username = "username-here";
            String password = "password-here";
            Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/hms", username,
                    password);
            System.out.println("DB accessed!");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
