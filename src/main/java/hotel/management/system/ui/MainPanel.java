package hotel.management.system.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import hotel.management.system.utils.billing_utils.BillingManager;
import hotel.management.system.utils.billing_utils.Transaction;
import hotel.management.system.utils.db_utils.DatabaseManager;
import hotel.management.system.utils.guest_utils.Guest;
import hotel.management.system.utils.room_utils.Room;
import hotel.management.system.utils.room_utils.RoomHours;
import hotel.management.system.utils.room_utils.RoomManager;
import hotel.management.system.utils.room_utils.RoomStatus;
import raven.datetime.DatePicker;
import raven.datetime.TimePicker;

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

            if (!searchBar.getText().isEmpty()) {
                return;
            }

            RoomManager allRooms = RoomManager.getRoomManagerInstance();

            JDialog dialog = new JDialog();
            dialog.setTitle("Search Room List");
            dialog.setPreferredSize(new Dimension(500, 510));
            dialog.setMaximumSize(dialog.getPreferredSize());
            dialog.setVisible(true);
            dialog.getContentPane().setBackground(new Color(24, 25, 27));
            dialog.setResizable(false);
            dialog.setLayout(new FlowLayout());

            JLabel roomListTitle = new JLabel(String.format(
                    "<html><center style='margin-top:30'>%s Room List</center></html>",
                    filterSettings.entrySet().stream().filter(entry -> entry.getValue()).collect(Collectors.toList())
                            .get(0).getKey().toString()),
                    SwingConstants.CENTER);
            roomListTitle.setPreferredSize(new Dimension(500, 90));
            roomListTitle.setFont(new Font("Inria Serif", Font.PLAIN, 25));
            roomListTitle.setForeground(Color.white);
            roomListTitle.setMaximumSize(roomListTitle.getPreferredSize());

            JPanel roomsPanel = new JPanel();
            roomsPanel.setPreferredSize(
                    new Dimension(400, ((int) Math.ceil(allRooms.filter(filterSettings).size() / 2)) * 53));
            roomsPanel.setMaximumSize(roomsPanel.getPreferredSize());
            roomsPanel.setBackground(new Color(25, 25, 27));
            FlowLayout flowLayout = new FlowLayout();
            flowLayout.setHgap(15);
            flowLayout.setVgap(20);
            roomsPanel.setLayout(flowLayout);

            List<Room> filteredRooms = allRooms.filter(filterSettings);

            if (filterSettings.get(RoomStatus.AVAILABLE)) {
                filteredRooms.stream().forEach(room -> {
                    JButton roomButton = new JButton(String.format("Room No. %d", room.getRoomId()));
                    roomButton.setPreferredSize(new Dimension(150, 30));
                    roomButton.setMaximumSize(roomButton.getPreferredSize());
                    roomButton.setFont(new Font("Inria Serif", Font.PLAIN, 14));
                    roomButton.setBackground(Color.white);
                    roomButton.setToolTipText(String.format("Room %d", room.getRoomId()));
                    roomButton.addActionListener((ActionEvent z) -> {
                        roomButton.setEnabled(false);
                        dialog.setVisible(false);
                        JDialog addDialog = new JDialog();
                        addDialog.setTitle("Add User Information");
                        addDialog.setPreferredSize(new Dimension(450, 360));
                        addDialog.setMaximumSize(addDialog.getPreferredSize());
                        addDialog.setVisible(true);
                        addDialog.getContentPane().setBackground(new Color(24, 25, 27));
                        addDialog.setResizable(false);
                        FlowLayout addDialogLayout = new FlowLayout();
                        flowLayout.setHgap(5);
                        flowLayout.setVgap(30);
                        addDialog.setLayout(addDialogLayout);

                        JLabel addTitle = new JLabel(String.format(
                                "<html><center style='margin-top:30'>Room No. %d</center></html>",
                                room.getRoomId()),
                                SwingConstants.CENTER);
                        addTitle.setPreferredSize(new Dimension(450, 90));
                        addTitle.setFont(new Font("Inria Serif", Font.PLAIN, 25));
                        addTitle.setForeground(Color.white);
                        addTitle.setMaximumSize(addTitle.getPreferredSize());

                        JPanel guestNamesFieldContainer = new JPanel();
                        guestNamesFieldContainer.setPreferredSize(new Dimension(450, 40));
                        guestNamesFieldContainer.setMaximumSize(guestNamesFieldContainer.getPreferredSize());
                        guestNamesFieldContainer.setBackground(new Color(25, 25, 27));

                        JLabel guestNamesLabel = new JLabel("Guest names :");
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

                        JPanel lengthOfStayFieldContainer = new JPanel();
                        lengthOfStayFieldContainer.setPreferredSize(new Dimension(450, 40));
                        lengthOfStayFieldContainer.setMaximumSize(lengthOfStayFieldContainer.getPreferredSize());
                        lengthOfStayFieldContainer.setBackground(new Color(25, 25, 27));

                        JLabel lengthOfStayLabel = new JLabel("Enter length of stay :");
                        lengthOfStayLabel.setPreferredSize(new Dimension(150, 30));
                        lengthOfStayLabel.setMaximumSize(lengthOfStayLabel.getPreferredSize());
                        lengthOfStayLabel.setFont(new Font("Inria Serif", Font.PLAIN, 14));
                        lengthOfStayLabel.setForeground(Color.white);

                        JComboBox<RoomHours> lengthOfStayBox = new JComboBox<>(RoomHours.values());
                        lengthOfStayBox.setPreferredSize(new Dimension(250, 30));
                        lengthOfStayBox.setMaximumSize(lengthOfStayBox.getPreferredSize());
                        lengthOfStayBox.setFont(new Font("Inria Serif", Font.PLAIN, 14));
                        lengthOfStayBox.setToolTipText(" Select length of stay ");

                        lengthOfStayFieldContainer.add(lengthOfStayLabel);
                        lengthOfStayFieldContainer.add(lengthOfStayBox);

                        JPanel selectCheckInTimeContainer = new JPanel();
                        selectCheckInTimeContainer.setPreferredSize(new Dimension(450, 40));
                        selectCheckInTimeContainer.setMaximumSize(selectCheckInTimeContainer.getPreferredSize());
                        selectCheckInTimeContainer.setBackground(new Color(25, 25, 27));

                        JLabel selectCheckInTimeLabel = new JLabel("Enter check in time :");
                        selectCheckInTimeLabel.setPreferredSize(new Dimension(150, 30));
                        selectCheckInTimeLabel.setMaximumSize(selectCheckInTimeLabel.getPreferredSize());
                        selectCheckInTimeLabel.setFont(new Font("Inria Serif", Font.PLAIN, 14));
                        selectCheckInTimeLabel.setForeground(Color.white);

                        JFormattedTextField selectCheckInTimeField = new JFormattedTextField();
                        selectCheckInTimeField.setPreferredSize(new Dimension(250, 30));

                        TimePicker selectCheckInTime = new TimePicker();
                        selectCheckInTime.setEditor(selectCheckInTimeField);
                        selectCheckInTime.set24HourView(true);

                        selectCheckInTimeContainer.add(selectCheckInTimeLabel);
                        selectCheckInTimeContainer.add(selectCheckInTimeField);

                        JPanel selectCheckInDateContainer = new JPanel();
                        selectCheckInDateContainer.setPreferredSize(new Dimension(450, 40));
                        selectCheckInDateContainer.setMaximumSize(selectCheckInDateContainer.getPreferredSize());
                        selectCheckInDateContainer.setBackground(new Color(25, 25, 27));

                        JLabel selectCheckInDateLabel = new JLabel("Enter check in date :");
                        selectCheckInDateLabel.setPreferredSize(new Dimension(150, 30));
                        selectCheckInDateLabel.setMaximumSize(selectCheckInDateLabel.getPreferredSize());
                        selectCheckInDateLabel.setFont(new Font("Inria Serif", Font.PLAIN, 14));
                        selectCheckInDateLabel.setForeground(Color.white);

                        JFormattedTextField selectCheckInDateField = new JFormattedTextField();
                        selectCheckInDateField.setPreferredSize(new Dimension(250, 30));

                        DatePicker selectCheckInDate = new DatePicker();
                        selectCheckInDate.setEditor(selectCheckInDateField);

                        selectCheckInDateContainer.add(selectCheckInDateLabel);
                        selectCheckInDateContainer.add(selectCheckInDateField);

                        JPanel buttonPanel = new JPanel();
                        buttonPanel.setPreferredSize(new Dimension(450, 50));
                        buttonPanel.setMaximumSize(buttonPanel.getPreferredSize());
                        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
                        buttonPanel.setBackground(new Color(25, 25, 27));

                        JButton checkInGuest = new JButton("Check In");
                        checkInGuest.setPreferredSize(new Dimension(100, 30));
                        checkInGuest.setMaximumSize(checkInGuest.getPreferredSize());
                        checkInGuest.setFont(new Font("Inria Serif", Font.PLAIN, 14));
                        checkInGuest.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1,
                                Color.black));
                        checkInGuest.setBackground(Color.white);
                        checkInGuest.setToolTipText(" Check In ");
                        checkInGuest.addActionListener((ActionEvent x) -> {
                            addDialog.dispose();

                            Guest guest = new Guest();
                            Arrays.stream(guestNamesField.getText().split(",")).forEach(name -> guest.addGuest(name));
                            guest.setLengthOfStay((RoomHours) lengthOfStayBox.getSelectedItem());

                            ZonedDateTime zonedDateTime = ZonedDateTime.of(selectCheckInDate.getSelectedDate(),
                                    selectCheckInTime.getSelectedTime(), ZoneId.of("Z"));

                            guest.setCheckedInDateTime(zonedDateTime);
                            guest.setCheckedOutDateTime(zonedDateTime
                                    .plusHours(
                                            Long.valueOf(((RoomHours) lengthOfStayBox.getSelectedItem()).toString())));
                            room.setGuest(guest);

                            BillingManager billingManager = BillingManager.getBillingManagerInstance();
                            Transaction transaction = billingManager.createTransaction(room);

                            DatabaseManager databaseManager = DatabaseManager.getDatabaseManagerInstance();
                            databaseManager.storeTransaction(transaction);

                            dialog.dispose();
                            parentFrame.setVisible(true);
                            searchButton.setEnabled(true);
                        });

                        JButton cancelCheckIn = new JButton("Cancel");
                        cancelCheckIn.setPreferredSize(new Dimension(100, 30));
                        cancelCheckIn.setMaximumSize(cancelCheckIn.getPreferredSize());
                        cancelCheckIn.setFont(new Font("Inria Serif", Font.PLAIN, 14));
                        cancelCheckIn.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1,
                                Color.black));
                        cancelCheckIn.setBackground(Color.white);
                        cancelCheckIn.setToolTipText(" Cancel ");
                        cancelCheckIn.addActionListener((ActionEvent x) -> {
                            addDialog.dispose();
                            dialog.setVisible(true);
                            roomButton.setEnabled(true);
                        });

                        buttonPanel.add(checkInGuest);
                        buttonPanel.add(cancelCheckIn);

                        addDialog.getContentPane().add(addTitle);
                        addDialog.getContentPane().add(guestNamesFieldContainer);
                        addDialog.getContentPane().add(lengthOfStayFieldContainer);
                        addDialog.getContentPane().add(selectCheckInTimeContainer);
                        addDialog.getContentPane().add(selectCheckInDateContainer);
                        addDialog.getContentPane().add(buttonPanel);
                        addDialog.pack();
                    });

                    roomsPanel.add(roomButton);
                });
            }

            if (filterSettings.get(RoomStatus.OCCUPIED)) {
                filteredRooms.stream().forEach(room -> {
                    JButton roomButton = new JButton(String.format("Room No. %d", room.getRoomId()));
                    roomButton.setPreferredSize(new Dimension(150, 30));
                    roomButton.setMaximumSize(roomButton.getPreferredSize());
                    roomButton.setFont(new Font("Inria Serif", Font.PLAIN, 14));
                    roomButton.setBackground(Color.white);
                    roomButton.setToolTipText(String.format("Room %d", room.getRoomId()));
                    roomButton.addActionListener((ActionEvent a) -> {
                        roomButton.setEnabled(false);
                        dialog.setVisible(false);
                        JDialog checkRoomDialog = new JDialog();
                        checkRoomDialog.setTitle("checkRoom User Information");
                        checkRoomDialog.setPreferredSize(new Dimension(450, 360));
                        checkRoomDialog.setMaximumSize(checkRoomDialog.getPreferredSize());
                        checkRoomDialog.setVisible(true);
                        checkRoomDialog.getContentPane().setBackground(new Color(24, 25, 27));
                        checkRoomDialog.setResizable(false);
                        FlowLayout checkRoomDialogLayout = new FlowLayout();
                        flowLayout.setHgap(5);
                        flowLayout.setVgap(30);
                        checkRoomDialog.setLayout(checkRoomDialogLayout);

                        JLabel checkRoomTitle = new JLabel(String.format(
                                "<html><center style='margin-top:30'>Room No. %d</center></html>",
                                room.getRoomId()),
                                SwingConstants.CENTER);
                        checkRoomTitle.setPreferredSize(new Dimension(450, 90));
                        checkRoomTitle.setFont(new Font("Inria Serif", Font.PLAIN, 25));
                        checkRoomTitle.setForeground(Color.white);

                        JPanel buttonPanel = new JPanel();
                        buttonPanel.setPreferredSize(new Dimension(450, 50));
                        buttonPanel.setMaximumSize(buttonPanel.getPreferredSize());
                        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
                        buttonPanel.setBackground(new Color(25, 25, 27));

                        JButton checkOutGuest = new JButton("Check Out");
                        checkOutGuest.setPreferredSize(new Dimension(100, 30));
                        checkOutGuest.setMaximumSize(checkOutGuest.getPreferredSize());
                        checkOutGuest.setFont(new Font("Inria Serif", Font.PLAIN, 14));
                        checkOutGuest.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1,
                                Color.black));
                        checkOutGuest.setBackground(Color.white);
                        checkOutGuest.setToolTipText(" Check Out ");
                        checkOutGuest.addActionListener((ActionEvent x) -> {
                            checkRoomDialog.dispose();
                            dialog.setVisible(true);
                            roomButton.setEnabled(true);
                        });

                        JButton cancelCheckOut = new JButton("Cancel");
                        cancelCheckOut.setPreferredSize(new Dimension(100, 30));
                        cancelCheckOut.setMaximumSize(cancelCheckOut.getPreferredSize());
                        cancelCheckOut.setFont(new Font("Inria Serif", Font.PLAIN, 14));
                        cancelCheckOut.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1,
                                Color.black));
                        cancelCheckOut.setBackground(Color.white);
                        cancelCheckOut.setToolTipText(" Cancel ");
                        cancelCheckOut.addActionListener((ActionEvent x) -> {
                            checkRoomDialog.dispose();
                            dialog.setVisible(true);
                            roomButton.setEnabled(true);
                        });

                        buttonPanel.add(checkOutGuest);
                        buttonPanel.add(cancelCheckOut);

                        checkRoomDialog.getContentPane().add(checkRoomTitle);
                        checkRoomDialog.getContentPane().add(buttonPanel);
                        checkRoomDialog.pack();
                    });
                    roomsPanel.add(roomButton);
                });
            }

            JScrollPane wholePane = new JScrollPane(roomsPanel);
            wholePane.setPreferredSize(new Dimension(400, 310));
            wholePane.setBorder(BorderFactory.createLineBorder(new Color(25, 25, 27)));
            wholePane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            wholePane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

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

            JRadioButton availableRadioButton = new JRadioButton("Available Rooms",
                    filterSettings.get(RoomStatus.AVAILABLE));
            availableRadioButton.setFont(new Font("Inria Serif", Font.PLAIN, 16));
            availableRadioButton.setPreferredSize(new Dimension(350, 65));
            availableRadioButton.setBackground(new Color(24, 25, 27));
            availableRadioButton.setBorder(BorderFactory.createEmptyBorder(20, 100, 0, 0));
            availableRadioButton.setForeground(Color.white);
            availableRadioButton.setMaximumSize(availableRadioButton.getPreferredSize());

            JRadioButton occupiedRadioButton = new JRadioButton("Occupied Rooms",
                    filterSettings.get(RoomStatus.OCCUPIED));
            occupiedRadioButton.setFont(new Font("Inria Serif", Font.PLAIN, 16));
            occupiedRadioButton.setPreferredSize(new Dimension(350, 35));
            occupiedRadioButton.setBackground(new Color(24, 25, 27));
            occupiedRadioButton.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0));
            occupiedRadioButton.setForeground(Color.white);
            occupiedRadioButton.setMaximumSize(occupiedRadioButton.getPreferredSize());

            JRadioButton reservedRadioButton = new JRadioButton("Reserved Rooms",
                    filterSettings.get(RoomStatus.RESERVED));
            reservedRadioButton.setFont(new Font("Inria Serif", Font.PLAIN, 16));
            reservedRadioButton.setPreferredSize(new Dimension(350, 35));
            reservedRadioButton.setBackground(new Color(24, 25, 27));
            reservedRadioButton.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0));
            reservedRadioButton.setForeground(Color.white);
            reservedRadioButton.setMaximumSize(reservedRadioButton.getPreferredSize());

            ButtonGroup buttonGroup = new ButtonGroup();
            buttonGroup.add(availableRadioButton);
            buttonGroup.add(occupiedRadioButton);
            buttonGroup.add(reservedRadioButton);

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
                filterSettings.put(RoomStatus.AVAILABLE, availableRadioButton.isSelected());
                filterSettings.put(RoomStatus.OCCUPIED, occupiedRadioButton.isSelected());
                filterSettings.put(RoomStatus.RESERVED, reservedRadioButton.isSelected());

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
            dialog.getContentPane().add(availableRadioButton);
            dialog.getContentPane().add(occupiedRadioButton);
            dialog.getContentPane().add(reservedRadioButton);
            dialog.getContentPane().add(buttonPanel);
            dialog.pack();

            parentFrame.setVisible(false);
        });

        panel.add(logo);
        panel.add(searchBar);
        panel.add(searchButton);
        panel.add(filterMenuButton);
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
        filterSettings.put(RoomStatus.AVAILABLE, true);
        filterSettings.put(RoomStatus.OCCUPIED, false);
        filterSettings.put(RoomStatus.RESERVED, false);
    }
}
