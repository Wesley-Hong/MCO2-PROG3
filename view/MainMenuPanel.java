package view;

import controller.Controller;
import javax.swing.*;
import java.awt.event.ActionListener;

public class MainMenuPanel extends JPanel {

    private Controller controller;

    public MainMenuPanel(Controller controller) {
        this.controller = controller;
        setLayout(null);
        setBackground(Style.BG);

        JLabel label = new JLabel("Green Property Exchange", SwingConstants.CENTER);
        label.setFont(Style.TITLE_FONT);
        label.setBounds(0, 0, 900, 80);
        add(label);

        // Now we link the buttons to the real screens
        JButton b1 = Style.createButton("Create Property",
                340, 100, 220, 60, e -> controller.switchScreen("Create"));
        JButton b2 = Style.createButton("View Property",
                340, 200, 220, 60, e -> controller.switchScreenWithValidation("Choose"));
        JButton b3 = Style.createButton("Manage Property",
                340, 300, 220, 60, e -> controller.switchScreenWithValidation("Choosing"));
        JButton b4 = Style.createButton("Simulate Booking",
                340, 400, 220, 60, e -> controller.switchScreenWithValidation("Booking"));

        add(b1);
        add(b2);
        add(b3);
        add(b4);

    }

}
