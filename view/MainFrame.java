package view;

import controller.Controller;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private CardLayout cardLayout = new CardLayout();
    private JPanel mainContainer = new JPanel();

    private Controller controller;

    public MainFrame(Controller controller) {

        // window size
        this.controller = controller;
        setTitle("Green Property Exchange");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // set up
        mainContainer.setLayout(cardLayout);
        mainContainer.setBackground(Style.BG);

        mainContainer.add(new MainMenuPanel(controller), "Menu");

        // 4 main screen
        mainContainer.add(new CreatePropertyPanel(this), "Create");
        mainContainer.add(new ViewPropertyPanel(controller), "View");
        mainContainer.add(new ManagePropertyPanel(controller), "Manage");
        mainContainer.add(new BookingPanel(controller), "Booking");

        // sub screen for choosing property
        mainContainer.add(new ChoosePropertyViewPanel(controller), "Choose");
        mainContainer.add(new ChoosePropertyManagePanel(controller), "Choosing");
        mainContainer.add(new ChoosePropertyReservationPanel(controller), "Chosen");

        // sub screen for property functions
        mainContainer.add(new CalendarPanel(controller), "Calendar");
        add(mainContainer);
        setVisible(true);
    }

    public void showScreen(String screenName) {
        cardLayout.show(mainContainer, screenName);
    }

    public Controller getController() {
        return controller;
    }

}
