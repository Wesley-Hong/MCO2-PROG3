package view;

import javax.swing.*;
import java.awt.*;

public class ManagePropertyPanel extends JPanel{

    private MainFrame mainFrame;
    private JPanel smallBox;

    public ManagePropertyPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
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

        JButton submit = Style.createButton("Submit",
                630, 500, 100, 40, e -> mainFrame.showScreen("Menu"));
        add(submit);

        JButton back = Style.createButton("Back",
                750, 500, 100, 40, e -> mainFrame.showScreen("Menu"));
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
        label.setBounds(0, 10, 350, 50);

        smallBox.add(label);

        smallBox.revalidate();
        smallBox.repaint();

    }



}
