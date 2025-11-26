package view;

import controller.Controller;
import model.Day;
import model.Property;
import javax.swing.*;
import java.awt.*;

/**
 * Panel for Calendar it shows the price per night and environment impact price
 */
public class CalendarPanel extends JPanel {

    private Controller controller;
    private JPanel grid;

    /**
     * Constructor for calendar display
     * @param controller the main application controller that handles
     *                     screen navigation and business logic
     */
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

            label1.setBackground(Color.decode("#5A77E0"));

            label1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            dayHeader.add(label1);
        }
        add(dayHeader);

        // calendar grid
        grid = new JPanel(new GridLayout(5, 7, 3, 3));
        grid.setBounds(50, 120, 800, 360);
        grid.setBackground(Color.decode("#68BA7F"));
        add(grid);

        // Legend panel
        JPanel legend = new JPanel();
        legend.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        legend.setBounds(30, 495, 700, 40);  // Moved left (30 instead of 50)
        legend.setBackground(Style.BG);

        // Green box
        JPanel greenBox = new JPanel();
        greenBox.setPreferredSize(new Dimension(15, 15));  // Smaller box
        greenBox.setBackground(Color.decode("#90EE90"));
        greenBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        legend.add(greenBox);
        JLabel greenLabel = new JLabel("Eco-Friendly (80-99%)");
        greenLabel.setFont(new Font("Arial", Font.PLAIN, 11));  // Smaller font
        legend.add(greenLabel);

        // White box
        JPanel whiteBox = new JPanel();
        whiteBox.setPreferredSize(new Dimension(15, 15));
        whiteBox.setBackground(Color.WHITE);
        whiteBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        legend.add(whiteBox);
        JLabel whiteLabel = new JLabel("Standard (100%)");
        whiteLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        legend.add(whiteLabel);

        // Yellow box
        JPanel yellowBox = new JPanel();
        yellowBox.setPreferredSize(new Dimension(15, 15));
        yellowBox.setBackground(Color.YELLOW);
        yellowBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        legend.add(yellowBox);
        JLabel yellowLabel = new JLabel("High Impact (101-120%)");
        yellowLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        legend.add(yellowLabel);

        // Red box
        JPanel redBox = new JPanel();
        redBox.setPreferredSize(new Dimension(15, 15));
        redBox.setBackground(Color.decode("#FFCCCC"));
        redBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        legend.add(redBox);
        JLabel redLabel = new JLabel("Booked");
        redLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        legend.add(redLabel);

        add(legend);

        JButton back = Style.createButton("Back",
                750, 500, 100, 40, e -> controller.switchScreen("View"));
        add(back);
    }

    /**
     * Overrides setVisible and updates the calendar when there is changes
     * @param visible  true to make the component visible; false to
     *          make it invisible
     */
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            updateCalendar();
        }
    }

    /**
     * Updates the calendar information when there is changes
     */
    private void updateCalendar() {

        grid.removeAll();

        Property currentProperty = controller.getCurrentProperty();
        Day[] days = currentProperty.getDays();
        double basePrice = currentProperty.getBasePrice();

        for (int i = 1; i <= 35; i++) {
            JPanel cell = new JPanel(new BorderLayout());
            cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            cell.setBackground(Color.WHITE);

            if (i <= 30) {
                Day day = days[i - 1];
                boolean isBooked = day.isBooked();
                double dayPrice = day.getPrice();

                // Calculate price modifier
                double priceModifier = dayPrice / basePrice;

                // Set background color based on booking status and price modifier
                if (isBooked) {
                    cell.setBackground(Color.decode("#FFCCCC"));
                } else if (priceModifier < 1.0) {
                    cell.setBackground(Color.decode("#90EE90"));
                } else if (priceModifier > 1.0) {
                    cell.setBackground(Color.YELLOW);
                } else {
                    cell.setBackground(Color.WHITE);
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
                JLabel priceLabel = new JLabel(String.format("₱%.0f", dayPrice));
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