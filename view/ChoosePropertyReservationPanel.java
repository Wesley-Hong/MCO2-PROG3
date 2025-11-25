package view;

import controller.Controller;

import javax.swing.*;

public class ChoosePropertyReservationPanel extends JPanel {

    private Controller controller;
    private JList<String> propertyList;
    private JTextField nameField;

    public ChoosePropertyReservationPanel(Controller controller) {
        this.controller = controller;
        setLayout(null);
        setBackground(Style.BG);

        // Title
        JLabel label = new JLabel("Choose Property", SwingConstants.CENTER);
        label.setFont(Style.TITLE_FONT);
        label.setBounds(0, 0, 900, 80);
        add(label);

        JLabel question = new JLabel("Choose Property:");
        question.setFont(Style.LABEL_FONT);
        question.setBounds(30, 100, 200, 50);
        add(question);

        nameField = new JTextField();
        nameField.setFont(Style.INPUT_FONT);
        nameField.setBounds(230, 100, 300,50);
        nameField.setEditable(false);
        add(nameField);

        propertyList = new JList<>();
        propertyList.setFont(Style.INPUT_FONT);

        // Add listener: When user clicks a name in the list, put it in the text field
        propertyList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selected = propertyList.getSelectedValue();
                if (selected != null) {
                    nameField.setText(selected);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(propertyList);
        scrollPane.setBounds(30, 160, 500, 350);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);

        JButton submit = Style.createButton("Submit",
                630, 500, 100, 40, e -> {
                    String selected = nameField.getText();
                    if (selected != null && !selected.isEmpty()) {
                        // 1. Tell Controller which property we chose
                        controller.setSelectedProperty(selected);
                        // 2. Go to the View Property Screen (Assuming you have one named "View")
                        // If you don't have a "View" screen yet, change "View" to "Menu" or create one
                        controller.switchScreen("Booking");
                    }
                });
        add(submit);

        JButton back = Style.createButton("Back",
                750, 500, 100, 40, e -> controller.switchScreen("Menu"));
        add(back);

    }

    public void refreshList() {
        if (controller != null) {
            String[] names = controller.getPropertyNames();
            propertyList.setListData(names);
        }
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            refreshList();
        }
    }
}
