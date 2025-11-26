package view;

import controller.Controller;
import model.Property;

import javax.swing.*;
import java.awt.*;

public class BookingPanel extends JPanel {

    private Controller controller;
    private JTextField guestNameField;
    private JTextField checkInField;
    private JTextField checkOutField;

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

        guestNameField = new JTextField();
        guestNameField.setFont(Style.INPUT_FONT);
        guestNameField.setBounds(300, 100, 200, 40);
        add(guestNameField);

        // check in
        JLabel checkIn = new JLabel("Check in date (1-29): ");
        checkIn.setFont(Style.LABEL_FONT);
        checkIn.setBounds(30, 160, 220, 40);
        add(checkIn);

        checkInField = new JTextField();
        checkInField.setFont(Style.INPUT_FONT);
        checkInField.setBounds(300, 160, 200, 40);
        add(checkInField);

        JLabel checkOut = new JLabel("Check out date (2-30): ");
        checkOut.setFont(Style.LABEL_FONT);
        checkOut.setBounds(30, 220, 220, 40);
        add(checkOut);

        checkOutField = new JTextField();
        checkOutField.setFont(Style.INPUT_FONT);
        checkOutField.setBounds(300, 220, 200, 40);
        add(checkOutField);

        JButton submit = Style.createButton("Submit",
                630, 500, 100, 40, e -> createBooking());
        add(submit);

        JButton back = Style.createButton("Back",
                750, 500, 100, 40, e -> controller.switchScreen("Menu"));
        add(back);

    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            clearFields();
        }
    }

    private void createBooking() {
        Property currentProperty = controller.getCurrentProperty();

        if (currentProperty == null) {
            JOptionPane.showMessageDialog(this,
                    "No property selected. Please select a property first.");
            return;
        }

        String guestName = guestNameField.getText().trim();
        String checkInStr = checkInField.getText().trim();
        String checkOutStr = checkOutField.getText().trim();

        if (guestName.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Guest name cannot be empty.");
            return;
        }

        int checkIn;
        try {
            checkIn = Integer.parseInt(checkInStr);
            if (checkIn < 1 || checkIn > 29) {
                JOptionPane.showMessageDialog(this,
                        "Check-in date must be between 1 and 29.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Invalid check-in date format. Please enter a number.");
            return;
        }

        int checkOut;
        try {
            checkOut = Integer.parseInt(checkOutStr);
            if (checkOut < 2 || checkOut > 30) {
                JOptionPane.showMessageDialog(this,
                        "Check-out date must be between 2 and 30.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Invalid check-out date format. Please enter a number.");
            return;
        }

        if (checkIn >= checkOut) {
            JOptionPane.showMessageDialog(this,
                    "Check-out date must be after check-in date.");
            return;
        }

        controller.createBooking(guestName, checkIn, checkOut);
        clearFields();
    }

    private void clearFields() {
        guestNameField.setText("");
        checkInField.setText("");
        checkOutField.setText("");
    }

}
