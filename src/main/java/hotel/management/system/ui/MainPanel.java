package hotel.management.system.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import hotel.management.system.utils.room_utils.RoomStatus;

public class MainPanel {
    private static JPanel mainPanel = null;
    private static Map<RoomStatus, Boolean> filterSettings = null;

    private MainPanel() {
    }

    public static JPanel setup() {
        if (MainPanel.mainPanel == null) {
            MainPanel.setupPanel();
            MainPanel.setupHeader();
            MainPanel.setupMainSection();
        }

        if (MainPanel.filterSettings == null) {
            MainPanel.setupFilterSetting();
        }
        return mainPanel;
    }

    private static void setupPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(800, 550));
        panel.setBackground(new Color(24, 25, 27));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setVisible(true);
        panel.setMaximumSize(panel.getPreferredSize());
        MainPanel.mainPanel = panel;
    }

    private static void setupHeader() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(24, 25, 27));
        panel.setPreferredSize(new Dimension(800, 100));
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setMaximumSize(panel.getPreferredSize());
        panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));

        JLabel logo = new JLabel(new ImageIcon("./src/main/java/hotel/management/system/assets/smalllogo.png"));
        logo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        logo.setPreferredSize(new Dimension(150, 75));
        logo.setMaximumSize(logo.getPreferredSize());

        JTextField searchBar = new JTextField("");
        searchBar.setPreferredSize(new Dimension(450, 30));
        searchBar.setMaximumSize(searchBar.getPreferredSize());
        searchBar.setFont(new Font("Inria Serif", Font.PLAIN, 14));
        searchBar.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        searchBar.setToolTipText(" Enter a room id ");

        JButton searchButton = new JButton(
                new ImageIcon("./src/main/java/hotel/management/system/assets/searchIcon2.png"));
        searchButton.setPreferredSize(new Dimension(50, 30));
        searchButton.setMaximumSize(searchButton.getPreferredSize());
        searchButton.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.black));
        searchButton.setFont(new Font("Inria Serif", Font.PLAIN, 14));
        searchButton.setBackground(Color.white);
        searchButton.setToolTipText(" Search for room id ");
        searchButton.addActionListener((ActionEvent e) -> {
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
            searchButton.setEnabled(false);

            JDialog dialog = new JDialog();
            dialog.setTitle("Room Status Filter");
            dialog.setPreferredSize(new Dimension(600, 510));
            dialog.setMaximumSize(dialog.getPreferredSize());
            dialog.setVisible(true);
            dialog.getContentPane().setBackground(new Color(24, 25, 27));
            dialog.setResizable(false);
            dialog.setLayout(new FlowLayout());

            JLabel roomListTitle = new JLabel(
                    "<html><center style='margin-top:30'>Room List</center></html>",
                    SwingConstants.CENTER);
            roomListTitle.setPreferredSize(new Dimension(600, 90));
            roomListTitle.setFont(new Font("Inria Serif", Font.PLAIN, 25));
            roomListTitle.setForeground(Color.white);
            roomListTitle.setMaximumSize(roomListTitle.getPreferredSize());

            JPanel roomsPanel = new JPanel();
            roomsPanel.setPreferredSize(new Dimension(600, 550));
            roomsPanel.setMaximumSize(roomsPanel.getPreferredSize());
            roomsPanel.setBackground(new Color(25, 25, 27));

            JScrollPane wholePane = new JScrollPane(roomsPanel);
            wholePane.setPreferredSize(new Dimension(500, 310));
            wholePane.setBorder(BorderFactory.createLineBorder(Color.white, 1));
            wholePane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            JButton closeButton = new JButton("Cancel");
            closeButton.setPreferredSize(new Dimension(100, 30));
            closeButton.setMaximumSize(closeButton.getPreferredSize());
            closeButton.setFont(new Font("Inria Serif", Font.PLAIN, 14));
            closeButton.setBackground(Color.white);
            closeButton.setToolTipText(" Close ");
            closeButton.addActionListener((ActionEvent x) -> {
                dialog.dispose();
                parentFrame.setVisible(true);
                searchButton.setEnabled(true);
            });

            Box box = Box.createVerticalBox();
            box.add(Box.createRigidArea(new Dimension(0, 15)));
            box.add(closeButton);
            box.add(Box.createRigidArea(new Dimension(0, 15)));

            dialog.getContentPane().add(roomListTitle);
            dialog.getContentPane().add(wholePane);
            dialog.getContentPane().add(box);
            dialog.pack();

            parentFrame.setVisible(false);

        });

        JButton filterMenuButton = new JButton(
                new ImageIcon("./src/main/java/hotel/management/system/assets/filterIcon.png"));
        filterMenuButton.setPreferredSize(new Dimension(50, 30));
        filterMenuButton.setMaximumSize(filterMenuButton.getPreferredSize());
        filterMenuButton.setFont(new Font("Inria Serif", Font.PLAIN, 14));
        filterMenuButton.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));
        filterMenuButton.setBackground(Color.white);
        filterMenuButton.setToolTipText(" Filter menu for room status ");
        filterMenuButton.addActionListener((ActionEvent e) -> {
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
            filterMenuButton.setEnabled(false);

            JDialog dialog = new JDialog();
            dialog.setTitle("Room Status Filter");
            dialog.setPreferredSize(new Dimension(350, 320));
            dialog.setMaximumSize(dialog.getPreferredSize());
            dialog.setVisible(true);
            dialog.getContentPane().setBackground(new Color(24, 25, 27));
            dialog.setResizable(false);
            dialog.setLayout(new FlowLayout());

            JLabel filterTitle = new JLabel(
                    "<html><center style='margin-top:30'>Set Filter Option</center></html>",
                    SwingConstants.CENTER);
            filterTitle.setPreferredSize(new Dimension(350, 65));
            filterTitle.setFont(new Font("Inria Serif", Font.PLAIN, 25));
            filterTitle.setForeground(Color.white);
            filterTitle.setMaximumSize(filterTitle.getPreferredSize());

            JCheckBox availableCheckBox = new JCheckBox("Available Rooms", filterSettings.get(RoomStatus.AVAILABLE));
            availableCheckBox.setFont(new Font("Inria Serif", Font.PLAIN, 16));
            availableCheckBox.setPreferredSize(new Dimension(350, 65));
            availableCheckBox.setBackground(new Color(24, 25, 27));
            availableCheckBox.setBorder(BorderFactory.createEmptyBorder(20, 100, 0, 0));
            availableCheckBox.setForeground(Color.white);
            availableCheckBox.setMaximumSize(availableCheckBox.getPreferredSize());

            JCheckBox occupiedCheckBox = new JCheckBox("Occupied Rooms", filterSettings.get(RoomStatus.OCCUPIED));
            occupiedCheckBox.setFont(new Font("Inria Serif", Font.PLAIN, 16));
            occupiedCheckBox.setPreferredSize(new Dimension(350, 35));
            occupiedCheckBox.setBackground(new Color(24, 25, 27));
            occupiedCheckBox.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0));
            occupiedCheckBox.setForeground(Color.white);
            occupiedCheckBox.setMaximumSize(occupiedCheckBox.getPreferredSize());

            JCheckBox reservedCheckBox = new JCheckBox("Reserved Rooms", filterSettings.get(RoomStatus.RESERVED));
            reservedCheckBox.setFont(new Font("Inria Serif", Font.PLAIN, 16));
            reservedCheckBox.setPreferredSize(new Dimension(350, 35));
            reservedCheckBox.setBackground(new Color(24, 25, 27));
            reservedCheckBox.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0));
            reservedCheckBox.setForeground(Color.white);
            reservedCheckBox.setMaximumSize(reservedCheckBox.getPreferredSize());

            JPanel buttonPanel = new JPanel();
            buttonPanel.setPreferredSize(new Dimension(350, 50));
            buttonPanel.setMaximumSize(buttonPanel.getPreferredSize());
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
            buttonPanel.setBackground(new Color(25, 25, 27));

            JButton filterSelected = new JButton("Filter");
            filterSelected.setPreferredSize(new Dimension(100, 30));
            filterSelected.setMaximumSize(filterSelected.getPreferredSize());
            filterSelected.setFont(new Font("Inria Serif", Font.PLAIN, 14));
            filterSelected.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));
            filterSelected.setBackground(Color.white);
            filterSelected.setToolTipText(" Filter ");
            filterSelected.addActionListener((ActionEvent x) -> {
                filterSettings.put(RoomStatus.AVAILABLE, availableCheckBox.isSelected());
                filterSettings.put(RoomStatus.OCCUPIED, occupiedCheckBox.isSelected());
                filterSettings.put(RoomStatus.RESERVED, reservedCheckBox.isSelected());

                dialog.dispose();
                parentFrame.setVisible(true);
                filterMenuButton.setEnabled(true);
            });

            JButton cancelSelected = new JButton("Cancel");
            cancelSelected.setPreferredSize(new Dimension(100, 30));
            cancelSelected.setMaximumSize(cancelSelected.getPreferredSize());
            cancelSelected.setFont(new Font("Inria Serif", Font.PLAIN, 14));
            cancelSelected.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));
            cancelSelected.setBackground(Color.white);
            cancelSelected.setToolTipText(" Cancel ");
            cancelSelected.addActionListener((ActionEvent x) -> {
                dialog.dispose();
                parentFrame.setVisible(true);
                filterMenuButton.setEnabled(true);
            });

            buttonPanel.add(filterSelected);
            buttonPanel.add(cancelSelected);

            dialog.getContentPane().add(filterTitle);
            dialog.getContentPane().add(availableCheckBox);
            dialog.getContentPane().add(occupiedCheckBox);
            dialog.getContentPane().add(reservedCheckBox);
            dialog.getContentPane().add(buttonPanel);
            dialog.pack();

            parentFrame.setVisible(false);
        });

        JButton addButton = new JButton(
                new ImageIcon("./src/main/java/hotel/management/system/assets/addIcon.png"));
        addButton.setPreferredSize(new Dimension(50, 30));
        addButton.setMaximumSize(addButton.getPreferredSize());
        addButton.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));
        addButton.setFont(new Font("Inria Serif", Font.PLAIN, 14));
        addButton.setBackground(Color.white);
        addButton.setToolTipText(" Check in room guest ");
        addButton.addActionListener((ActionEvent e) -> {
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
            addButton.setEnabled(false);

            JDialog dialog = new JDialog();
            dialog.setTitle("Room Status Filter");
            dialog.setPreferredSize(new Dimension(450, 320));
            dialog.setMaximumSize(dialog.getPreferredSize());
            dialog.setVisible(true);
            dialog.getContentPane().setBackground(new Color(24, 25, 27));
            dialog.setResizable(false);
            dialog.setLayout(new FlowLayout());

            JLabel addTitle = new JLabel(
                    "<html><center style='margin-top:30'>Check In Details</center></html>",
                    SwingConstants.CENTER);
            addTitle.setPreferredSize(new Dimension(450, 65));
            addTitle.setFont(new Font("Inria Serif", Font.PLAIN, 25));
            addTitle.setForeground(Color.white);
            addTitle.setMaximumSize(addTitle.getPreferredSize());

            JPanel guestNamesFieldContainer = new JPanel();
            guestNamesFieldContainer.setPreferredSize(new Dimension(450, 70));
            guestNamesFieldContainer.setMaximumSize(guestNamesFieldContainer.getPreferredSize());
            guestNamesFieldContainer.setBackground(new Color(25, 25, 27));
            guestNamesFieldContainer.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));

            JLabel guestNamesLabel = new JLabel("Guest Names :");
            guestNamesLabel.setPreferredSize(new Dimension(100, 30));
            guestNamesLabel.setMaximumSize(guestNamesLabel.getPreferredSize());
            guestNamesLabel.setFont(new Font("Inria Serif", Font.PLAIN, 14));
            guestNamesLabel.setForeground(Color.white);

            JTextField guestNamesField = new JTextField("");
            guestNamesField.setPreferredSize(new Dimension(300, 30));
            guestNamesField.setMaximumSize(guestNamesField.getPreferredSize());
            guestNamesField.setFont(new Font("Inria Serif", Font.PLAIN, 14));
            guestNamesField.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
            guestNamesField.setToolTipText(" Enter guest names (separated with comma)");

            guestNamesFieldContainer.add(guestNamesLabel);
            guestNamesFieldContainer.add(guestNamesField);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setPreferredSize(new Dimension(450, 50));
            buttonPanel.setMaximumSize(buttonPanel.getPreferredSize());
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
            buttonPanel.setBackground(new Color(25, 25, 27));

            JButton checkInGuest = new JButton("Check In");
            checkInGuest.setPreferredSize(new Dimension(100, 30));
            checkInGuest.setMaximumSize(checkInGuest.getPreferredSize());
            checkInGuest.setFont(new Font("Inria Serif", Font.PLAIN, 14));
            checkInGuest.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));
            checkInGuest.setBackground(Color.white);
            checkInGuest.setToolTipText(" Check In ");
            checkInGuest.addActionListener((ActionEvent x) -> {

                dialog.dispose();
                parentFrame.setVisible(true);
                addButton.setEnabled(true);
            });

            JButton cancelCheckIn = new JButton("Cancel");
            cancelCheckIn.setPreferredSize(new Dimension(100, 30));
            cancelCheckIn.setMaximumSize(cancelCheckIn.getPreferredSize());
            cancelCheckIn.setFont(new Font("Inria Serif", Font.PLAIN, 14));
            cancelCheckIn.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));
            cancelCheckIn.setBackground(Color.white);
            cancelCheckIn.setToolTipText(" Cancel ");
            cancelCheckIn.addActionListener((ActionEvent x) -> {
                dialog.dispose();
                parentFrame.setVisible(true);
                addButton.setEnabled(true);
            });

            buttonPanel.add(checkInGuest);
            buttonPanel.add(cancelCheckIn);

            dialog.getContentPane().add(addTitle);
            dialog.getContentPane().add(guestNamesFieldContainer);
            dialog.getContentPane().add(buttonPanel);
            dialog.pack();

            parentFrame.setVisible(false);
        });

        panel.add(logo);
        panel.add(searchBar);
        panel.add(searchButton);
        panel.add(filterMenuButton);
        panel.add(addButton);
        mainPanel.add(panel);
    }

    private static void setupMainSection() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(800, 450));
        panel.setBackground(new Color(24, 25, 27));
        mainPanel.add(panel);
    }

    private static void setupFilterSetting() {
        filterSettings = new HashMap<>();
        filterSettings.put(RoomStatus.AVAILABLE, false);
        filterSettings.put(RoomStatus.OCCUPIED, false);
        filterSettings.put(RoomStatus.RESERVED, false);
    }
}
