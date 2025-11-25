package view;

import controller.Controller;
import model.Property;

import javax.swing.*;
import java.awt.*;

public class ManagePropertyPanel extends JPanel{

    private Controller controller;
    private JPanel smallBox;
    public Property currentProperty;

    public ManagePropertyPanel(Controller controller) {
        this.controller = controller;
        currentProperty = controller.getCurrentProperty();
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

        JLabel label = new JLabel("Change property name to:", SwingConstants.CENTER);
        label.setFont(Style.LABEL_FONT);
        label.setBounds(0, 10, 280, 50);

        JTextField answer = new JTextField();
        answer.setFont(Style.INPUT_FONT);
        answer.setBounds(285, 10, 200, 50);

        JButton submit = Style.createButton("Submit",
                350, 300, 100, 40, e -> {
                controller.manageChangeName(currentProperty.getName(), answer.getText());
                });

        smallBox.add(submit);

        smallBox.add(label);
        smallBox.add(answer);

        smallBox.revalidate();
        smallBox.repaint();

    }

    public void updateBasePrice() {

        smallBox.removeAll();

        JLabel label = new JLabel("Update Base Price:", SwingConstants.CENTER);
        label.setFont(Style.LABEL_FONT);
        label.setBounds(0, 10, 280, 50);

        JTextField answer = new JTextField();
        answer.setFont(Style.INPUT_FONT);
        answer.setBounds(285, 10, 200, 50);

        JButton submit = Style.createButton("Submit",
                350, 300, 100, 40, e -> {
                controller.manageUpdatePrice(currentProperty.getName(), answer.getText());
                });

        smallBox.add(submit);

        smallBox.add(label);
        smallBox.add(answer);

        smallBox.revalidate();
        smallBox.repaint();

    }

    public void removeReservation() {

        smallBox.removeAll();

        JLabel title = new JLabel("Total Reservation ##:", SwingConstants.CENTER);
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

    public void removeProperty() {

        smallBox.removeAll();

        JLabel label = new JLabel("Are you sure to the delete property?", SwingConstants.CENTER);
        label.setFont(Style.LABEL_FONT);
        label.setBounds(30, 10, 370, 50);

        JButton yes = Style.createButton("Yes",
                100, 120, 100, 40, e -> controller.manageRemoveProperty(currentProperty.getName()));

        JButton no = Style.createButton("No",
                270, 120, 100, 40, e -> {
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
