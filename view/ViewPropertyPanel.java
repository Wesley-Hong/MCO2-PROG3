package view;

import javax.swing.*;

public class ViewPropertyPanel extends JPanel {

    private MainFrame mainFrame;

    public ViewPropertyPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(null);
        setBackground(Style.BG);

        // Title
        JLabel label = new JLabel("View Property", SwingConstants.CENTER);
        label.setFont(Style.TITLE_FONT);
        label.setBounds(0, 0, 900, 80);
        add(label);

        JButton b1 = Style.createButton("Calendar",
                300, 100, 300, 50, e -> mainFrame.showScreen("Calendar"));
        JButton b2 = Style.createButton("Property Summary",
                300, 170, 300, 50, e -> mainFrame.showScreen("Summary"));
        JButton b3 = Style.createButton("Availability (date range)",
                300, 240, 300, 50, e -> mainFrame.showScreen("Availability"));
        JButton b4 = Style.createButton("Information (specific date)",
                300, 310, 300, 50, e -> mainFrame.showScreen("DayInformation"));
        JButton b5 = Style.createButton("Reservation Details",
                300, 380, 300, 50, e -> mainFrame.showScreen("ReservationDetails"));

        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);

        JButton back = Style.createButton("Back",
                750, 500, 100, 40, e -> mainFrame.showScreen("Menu"));
        add(back);

    }
}
