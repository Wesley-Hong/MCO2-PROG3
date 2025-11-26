package view;

import controller.Controller;
import model.Property;

import javax.swing.*;
import java.awt.*;

public class ManagePropertyPanel extends JPanel{

    private Controller controller;
    private JPanel smallBox;

    private JLabel oldPropertyName;

    public ManagePropertyPanel(Controller controller) {
        this.controller = controller;
        setLayout(null);
        setBackground(Style.BG);

        // Title
        JLabel label = new JLabel("Manage Property", SwingConstants.CENTER);
        label.setFont(Style.TITLE_FONT);
        label.setBounds(0, 0, 900, 80);
        add(label);

        JButton b1 = Style.createButton("Change Property Name",
                80, 100, 240, 60, e -> changePropertyName());
        JButton b2 = Style.createButton("Update Base Price",
                80, 200, 240, 60, e -> updateBasePrice());
        JButton b3 = Style.createButton("Remove Reservation",
                80, 300, 240, 60, e -> removeReservation());
        JButton b4 = Style.createButton("Remove Property",
                80, 400, 240, 60, e -> removeProperty());

        add(b1);
        add(b2);
        add(b3);
        add(b4);

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

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);

        if (visible && smallBox != null) {
            smallBox.removeAll();
            smallBox.revalidate();
            smallBox.repaint();
        }
    }

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
                //controller.manageRemoveReservation(currentProperty.getName(), answer.getText());
                });

        smallBox.add(submit);

        smallBox.add(title);
        smallBox.add(label);
        smallBox.add(answer);

        smallBox.revalidate();
        smallBox.repaint();

    }

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

                });

        JButton no = Style.createButton("No",
                270, 180, 100, 40, e -> {
                controller.manageRemoveProperty(currentProperty.getName());
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


}
