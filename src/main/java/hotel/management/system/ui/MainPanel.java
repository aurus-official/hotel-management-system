package hotel.management.system.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class MainPanel {
    private static JPanel mainPanel = null;

    private MainPanel() {
    }

    public static JPanel setup() {
        if (MainPanel.mainPanel == null) {
            MainPanel.setupPanel();
            MainPanel.setupHeader();
            MainPanel.setupMainSection();
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

        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalGlue());
        box.add(searchBar);
        box.add(Box.createVerticalGlue());

        JButton searchButton = new JButton(
                new ImageIcon("./src/main/java/hotel/management/system/assets/searchIcon2.png"));
        searchButton.setPreferredSize(new Dimension(50, 30));
        searchButton.setAlignmentX(SwingConstants.RIGHT);
        searchButton.setMaximumSize(searchButton.getPreferredSize());
        searchButton.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.black));
        searchButton.setFont(new Font("Inria Serif", Font.PLAIN, 14));
        searchButton.setBackground(Color.white);
        searchButton.setToolTipText(" Search for room id ");

        JButton filterButton = new JButton(
                new ImageIcon("./src/main/java/hotel/management/system/assets/filterIcon.png"));
        filterButton.setPreferredSize(new Dimension(50, 30));
        filterButton.setAlignmentX(SwingConstants.RIGHT);
        filterButton.setMaximumSize(filterButton.getPreferredSize());
        filterButton.setFont(new Font("Inria Serif", Font.PLAIN, 14));
        filterButton.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));
        filterButton.setBackground(Color.white);
        filterButton.setToolTipText(" Filter room status ");
        filterButton.addActionListener((ActionEvent e) -> {
            filterButton.setEnabled(false);
            JDialog dialog = new JDialog();
            dialog.setTitle("Room Status Filter");
            dialog.setPreferredSize(new Dimension(500, 300));
            dialog.setMaximumSize(dialog.getPreferredSize());
            dialog.setVisible(true);
            dialog.getContentPane().setBackground(new Color(24, 25, 27));
            dialog.setResizable(false);
            dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));

            JLabel filterTitle = new JLabel(
                    "<html><center style='margin-top:20'>Set Filter Option</center></html>",
                    SwingConstants.CENTER);
            filterTitle.setPreferredSize(new Dimension(300, 10));
            filterTitle.setFont(new Font("Inria Serif", Font.PLAIN, 25));
            filterTitle.setForeground(Color.white);

            dialog.getContentPane().add(filterTitle);
            dialog.pack();

            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
            parentFrame.setVisible(false);
        });

        panel.add(logo);
        panel.add(searchBar);
        panel.add(searchButton);
        panel.add(filterButton);
        mainPanel.add(panel);
    }

    private static void setupMainSection() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(800, 450));
        panel.setBackground(new Color(24, 25, 27));
        mainPanel.add(panel);
    }
}
