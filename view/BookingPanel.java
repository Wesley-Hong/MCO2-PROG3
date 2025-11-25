package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class BookingPanel extends JPanel {

    private Controller controller;

    public BookingPanel(Controller controller) {
        this.controller = controller;
        setLayout(null);
        setBackground(Style.BG);

        // Title
        JLabel label = new JLabel("Simulate Booking", SwingConstants.CENTER);
        label.setFont(Style.TITLE_FONT);
        label.setBounds(0, 0, 900, 80);
        add(label);

        // name
        JLabel guestName = new JLabel("Guest Name: ");
        guestName.setFont(Style.LABEL_FONT);
        guestName.setBounds(30, 100, 220, 40);
        add(guestName);

        JTextField guestNameField = new JTextField();
        guestNameField.setFont(Style.INPUT_FONT);
        guestNameField.setBounds(300, 100, 200, 40);
        add(guestNameField);

        // check in
        JLabel checkIn = new JLabel("Check in date (1-29): ");
        checkIn.setFont(Style.LABEL_FONT);
        checkIn.setBounds(30, 160, 220, 40);
        add(checkIn);

        JTextField checkInField = new JTextField();
        checkInField.setFont(Style.INPUT_FONT);
        checkInField.setBounds(300, 160, 200, 40);
        add(checkInField);

        JLabel checkOut = new JLabel("Check out date (2-30): ");
        checkOut.setFont(Style.LABEL_FONT);
        checkOut.setBounds(30, 220, 220, 40);
        add(checkOut);

        JTextField checkOutField = new JTextField();
        checkOutField.setFont(Style.INPUT_FONT);
        checkOutField.setBounds(300, 220, 200, 40);
        add(checkOutField);

        JButton submit = Style.createButton("Submit",
                630, 500, 100, 40, e -> {

                });
        add(submit);

        JButton back = Style.createButton("Back",
                750, 500, 100, 40, e -> controller.switchScreen("Menu"));
        add(back);

    }
}
