package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class ReservationDetailsPanel extends JPanel {

    private Controller controller;
    private JPanel displayBox;
    private JTextArea infoTextArea;
    private JScrollPane scrollPane;

    public ReservationDetailsPanel(Controller controller) {
        this.controller = controller;
        setLayout(null);
        setBackground(Style.BG);

        // Title
        JLabel label = new JLabel("Reservation Details", SwingConstants.CENTER);
        label.setFont(Style.TITLE_FONT);
        label.setBounds(0, 0, 900, 80);
        add(label);

        // White display box
        displayBox = new JPanel();
        displayBox.setBounds(50, 100, 800, 360);
        displayBox.setBackground(Color.WHITE);
        displayBox.setLayout(null);
        displayBox.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        add(displayBox);

        // Text area for displaying reservation info
        infoTextArea = new JTextArea();
        infoTextArea.setFont(new Font("Courier New", Font.PLAIN, 14));
        infoTextArea.setEditable(false);
        infoTextArea.setBackground(Color.WHITE);
        infoTextArea.setLineWrap(true);
        infoTextArea.setWrapStyleWord(true);

        // Add scroll pane
        scrollPane = new JScrollPane(infoTextArea);
        scrollPane.setBounds(10, 10, 780, 340);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        displayBox.add(scrollPane);

        JButton back = Style.createButton("Back",
                750, 500, 100, 40, e -> controller.switchScreen("View"));
        add(back);
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            loadReservationDetails();
        }
    }

    private void loadReservationDetails() {
        String info = controller.getReservationDetails();

        if (info != null) {
            infoTextArea.setText(info);
            // Scroll to top
            infoTextArea.setCaretPosition(0);
        } else {
            infoTextArea.setText("Error: Could not retrieve reservation information.");
        }
    }
}