package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class DateRangePanel extends JPanel {

    private Controller controller;
    private JTextField startDateField;
    private JTextField endDateField;
    private JPanel displayBox;
    private JTextArea infoTextArea;

    public DateRangePanel(Controller controller) {
        this.controller = controller;
        setLayout(null);
        setBackground(Style.BG);

        // Title
        JLabel label = new JLabel("Date Range", SwingConstants.CENTER);
        label.setFont(Style.TITLE_FONT);
        label.setBounds(0, 0, 900, 80);
        add(label);

        // Check in
        JLabel startLabel = new JLabel("Check-in date (1-29): ");
        startLabel.setFont(Style.LABEL_FONT);
        startLabel.setBounds(30, 100, 220, 40);
        add(startLabel);

        startDateField = new JTextField();
        startDateField.setFont(Style.INPUT_FONT);
        startDateField.setBounds(250, 100, 100, 40);
        add(startDateField);

        // Check out
        JLabel endLabel = new JLabel("Check-out date (2-30): ");
        endLabel.setFont(Style.LABEL_FONT);
        endLabel.setBounds(30, 160, 220, 40);
        add(endLabel);

        endDateField = new JTextField();
        endDateField.setFont(Style.INPUT_FONT);
        endDateField.setBounds(250, 160, 100, 40);
        add(endDateField);

// Enter button
        JButton enter = Style.createButton("Enter",
                380, 130, 100, 40, e -> showDateRange());
        add(enter);

        // White display box
        displayBox = new JPanel();
        displayBox.setBounds(50, 240, 800, 220);
        displayBox.setBackground(Color.WHITE);
        displayBox.setLayout(null);
        displayBox.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        add(displayBox);

        // Text area for displaying info
        infoTextArea = new JTextArea();
        infoTextArea.setFont(new Font("Arial", Font.PLAIN, 20));
        infoTextArea.setEditable(false);
        infoTextArea.setBackground(Color.WHITE);
        infoTextArea.setBounds(20, 20, 760, 180);
        displayBox.add(infoTextArea);

        JButton back = Style.createButton("Back",
                750, 500, 100, 40, e -> controller.switchScreen("View"));
        add(back);

    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            clearDisplay();
            startDateField.setText("");
            endDateField.setText("");
        }
    }

    private void showDateRange() {
        String startStr = startDateField.getText().trim();
        String endStr = endDateField.getText().trim();

        if (startStr.isEmpty() || endStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter both check-in and check-out dates.");
            return;
        }

        // Get the formatted info string from controller/model
        String info = controller.getDateRangeString(startStr, endStr);

        if (info != null) {
            infoTextArea.setText(info);
        } else {
            infoTextArea.setText("Error: Could not retrieve date range information.");
        }
    }

    private void clearDisplay() {
        infoTextArea.setText("");
    }




}
