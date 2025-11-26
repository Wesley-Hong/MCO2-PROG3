package view;

import controller.Controller;
import javax.swing.*;
import java.awt.*;

/**
 * Panel for displaying date information
 */
public class DateInformationPanel extends JPanel {

    private Controller controller;
    private JTextField answer;
    private JPanel displayBox;
    private JTextField displayInfo;

    /**
     * Constructor for displaying date information
     * @param controller the main application controller that handles
     *                   screen navigation and business logic
     */
    public DateInformationPanel(Controller controller) {
        this.controller = controller;
        setLayout(null);
        setBackground(Style.BG);

        // Title
        JLabel label = new JLabel("Date Information", SwingConstants.CENTER);
        label.setFont(Style.TITLE_FONT);
        label.setBounds(0, 0, 900, 80);
        add(label);

        // Input section
        JLabel label1 = new JLabel("Enter date: (1-30): ");
        label1.setFont(Style.LABEL_FONT);
        label1.setBounds(30, 100, 200, 40);
        add(label1);

        answer = new JTextField();
        answer.setFont(Style.INPUT_FONT);
        answer.setBounds(230, 100, 40, 40);
        add(answer);

        JButton enter = Style.createButton("Enter",
                280, 100, 100, 40, e -> showDateInfo());
        add(enter);

        // Display box
        displayBox = new JPanel();
        displayBox.setBounds(30, 150, 600, 300);
        displayBox.setBackground(Color.WHITE);
        displayBox.setLayout(null);
        displayBox.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        add(displayBox);

        displayInfo = new JTextField();
        displayInfo.setFont(Style.INPUT_FONT);
        displayInfo.setEditable(false);
        displayInfo.setBackground(Color.WHITE);
        displayInfo.setBounds(20, 20, 550, 250);
        displayBox.add(displayInfo);

        JButton back = Style.createButton("Back",
                750, 500, 100, 40, e -> controller.switchScreen("View"));
        add(back);
    }

    /**
     * Overrides setVisible to rest input field
     * @param visible  true to make the component visible; false to
     *          make it invisible
     */
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            clearDisplay();
            answer.setText("");
        }
    }

    /**
     * Processing the input data
     */
    private void showDateInfo() {
        String dateStr = answer.getText().trim();

        if (dateStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a date.");
            return;
        }

        // Get the formatted info string from controller/model
        String info = controller.getDateInformationString(dateStr);

        if (info != null) {
            displayInfo.setText(info);
        } else {
            displayInfo.setText("Error: Could not retrieve date information.");
        }

    }

    /**
     * Clear the display
     */
    private void clearDisplay() {
        displayInfo.setText("");
    }

}
