package hotel.management.system.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class LoadingPanel {

    private static JPanel loadingPanel = null;
    private static JProgressBar progressBar = null;

    private LoadingPanel() {
    }

    public static JPanel setup() {
        if (LoadingPanel.loadingPanel == null) {
            LoadingPanel.setupPanel();
            LoadingPanel.setupHeader();
            LoadingPanel.setupFooter();
        }
        return LoadingPanel.loadingPanel;
    }

    private static void setupPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(600, 450));
        panel.setBackground(new Color(24, 25, 27));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setVisible(true);
        LoadingPanel.loadingPanel = panel;
    }

    private static void setupHeader() {
        JLabel logo = new JLabel(new ImageIcon("./src/main/java/hotel/management/system/assets/fixed.png"));
        logo.setBorder(BorderFactory.createEmptyBorder(70, 0, 0, 0));
        logo.setPreferredSize(new Dimension(600, 200));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel(
                "<html>Hotel Management<br/><center style='margin-top:-15'>System</center></html>",
                SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Inria Serif", Font.PLAIN, 45));
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 80, 0));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LoadingPanel.loadingPanel.add(logo);
        LoadingPanel.loadingPanel.add(title);
    }

    private static void setupFooter() {
        JProgressBar progressBar = new JProgressBar();
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setString("Setting up databases...");
        progressBar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 50, 40, 50, new Color(24, 25, 27)),
                BorderFactory.createLineBorder(new Color(230, 230, 230), 1)));
        progressBar.setPreferredSize(new Dimension(500, 70));
        progressBar.setBackground(new Color(24, 25, 27));
        progressBar.setForeground(new Color(67, 36, 15));
        progressBar.setFont(new Font("Inria Serif", Font.PLAIN, 15));
        progressBar.setUI(new BasicProgressBarUI() {
            @Override
            protected Color getSelectionBackground() {
                return Color.white;
            }

            @Override
            protected Color getSelectionForeground() {
                return Color.white;
            }
        });

        LoadingPanel.loadingPanel.add(progressBar);
        LoadingPanel.progressBar = progressBar;
    }

    public static void setupBackend() {
        int i = 0;
        try {
            while (i < 100) {
                progressBar.setValue(i + 1);
                Thread.sleep(100);
                i += 1;
            }
        } catch (Exception e) {
        }
    }
}
