package view;

import controller.Controller;
import javax.swing.*;

/**
 * The main menu panel for Green Property Exchange
 * It provides navigation to different system which includes
 * property creation, viewing, management, and booking simulation
 */

public class MainMenuPanel extends JPanel {

    private Controller controller;

    /**
     * Constructor for main menu and with navigation buttons
     * @param controller the main application controller that handles
     *                   screen navigation and business logic
     */
    public MainMenuPanel(Controller controller) {
        this.controller = controller;
        setLayout(null);
        setBackground(Style.BG);

        // Title
        JLabel label = new JLabel("Green Property Exchange", SwingConstants.CENTER);
        label.setFont(Style.TITLE_FONT);
        label.setBounds(0, 0, 900, 80);
        add(label);

        // Four main buttons
        JButton b1 = Style.createButton("Create Property",
                340, 100, 220, 60, e -> controller.switchScreen("Create"));
        JButton b2 = Style.createButton("View Property",
                340, 200, 220, 60, e -> controller.switchScreenWithValidation("Choose"));
        JButton b3 = Style.createButton("Manage Property",
                340, 300, 220, 60, e -> controller.switchScreenWithValidation("Choosing"));
        JButton b4 = Style.createButton("Simulate Booking",
                340, 400, 220, 60, e -> controller.switchScreenWithValidation("Chosen"));

        add(b1);
        add(b2);
        add(b3);
        add(b4);
    }

}
