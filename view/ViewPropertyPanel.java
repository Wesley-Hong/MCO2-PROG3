package view;

import controller.Controller;
import javax.swing.*;

/**
 * Panel for view property and let user choose functions such as:
 * View calendar, property summary, availability (date range), specific
 * date information, and reservation details
 */
public class ViewPropertyPanel extends JPanel {

    private Controller controller;

    /**
     * Constructor for accessing 5 different features
     * @param controller the main application controller that handles
     *                       screen navigation and business logic
     */
    public ViewPropertyPanel(Controller controller) {
        this.controller = controller;
        setLayout(null);
        setBackground(Style.BG);

        // Title
        JLabel label = new JLabel("View Property", SwingConstants.CENTER);
        label.setFont(Style.TITLE_FONT);
        label.setBounds(0, 0, 900, 80);
        add(label);

        JButton b1 = Style.createButton("Calendar",
                300, 100, 300, 50, e -> controller.switchScreen("Calendar"));
        JButton b2 = Style.createButton("Property Summary",
                300, 170, 300, 50, e -> controller.switchScreen("Summary"));
        JButton b3 = Style.createButton("Availability (date range)",
                300, 240, 300, 50, e -> controller.switchScreen("Availability"));
        JButton b4 = Style.createButton("Information (specific date)",
                300, 310, 300, 50, e -> controller.switchScreen("DayInformation"));
        JButton b5 = Style.createButton("Reservation Details",
                300, 380, 300, 50, e -> controller.switchScreen("ReservationDetails"));

        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);

        JButton back = Style.createButton("Back",
                750, 500, 100, 40, e -> controller.switchScreen("Menu"));
        add(back);

    }
}
