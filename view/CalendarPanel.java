package view;

import javax.swing.*;
import java.awt.*;

public class CalendarPanel extends JPanel {

    private MainFrame mainFrame;

    public CalendarPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(null);
        setBackground(Style.BG);

        // Title
        JLabel label = new JLabel("Calendar", SwingConstants.CENTER);
        label.setFont(Style.TITLE_FONT);
        label.setBounds(0, 0, 900, 80);
        add(label);

        // sunday to saturday
        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

        JPanel dayHeader = new JPanel(new GridLayout(1, 7));
        dayHeader.setBounds(50, 80, 800, 40);
        dayHeader.setBackground(Color.decode("#68BA7F"));

        for (String d : days) {
            JLabel label1 = new JLabel(d, SwingConstants.CENTER);
            label1.setFont(new Font("Arial", Font.BOLD, 18));
            label1.setOpaque(true);

            label1.setBackground(Color.YELLOW);

            label1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            dayHeader.add(label1);
        }
        add(dayHeader);

        // calendar grid
        JPanel grid = new JPanel(new GridLayout(5, 7, 3, 3));
        grid.setBounds(50, 120, 800, 360);
        grid.setBackground(Color.decode("#68BA7F"));

        for (int day = 1; day <= 35; day++) {

            JPanel cell = new JPanel(new BorderLayout());
            cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            cell.setBackground(Color.WHITE);

            if (day <= 30) {

                // TOP-LEFT: Day number (small panel)
                JPanel topLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
                topLeft.setOpaque(false);
                JLabel dayLabel = new JLabel(String.valueOf(day));
                dayLabel.setFont(new Font("Arial", Font.BOLD, 16));
                topLeft.add(dayLabel);

                // BOTTOM-RIGHT: Price label
                JPanel bottomRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
                bottomRight.setOpaque(false);
                JLabel priceLabel = new JLabel("₱1500");
                priceLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                bottomRight.add(priceLabel);

                // Add to cell
                cell.add(topLeft, BorderLayout.NORTH);
                cell.add(bottomRight, BorderLayout.SOUTH);

            } else {
                // Empty cell
                cell.add(new JLabel(""), BorderLayout.CENTER);
            }

            grid.add(cell);
        }

        add(grid);

        JButton back = Style.createButton("Back",
                750, 500, 100, 40, e -> mainFrame.showScreen("Menu"));
        add(back);

    }



}
