package view;

import javax.swing.*;
import java.awt.*;

public class CreatePropertyPanel extends JPanel {

    private MainFrame mainFrame;

    public CreatePropertyPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(null);
        setBackground(Style.BG);

        // Title
        JLabel label = new JLabel("Create Property", SwingConstants.CENTER);
        label.setFont(Style.TITLE_FONT);
        label.setBounds(0, 0, 900, 80);
        add(label);

        // -- Form Area --
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 20));
        formPanel.setBounds(100, 150, 400, 100);
        formPanel.setBackground(Style.BG);

        JLabel nameLabel = new JLabel("Property Name:");
        nameLabel.setFont(Style.LABEL_FONT);
        JTextField nameField = new JTextField();
        nameField.setFont(Style.INPUT_FONT);

        JLabel typeLabel = new JLabel("Property Type:");
        typeLabel.setFont(Style.LABEL_FONT);
        String[] types = {"Eco-Apartment", "Sustainable House", "Green Resort", "Eco-Glamping"};
        JComboBox<String> typeBox = new JComboBox<>(types);
        typeBox.setFont(Style.INPUT_FONT);

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(typeLabel);
        formPanel.add(typeBox);
        add(formPanel);

        // -- Notes Area --
        JTextArea notes = new JTextArea(
                "Price Rate: \n" +
                        "Eco-Apartment: Base Price \n" +
                        "Sustainable House: Base Price * 1.2\n" +
                        "Green Resort: Base Price * 1.35\n" +
                        "Eco-Glamping: Base Price * 1.5\n"
        );
        notes.setBackground(Style.BG);
        notes.setFont(Style.LABEL_FONT);
        notes.setBounds(100, 300, 400, 150);
        notes.setEditable(false); // Make it read-only
        add(notes);

        // -- Buttons --
        JButton submit = Style.createButton("Submit",
                630, 500, 100, 40, e -> mainFrame.showScreen("Menu"));
        add(submit);

        JButton back = Style.createButton("Back",
                750, 500, 100, 40, e -> mainFrame.showScreen("Menu"));
        add(back);
    }
}