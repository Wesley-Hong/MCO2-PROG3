package view;

import controller.Controller;
import model.Property;

import javax.swing.*;
import java.awt.*;

/**
 * Panel for managing property operations
 * It contains change property name, update property,
 * remove reservation, remove property, set environmental impact modifier
 */
public class ManagePropertyPanel extends JPanel{

    private Controller controller;
    private JPanel smallBox;
    private JLabel oldPropertyName;

    /**
     * Constructor for managing property
     * @param controller the main application controller that handles
     *                       screen navigation and business logic
     */
    public ManagePropertyPanel(Controller controller) {
        this.controller = controller;
        setLayout(null);
        setBackground(Style.BG);

        // Title
        JLabel label = new JLabel("Manage Property", SwingConstants.CENTER);
        label.setFont(Style.TITLE_FONT);
        label.setBounds(0, 0, 900, 80);
        add(label);

        // Buttons
        JButton b1 = Style.createButton("Change Property Name",
                50, 100, 240, 50, e -> changePropertyName());
        JButton b2 = Style.createButton("Update Base Price",
                50, 180, 240, 50, e -> updateBasePrice());
        JButton b3 = Style.createButton("Remove Reservation",
                50, 260, 240, 50, e -> removeReservation());
        JButton b4 = Style.createButton("Remove Property",
                50, 340, 240, 50, e -> removeProperty());
        JButton b5 = Style.createButton("Environment Modifier",
                50, 410, 240, 50, e -> environmentImpact());

        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);

        JButton back = Style.createButton("Back",
                750, 500, 100, 40, e -> controller.switchScreen("Menu"));
        add(back);

        smallBox = new JPanel();
        smallBox.setBounds(350, 100, 500, 360);
        smallBox.setBackground(Color.decode("#68BA7F"));
        smallBox.setLayout(null);
        smallBox.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        add(smallBox);

    }

    /**
     * For clearing the content of the box
     * @param visible  true to make the component visible; false to
     *          make it invisible
     */
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);

        if (visible && smallBox != null) {
            smallBox.removeAll();
            smallBox.revalidate();
            smallBox.repaint();
        }
    }

    /**
     * Display property name and ask user to change property
     */
    public void changePropertyName() {

        smallBox.removeAll();

        Property currentProperty = controller.getCurrentProperty();

        JLabel name = new JLabel("Property name: ");
        name.setFont(Style.LABEL_FONT);
        name.setBounds(30,10, 280,50);
        smallBox.add(name);

        oldPropertyName = new JLabel(currentProperty.getName());
        oldPropertyName.setFont(Style.LABEL_FONT);
        oldPropertyName.setBounds(285,10, 200,50);
        smallBox.add(oldPropertyName);

        JLabel label = new JLabel("Change property name to:", SwingConstants.CENTER);
        label.setFont(Style.LABEL_FONT);
        label.setBounds(10, 60, 280, 50);
        smallBox.add(label);

        JTextField answer = new JTextField();
        answer.setFont(Style.INPUT_FONT);
        answer.setBounds(285, 60, 200, 50);
        smallBox.add(answer);

        JButton submit = Style.createButton("Submit",
                350, 300, 100, 40, e -> {
                String newName = answer.getText().trim();
                if (newName.isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                            "Property name cannot be empty.");
                    return;
                }
                controller.manageChangeName(currentProperty.getName(), answer.getText());
                oldPropertyName.setText(controller.getCurrentProperty().getName());
                });

        smallBox.add(submit);

        smallBox.revalidate();
        smallBox.repaint();

    }

    /**
     * Display original base price and ask user to change base price
     */
    public void updateBasePrice() {

        smallBox.removeAll();

        Property currentProperty = controller.getCurrentProperty();

        JLabel name = new JLabel("Current Base Price: ", SwingConstants.CENTER);
        name.setFont(Style.LABEL_FONT);
        name.setBounds(0,10, 280,50);
        smallBox.add(name);

        oldPropertyName = new JLabel(String.format("₱%.2f", currentProperty.getBasePrice()));
        oldPropertyName.setFont(Style.LABEL_FONT);
        oldPropertyName.setBounds(285,10, 200,50);
        smallBox.add(oldPropertyName);

        JLabel label = new JLabel("Update Base Price:", SwingConstants.CENTER);
        label.setFont(Style.LABEL_FONT);
        label.setBounds(0, 60, 280, 50);
        smallBox.add(label);

        JTextField answer = new JTextField();
        answer.setFont(Style.INPUT_FONT);
        answer.setBounds(285, 60, 200, 50);
        smallBox.add(answer);

        JButton submit = Style.createButton("Submit",
                350, 300, 100, 40, e -> {
                String input = answer.getText().trim();

                if (input.isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                            "Price cannot be empty.");
                    return;
                }

                controller.manageUpdatePrice(currentProperty.getName(), input);
                answer.setText("");

                });

        smallBox.add(submit);

        smallBox.revalidate();
        smallBox.repaint();

    }

    /**
     * Remove reservation
     */
    public void removeReservation() {

        smallBox.removeAll();

        Property currentProperty = controller.getCurrentProperty();

        JLabel name = new JLabel("Property name: ");
        name.setFont(Style.LABEL_FONT);
        name.setBounds(30,10, 280,50);
        smallBox.add(name);

        oldPropertyName = new JLabel(currentProperty.getName());
        oldPropertyName.setFont(Style.LABEL_FONT);
        oldPropertyName.setBounds(285,10, 200,50);
        smallBox.add(oldPropertyName);

        JLabel title = new JLabel("Total Reservation #:", SwingConstants.CENTER);
        title.setFont(Style.LABEL_FONT);
        title.setBounds(0, 10, 280, 50);

        JLabel label = new JLabel("Reservation to delete:", SwingConstants.CENTER);
        label.setFont(Style.LABEL_FONT);
        label.setBounds(0, 60, 280, 50);

        JTextField answer = new JTextField();
        answer.setFont(Style.INPUT_FONT);
        answer.setBounds(285, 60, 200, 50);

        JButton submit = Style.createButton("Submit",
                350, 300, 100, 40, e -> {
                controller.manageRemoveReservation(currentProperty.getName(), answer.getText());
                });

        smallBox.add(submit);

        smallBox.add(title);
        smallBox.add(label);
        smallBox.add(answer);

        smallBox.revalidate();
        smallBox.repaint();

    }

    /**
     * Remove property that has no active reservation
     */
    public void removeProperty() {

        smallBox.removeAll();

        Property currentProperty = controller.getCurrentProperty();

        JLabel name = new JLabel("Property name: ");
        name.setFont(Style.LABEL_FONT);
        name.setBounds(30,10, 280,50);
        smallBox.add(name);

        oldPropertyName = new JLabel(currentProperty.getName());
        oldPropertyName.setFont(Style.LABEL_FONT);
        oldPropertyName.setBounds(285,10, 200,50);
        smallBox.add(oldPropertyName);

        JLabel label = new JLabel("Are you sure to the delete property?", SwingConstants.CENTER);
        label.setFont(Style.LABEL_FONT);
        label.setBounds(30, 60, 370, 50);

        JButton yes = Style.createButton("Yes",
                100, 180, 100, 40, e -> {
                controller.manageRemoveProperty(currentProperty.getName());
                });

        JButton no = Style.createButton("No",
                270, 180, 100, 40, e -> {
                smallBox.removeAll();
                smallBox.revalidate();
                smallBox.repaint();
                });

        smallBox.add(yes);
        smallBox.add(no);

        smallBox.add(label);

        smallBox.revalidate();
        smallBox.repaint();
    }

    

    public void environmentImpact() {

        smallBox.removeAll();

        Property currentProperty = controller.getCurrentProperty();

        JLabel name = new JLabel("Property name: ");
        name.setFont(Style.LABEL_FONT);
        name.setBounds(30,10, 280,50);
        smallBox.add(name);

        oldPropertyName = new JLabel(currentProperty.getName());
        oldPropertyName.setFont(Style.LABEL_FONT);
        oldPropertyName.setBounds(285,10, 200,50);
        smallBox.add(oldPropertyName);

        JLabel label = new JLabel("Date to modify (1-30):", SwingConstants.CENTER);
        label.setFont(Style.LABEL_FONT);
        label.setBounds(10, 60, 280, 50);
        smallBox.add(label);

        JTextField answer = new JTextField();
        answer.setFont(Style.INPUT_FONT);
        answer.setBounds(285, 60, 200, 50);
        smallBox.add(answer);

        JLabel value = new JLabel("Date to modify (1-30):", SwingConstants.CENTER);
        value.setFont(Style.LABEL_FONT);
        value.setBounds(10, 110, 280, 50);
        smallBox.add(value);

        JTextField valueField = new JTextField();
        valueField.setFont(Style.INPUT_FONT);
        valueField.setBounds(285, 110, 200, 50);
        smallBox.add(valueField);

        JButton submit = Style.createButton("Submit",
                350, 300, 100, 40, e ->{
                double multiplier = Double.parseDouble(valueField.getText());
                int date = Integer.parseInt(answer.getText());
                controller.manageEnvironmentModifier(
                        currentProperty.getName(), date, multiplier);
                });

        smallBox.add(submit);
        smallBox.revalidate();
        smallBox.repaint();

    }


}
