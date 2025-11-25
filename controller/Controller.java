package controller;

import model.Property;
import model.PropertyManagement;
import view.MainFrame;

import javax.swing.*;
import java.util.List;

public class Controller {

    private MainFrame mainFrame; // Renamed for clarity
    private PropertyManagement model;

    private String currentPropertyName = null;

    // 1. Constructor only takes the Model initially
    public Controller(PropertyManagement model) {
        this.model = model;
    }

    // 2. Add a Setter to connect the View later
    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void switchScreen(String name) {
        if (mainFrame != null) {
            mainFrame.showScreen(name);
        }
    }

    // get all property info
    public String[] getPropertyNames() {
        return model.getPropertyNames();
    }

    public String[] getFullInformation() {
        return model.getFullInformation();
    }

    public void setSelectedProperty(String name) {
        this.currentPropertyName = name;
        System.out.println("Selected Property: " + name);
    }

    public Property getCurrentProperty() {
        if (currentPropertyName == null) return null;
        return model.getProperty(currentPropertyName);
    }

    // create property
    public void createProperty (String inputName, String typeString) {
        String name = inputName.trim();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame, "Error: Property name cannot by empty.");
            return;
        }

        String propertyName = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();

        int typeID = 1;
        switch (typeString) {
            case "Eco-Apartment": typeID = 1; break;
            case "Sustainable House": typeID = 2; break;
            case "Green Resort": typeID = 3; break;
            case "Eco-Glamping": typeID = 4; break;
        }

        boolean success = model.createProperty(propertyName, typeID);

        if (success) {
            JOptionPane.showMessageDialog(mainFrame, "Property '" + propertyName + "' created successfully.");
            mainFrame.showScreen("Menu");
        }
        else {
            JOptionPane.showMessageDialog(mainFrame, "Error: Name already exists.");
        }
    }


    // new need fix code (too ai)
    public void manageChangeName(String currentName, String newName) {
        if (newName.isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame, "New name cannot be empty.");
            return;
        }

        // Use capitalization logic if needed
        String formattedName = newName.substring(0, 1).toUpperCase() + newName.substring(1);

        if (model.changePropertyName(currentName, formattedName)) {
            JOptionPane.showMessageDialog(mainFrame, "Property renamed to " + formattedName);
            mainFrame.showScreen("Menu");
        } else {
            JOptionPane.showMessageDialog(mainFrame, "Error: Name taken or invalid.");
        }
    }

    public void manageUpdatePrice(String propertyName, String priceStr) {
        try {
            double price = Double.parseDouble(priceStr);
            if (model.updateBasePrice(propertyName, price)) {
                JOptionPane.showMessageDialog(mainFrame, "Price updated successfully.");
                mainFrame.showScreen("Menu");
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Error: Cannot update price (Active reservations or invalid amount).");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(mainFrame, "Error: Invalid price format.");
        }
    }

    public void manageRemoveReservation(String propertyName, String indexStr) {
        try {
            int index = Integer.parseInt(indexStr);
            if (model.removeReservation(propertyName, index)) {
                JOptionPane.showMessageDialog(mainFrame, "Reservation #" + index + " removed.");
                mainFrame.showScreen("Menu");
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Error: Invalid reservation number.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(mainFrame, "Error: Invalid number format.");
        }
    }

    public void manageRemoveProperty(String propertyName) {
        // Confirmation dialog could go here
        if (model.removeProperty(propertyName)) {
            JOptionPane.showMessageDialog(mainFrame, "Property '" + propertyName + "' deleted.");
            mainFrame.showScreen("Menu");
        } else {
            JOptionPane.showMessageDialog(mainFrame, "Error: Cannot delete (Active reservations or not found).");
        }
    }

    // Check if properties exist
    public boolean hasProperties() {
        String[] properties = model.getPropertyNames();
        return properties != null && properties.length > 0;
    }

    // Switch screen with validation
    public void switchScreenWithValidation(String screenName) {
        if (!hasProperties()) {
            JOptionPane.showMessageDialog(mainFrame,
                    "Please create a property first!",
                    "No Properties",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        switchScreen(screenName);
    }

}