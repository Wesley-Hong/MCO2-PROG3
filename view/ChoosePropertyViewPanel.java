package view;

import controller.Controller;
import javax.swing.*;

/**
 * Panel for choosing property to view
 * It displays the set of property names and its corresponding
 * property type
 */

public class ChoosePropertyViewPanel extends JPanel {

    private Controller controller;
    private JList<String> propertyList;
    private JTextField nameField;

    /**
     * Constructor for choosing property for view property
     * @param controller the main application controller that handles
     *                       screen navigation and business logic
     */
    public ChoosePropertyViewPanel(Controller controller) {
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
                    // selected property
                    String name = extractPropertyName(selected);
                    controller.setSelectedProperty(name);
                    controller.switchScreen("View");
                } else { // no property selected
                    JOptionPane.showMessageDialog(this, "Please select a property first.");
                }
        });
        add(submit);

        JButton back = Style.createButton("Back",
                750, 500, 100, 40, e -> controller.switchScreen("Menu"));
        add(back);

    }

    /**
     * Refresh the property list with data from controller
     * Updates the JList component with lastest available property names
     */
    public void refreshList() {
        if (controller != null) {
            String[] names = controller.getPropertyNames();
            propertyList.setListData(names);
        }
    }

    /**
     * Override setVisible to refresh the property list
     * @param visible  true to make the component visible; false to
     *          make it invisible
     */
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            refreshList();
        }
    }

    /**
     * Extract property name from formatted string
     * @param fullText the formatted string
     * @return the property name only
     */
    private String extractPropertyName(String fullText) {
        int parenIndex = fullText.indexOf('(');
        if (parenIndex > 0) {
            return fullText.substring(0, parenIndex).trim();
        }
        return fullText.trim();
    }

}
