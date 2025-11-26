package view;

import controller.Controller;
import model.Property;

import javax.swing.*;

public class PropertySummaryPanel extends JPanel {

    private Controller controller;
    private JTextField propertyNameField;
    private JTextField typeField;
    private JTextField dateField;
    private JTextField earningField;

    public PropertySummaryPanel(Controller controller) {
        this.controller = controller;
        setLayout(null);
        setBackground(Style.BG);

        // Title
        JLabel label = new JLabel("Property Summary", SwingConstants.CENTER);
        label.setFont(Style.TITLE_FONT);
        label.setBounds(0, 0, 900, 80);
        add(label);

        // name
        JLabel propertyName = new JLabel("Property name: ");
        propertyName.setFont(Style.LABEL_FONT);
        propertyName.setBounds(30, 100, 220, 40);
        add(propertyName);

        propertyNameField = new JTextField();
        propertyNameField.setFont(Style.INPUT_FONT);
        propertyNameField.setBounds(300, 100, 200, 40);
        propertyNameField.setEditable(false);
        add(propertyNameField);

        // property type
        JLabel type = new JLabel("Property type: ");
        type.setFont(Style.LABEL_FONT);
        type.setBounds(30, 160, 220, 40);
        add(type);

        typeField = new JTextField();
        typeField.setFont(Style.INPUT_FONT);
        typeField.setBounds(300, 160, 200, 40);
        typeField.setEditable(false);
        add(typeField);

        // available dates
        JLabel date = new JLabel("Total available dates: ");
        date.setFont(Style.LABEL_FONT);
        date.setBounds(30, 220, 220, 40);
        add(date);

        dateField = new JTextField();
        dateField.setFont(Style.INPUT_FONT);
        dateField.setBounds(300, 220, 200, 40);
        dateField.setEditable(false);
        add(dateField);

        // earnings
        JLabel earning = new JLabel("Estimated earnings: ");
        earning.setFont(Style.LABEL_FONT);
        earning.setBounds(30, 280, 220, 40);
        add(earning);

        earningField = new JTextField();
        earningField.setFont(Style.INPUT_FONT);
        earningField.setBounds(300, 280, 200, 40);
        earningField.setEditable(false);
        add(earningField);

        JButton back = Style.createButton("Back",
                750, 500, 100, 40, e -> controller.switchScreen("View"));
        add(back);

    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            updateSummary();
        }
    }

    private void updateSummary() {
        Property currentProperty = controller.getCurrentProperty();
        propertyNameField.setText(currentProperty.getName());
        typeField.setText(currentProperty.getType());
        dateField.setText(String.valueOf(currentProperty.getAvailableDateCount()));
        earningField.setText(String.format("₱%.2f", currentProperty.getEstimatedEarnings()));
    }
}
