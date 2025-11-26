package view;

import controller.Controller;
import model.Day;
import model.Property;

import javax.swing.*;
import java.awt.*;

public class CalendarPanel extends JPanel {

    private Controller controller;
    private JPanel grid;

    public CalendarPanel(Controller controller) {
        this.controller = controller;
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
        grid = new JPanel(new GridLayout(5, 7, 3, 3));
        grid.setBounds(50, 120, 800, 360);
        grid.setBackground(Color.decode("#68BA7F"));
        add(grid);

        JButton back = Style.createButton("Back",
                750, 500, 100, 40, e -> controller.switchScreen("View"));
        add(back);
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            updateCalendar();
        }
    }

    private void updateCalendar() {
        grid.removeAll();

        Property currentProperty = controller.getCurrentProperty();

        Day[] days = currentProperty.getDays();

        for (int i = 1; i <= 35; i++) {
            JPanel cell = new JPanel(new BorderLayout());
            cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            cell.setBackground(Color.WHITE);

            if (i <= 30) {
                Day day = days[i - 1];
                double price = currentProperty.getBasePrice();
                boolean isBooked = day.isBooked();

                if (isBooked) {
                    cell.setBackground(Color.decode("#FFCCCC"));
                }

                // top left day number
                JPanel topLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
                topLeft.setOpaque(false);
                JLabel dayLabel = new JLabel(String.valueOf(i));
                dayLabel.setFont(new Font("Arial", Font.BOLD, 16));
                topLeft.add(dayLabel);

                // add booking status
                JLabel status = new JLabel(day.getStatus());
                status.setFont(new Font("Arial", Font.ITALIC, 10));

                JPanel topCenter = new JPanel(new FlowLayout(FlowLayout.CENTER));
                topCenter.setOpaque(false);
                topCenter.add(status);

                // bottom right
                JPanel bottomRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
                bottomRight.setOpaque(false);

                // format price
                JLabel priceLabel = new JLabel(String.format("₱%.0f", day.getPrice()));
                priceLabel.setFont(new Font("Arial", Font.PLAIN, 12));

                bottomRight.add(priceLabel);

                cell.add(topLeft, BorderLayout.NORTH);
                cell.add(topCenter, BorderLayout.CENTER);
                cell.add(bottomRight, BorderLayout.SOUTH);

            }
            grid.add(cell);
        }
        grid.revalidate();
        grid.repaint();

    }



}
