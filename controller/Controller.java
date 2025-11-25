package controller;

import view.MainFrame;

public class Controller {

    private MainFrame mainFrame;

    public Controller() {

        mainFrame = new MainFrame(this);
        mainFrame.setVisible(true);
    }
    public void switchScreen (String name) {
        mainFrame.showScreen(name);
    }

    // ADD YOUR LOGIC HERE
    public void changePropertyName(String newName) {
        System.out.println("Property name changed to: " + newName);
    }

    public void updateBasePrice(double price) {
        System.out.println("Base price updated to: " + price);
    }

    public void deleteReservation(int id) {
        System.out.println("Deleted reservation: " + id);
    }

    public void deleteProperty() {
        System.out.println("Property deleted.");
    }
}
