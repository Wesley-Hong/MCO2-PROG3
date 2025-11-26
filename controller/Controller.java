package controller;

import model.Property;
import model.PropertyManagement;
import model.Reservation;
import view.MainFrame;

import javax.swing.*;
import java.util.List;

public class Controller {

    private MainFrame mainFrame; // Renamed for clarity
    private PropertyManagement model;
    private Property currentProperty;

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

    // view property
    public void setSelectedProperty(String propertyName) {
        currentProperty = model.findPropertyByName(propertyName);
    }

    public Property getCurrentProperty() {
        return currentProperty;
    }


    public String getDateInformationString(String dateStr) {
        Property currentProperty = getCurrentProperty();

        if (currentProperty == null) {
            JOptionPane.showMessageDialog(mainFrame,
                    "No property selected.");
            return null;
        }

        // Validate and parse date
        int dateNumber;
        try {
            dateNumber = Integer.parseInt(dateStr.trim());
            if (dateNumber < 1 || dateNumber > 30) {
                JOptionPane.showMessageDialog(mainFrame,
                        "Date must be between 1 and 30.");
                return null;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(mainFrame,
                    "Invalid date format. Please enter a number.");
            return null;
        }

        // Get date information string from model
        return model.getDateInformation(currentProperty.getName(), dateNumber);
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

    public void createBooking (String guestName, int checkIn, int checkOut) {
        Property currentProperty = getCurrentProperty();

        if (currentProperty == null) {
            JOptionPane.showMessageDialog(mainFrame, "No property selected");
            return;
        }

        if (guestName == null || guestName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame,
                    "Guest name should not be empty. Please try again.");
            return;
        }

        if (!model.isDateRangeAvailable(currentProperty.getName(), checkIn, checkOut)) {
            JOptionPane.showMessageDialog(mainFrame,
                    "Booking failed: invalid or unavailable dates.");
            return;
        }

        Reservation reservation = model.createBooking(
                currentProperty.getName(), guestName, checkIn, checkOut);

        if (reservation != null) {
            // Show success message with booking details
            String message = String.format(
                    "Booking successful for %s\n\n" +
                            "Check-in: Day %d\n" +
                            "Check-out: Day %d\n" +
                            "Total: ₱%.2f\n\n" +
                            "Price Breakdown:\n%s",
                    reservation.getGuestName(),
                    checkIn,
                    checkOut,
                    reservation.getTotalPrice(),
                    reservation.getPriceBreakdownString()
            );
            JOptionPane.showMessageDialog(mainFrame, message);

            // Return to View screen
            mainFrame.showScreen("Menu");
        } else {
            JOptionPane.showMessageDialog(mainFrame,
                    "Booking failed: Could not create reservation.");
        }
    }

    public String getDateRangeString(String start, String end) {
        Property currentProperty = getCurrentProperty();

        if (currentProperty == null) {
            JOptionPane.showMessageDialog(mainFrame,
                    "No property selected.");
            return null;
        }

        int startDate;
        try {
            startDate = Integer.parseInt(start.trim());
            if (startDate < 1 || startDate > 29) {
                JOptionPane.showMessageDialog(mainFrame,
                        "Check-in date must be between 1 and 29.");
                return null;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(mainFrame,
                    "Invalid check-in date format. Please enter a number.");
            return null;
        }

        int endDate;
        try {
            endDate = Integer.parseInt(end.trim());
            if (endDate < 2 || endDate > 30) {
                JOptionPane.showMessageDialog(mainFrame,
                        "Check-out date must be between 2 and 30.");
                return null;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(mainFrame,
                    "Invalid check-out date format. Please enter a number.");
            return null;
        }

        if (startDate >= endDate) {
            JOptionPane.showMessageDialog(mainFrame,
                    "Check-out date must be after check-in date.");
            return null;
        }

        return model.getDateRange(currentProperty.getName(), startDate, endDate);

    }

    public String getReservationDetails() {
        Property currentProperty = getCurrentProperty();

        if (currentProperty == null) {
            JOptionPane.showMessageDialog(mainFrame,
                    "No property selected.");
            return null;
        }

        return model.getReservationInformation(currentProperty.getName());
    }

    public void manageEnvironmentModifier(String name, int date, double modifier) {
        try {
            if (model.setEnvironmentalModifier(name, date, modifier)) {
                JOptionPane.showMessageDialog(mainFrame, "Date " + date + " is " + modifier + " * base price");
                mainFrame.showScreen("Menu");
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Error: Invalid date number.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(mainFrame, "Error: Invalid number format.");
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