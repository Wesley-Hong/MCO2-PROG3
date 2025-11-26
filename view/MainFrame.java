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

        // main menu
        mainContainer.add(new MainMenuPanel(controller), "Menu");

        // four main screen
        mainContainer.add(new CreatePropertyPanel(this), "Create");
        mainContainer.add(new ViewPropertyPanel(controller), "View");
        mainContainer.add(new ManagePropertyPanel(controller), "Manage");
        mainContainer.add(new BookingPanel(controller), "Booking");

        // choosing property screen
        mainContainer.add(new ChoosePropertyViewPanel(controller), "Choose");
        mainContainer.add(new ChoosePropertyManagePanel(controller), "Choosing");
        mainContainer.add(new ChoosePropertyReservationPanel(controller), "Chosen");

        // screen for more property functions
        mainContainer.add(new CalendarPanel(controller), "Calendar");
        mainContainer.add(new PropertySummaryPanel(controller), "Summary");
        mainContainer.add(new DateRangePanel(controller), "Availability");
        mainContainer.add(new DateInformationPanel(controller), "DayInformation");
        mainContainer.add(new ReservationDetailsPanel(controller), "ReservationDetails");
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
