package controller;

import model.Property;
import model.PropertyManagement;
import model.Reservation;
import view.MainFrame;
import javax.swing.*;

/**
 * It handles user input, validates date, coordinates interaction between
 * model (Property Management) and view (Main Frame)
 */

public class Controller {

    private MainFrame mainFrame; // Renamed for clarity
    private PropertyManagement model;
    private Property currentProperty;

    /**
     * Constructor for controller with specified model to use
     * @param model to control the property management
     */
    public Controller(PropertyManagement model) {
        this.model = model;
    }

    /**
     * Setting up the main frame for the controller
     * @param mainFrame main frame view
     */
    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    /**
     * Switching the main frame to display specified screen
     * @param name the name of the screen to display
     */
    public void switchScreen(String name) {
        if (mainFrame != null) {
            mainFrame.showScreen(name);
        }
    }

    /**
     * Get all property names and type
     * @return array of formatted string of property name and type
     */
    public String[] getPropertyNames() {
        return model.getPropertyNames();
    }

    /**
     * Get all property and its information
     * @return array of formatted string of property and its information
     */
    public String[] getFullInformation() {
        return model.getFullInformation();
    }

    /**
     * Create property with the specified name and type
     * @param inputName property name
     * @param typeString property type
     */
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

    /**
     * Sets the currently selected property for operation
     * @param propertyName the name of property being selected
     */
    public void setSelectedProperty(String propertyName) {
        currentProperty = model.findPropertyByName(propertyName);
    }

    /**
     * Gets the currently selected property
     * @return the selected property object
     */
    public Property getCurrentProperty() {
        return currentProperty;
    }

    /**
     * Get the detailed information for specific date on the current property
     * @param date number 1 to 30
     * @return formatted string with date information
     */
    public String getDateInformationString(String date) {
        Property currentProperty = getCurrentProperty();

        if (currentProperty == null) {
            JOptionPane.showMessageDialog(mainFrame,
                    "No property selected.");
            return null;
        }

        // Validate and parse date
        int dateNumber;
        try {
            dateNumber = Integer.parseInt(date.trim());
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

    /**
     * Manage the process of changing a property's name
     * @param currentName current name of the property
     * @param newName new name of the property
     */
    public void manageChangeName(String currentName, String newName) {
        if (newName.isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame, "New name cannot be empty.");
            return;
        }

        // Use capitalization logic
        String formattedName = newName.substring(0, 1).toUpperCase() + newName.substring(1);

        if (model.changePropertyName(currentName, formattedName)) {
            JOptionPane.showMessageDialog(mainFrame, "Property renamed to " + formattedName);
            mainFrame.showScreen("Menu");
        } else {
            JOptionPane.showMessageDialog(mainFrame, "Error: Name taken or invalid.");
        }
    }

    /**
     * Manages updating the base price of property
     * @param propertyName name of the property
     * @param priceStr new price as string
     */
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

    /**
     * Manages removing reservation from a property
     * @param propertyName the name of the property
     * @param indexStr index of the reservation
     */
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

    /**
     * Manage remove property from the system
     * @param propertyName the name of the property to remove
     */
    public void manageRemoveProperty(String propertyName) {

        if (model.removeProperty(propertyName)) {
            JOptionPane.showMessageDialog(mainFrame, "Property '" + propertyName + "' deleted.");
            mainFrame.showScreen("Menu");
        } else {
            JOptionPane.showMessageDialog(mainFrame, "Error: Cannot delete (Active reservations or not found).");
        }
    }

    /**
     * Create booking for current property
     * @param guestName guest name
     * @param checkIn check in date
     * @param checkOut check out date
     */
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

    /**
     * Get availability information for a date range
     * @param start start date
     * @param end end date
     * @return formatted string with availability information
     */
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

    /**
     * Get details information on reservations
     * @return formatted string of all reservations
     */
    public String getReservationDetails() {
        Property currentProperty = getCurrentProperty();

        if (currentProperty == null) {
            JOptionPane.showMessageDialog(mainFrame,
                    "No property selected.");
            return null;
        }

        return model.getReservationInformation(currentProperty.getName());
    }

    /**
     * Manage setting environmental price modifiers for specific dates
     * @param name the name of property
     * @param date date to modify
     * @param modifier the price modifier value
     */
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

    /**
     * Check if any property exist in the system
     * @return true -> if at least one property exist, false -> otherwise
     */
    public boolean hasProperties() {
        String[] properties = model.getPropertyNames();
        return properties != null && properties.length > 0;
    }

    /**
     * Switches screen only if properties exist
     * @param screenName screen to switch
     */
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