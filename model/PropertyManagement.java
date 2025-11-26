package model;

import java.util.List;
import java.util.Map;

/**
 * It is the main model that handles the overall business logic of property management
 */
public class PropertyManagement {

    private PropertySystem system;

    /**
     * Constructor for making the property system
     */
    public PropertyManagement() {
        this.system = new PropertySystem();
    }

    /**
     * Creates new property with specified name and type
     * @param name property name
     * @param type property type
     * @return true -> successfully create property, false -> otherwise
     */
    public boolean createProperty(String name, int type) {
        if (name == null || name.isEmpty()) {
            return false;
        }
        return system.createProperty(name, type);
    }

    /**
     * Get an array of property names and its type for display purposes
     * @return string of array that contains property name and type
     */
    public String[] getPropertyNames() {
        List<Property> properties = system.getAllProperties();
        String[] nameAndType = new String[properties.size()];

        // loop through all properties
        for (int i = 0; i < properties.size(); i++) {
            Property p = properties.get(i);
            String type = p.getClass().getSimpleName();
            nameAndType[i] = String.format("%s (%s)", p.getName(), type);
        }
        return nameAndType;
    }

    /**
     * Get an array of property names and its type, price, available date,
     * and earnings for display purposes
     * @return string of array that contains property name, type, price,
     *         available date, earnings
     */
    public String[] getFullInformation() {
        List<Property> properties = system.getAllProperties();
        String[] fullInformation = new String[properties.size()];
        for (int i = 0; i < properties.size(); i++) {
            Property p = properties.get(i);
            String type = p.getClass().getSimpleName();
            double price = p.getBasePrice();
            int availableDate = p.getAvailableDateCount();
            double earnings = p.getEstimatedEarnings();
            fullInformation[i] = String.format("%s (%s) | ₱%.2f | Available Dates: %d | Earnings: ₱%.2f",
                    p.getName(), type, price, availableDate, earnings);
        }
        return fullInformation;
    }

    /**
     * Find property by its name
     * @param name property name
     * @return Property object, if not found then null
     */
    public Property findPropertyByName(String name) {
        List<Property> properties = system.getAllProperties();
        for (Property p : properties) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Changing existing property name
     * @param oldName original property name
     * @param newName new property name
     * @return true -> successfully change property name, false -> otherwise
     */
    public boolean changePropertyName(String oldName, String newName) {
        Property p = system.findProperty(oldName);
        if (p == null) {
            return false;
        }
        return p.setName(newName, system);
    }

    /**
     * Update property base price
     * @param propertyName property to update
     * @param newPrice new base price to set
     * @return true -> successfully change base price, false -> otherwise
     */
    public boolean updateBasePrice(String propertyName, double newPrice) {
        Property p = system.findProperty(propertyName);
        if (p == null) {
            return false;
        }
        return p.updateBasePrice(newPrice);
    }

    /**
     * Remove property from the system
     * @param propertyName the property to remove
     * @return true -> successfully remove property, false -> otherwise
     */
    public boolean removeProperty(String propertyName) {
        return system.removeProperty(propertyName);
    }

    /**
     * Remove reservation from the property
     * @param propertyName the property to remove reservation
     * @param reservationIndex the index of reservation to remove
     * @return true -> successfully remove reservation, false -> otherwise
     */
    public boolean removeReservation(String propertyName, int reservationIndex) {
        Property p = system.findProperty(propertyName);
        if (p == null) {
            return false;
        }
        List<Reservation> reservations = p.getReservations();
        if (reservationIndex < 1 || reservationIndex > reservations.size()) {
            return false;
        }
        Reservation res = reservations.get(reservationIndex - 1);
        return p.removeReservation(res);
    }

    /**
     * Create reservation for a property
     * @param propertyName property to book
     * @param guestName name of the guest
     * @param checkIn check in date
     * @param checkOut check out date
     * @return reservation object
     */
    public Reservation createBooking(String propertyName, String guestName, int checkIn, int checkOut) {
        Property property = system.findProperty(propertyName);

        if (property == null) {
            return null;
        }

        if (guestName == null || guestName.trim().isEmpty()) {
            return null;
        }

        return property.createBooking(guestName, checkIn, checkOut);
    }

    /**
     * Check if date range is available for booking
     * @param propertyName property to check
     * @param checkIn check in date
     * @param checkOut check out date
     * @return true -> date range is available, false -> otherwise
     */
    public boolean isDateRangeAvailable(String propertyName, int checkIn, int checkOut) {
        Property property = system.findProperty(propertyName);

        if (property == null) {
            return false;
        }
        return property.isDateRangeAvailable(checkIn, checkOut);
    }

    /**
     * Details on specific date of a property
     * @param propertyName day info of property
     * @param dateNumber the date number to get information
     * @return formatted string of date information
     */
    public String getDateInformation(String propertyName, int dateNumber) {
        Property property = system.findProperty(propertyName);

        if (property == null) {
            return null;
        }

        if (dateNumber < 1 || dateNumber > 30) {
            return null;
        }

        Day day = property.getDayInfo(dateNumber);

        StringBuilder info = new StringBuilder();
        info.append("===== Date Information =====\n\n");
        info.append("Date number: ").append(day.getDateNumber()).append("\n");
        info.append("Price per night: ₱").append(String.format("%.2f", day.getPrice())).append("\n");
        info.append("Status: ").append(day.getStatus()).append("\n");

        return info.toString();
    }

    /**
     * Get information on date range about the number of booked and available dates
     * @param propertyName property to get information
     * @param startDate start date of the range
     * @param endDate end date of the range
     * @return formatted string on number of booked and available dates
     */
    public String getDateRange(String propertyName, int startDate, int endDate) {
        Property property = system.findProperty(propertyName);

        if (property == null) {
            return null;
        }

        if (startDate < 1 || startDate > 29 || endDate < 2 || endDate > 30) {
            return null;
        }

        if (startDate >= endDate) {
            return null;
        }

        int totalNights = endDate - startDate;
        int availableNights = property.getAvailableDateCount(startDate, endDate);
        int bookedNights = totalNights - availableNights;

        StringBuilder info = new StringBuilder();
        info.append("===========================\n\n");
        info.append("Available Dates: ").append(availableNights).append("\n");
        info.append("Booked Dates: ").append(bookedNights).append("\n");

        return info.toString();
    }

    /**
     * Get reservation information for a property
     * @param propertyName property to get reservation information
     * @return formatted string of all reservation details
     */
    public String getReservationInformation(String propertyName) {
        Property property = system.findProperty(propertyName);

        if (property == null) {
            return null;
        }

        StringBuilder info = new StringBuilder();
        info.append("===== Reservation Information =====\n\n");
        info.append("Active Reservation for ").append(property.getName()).append(" :\n\n");

        if (property.getReservations().isEmpty()) {
            info.append("No reservations found\n");
            return info.toString();
        }

        info.append("Total Reservation: ").append(property.getReservations().size()).append("\n");
        info.append("================================\n\n");

        int count = 1;
        for (Reservation res : property.getReservations()) {
            info.append("Reservation #").append(count).append("\n");
            info.append("Guest Name: ").append(res.getGuestName()).append("\n");
            info.append("Check in Date: ").append(res.getCheckInDate()).append("\n");
            info.append("Check out Date: ").append(res.getCheckOutDate()).append("\n");
            info.append(String.format("Total Price: ₱%.2f%n", res.getTotalPrice()));
            info.append("\n");

            info.append("Price breakdown: \n");
            Map<Integer, Double> breakdown = res.getPriceBreakdown();
            for (int date = res.getCheckInDate(); date < res.getCheckOutDate(); date++) {
                info.append(String.format("Date %d: ₱%.2f%n", date, breakdown.get(date)));
            }
            info.append("\n");
            count++;
        }

        return info.toString();
    }

    /**
     * Setting up an environmental price modifier for specific date on a property
     * @param propertyName property to use
     * @param date date number
     * @param modifier price modifier
     * @return true -> successfully set, false -> otherwise
     */
    public boolean setEnvironmentalModifier(String propertyName, int date, double modifier) {
        Property property = system.findProperty(propertyName);
        if (property == null) {
            return false;
        }

        if (date < 1 || date > 30) {
            return false;
        }

        if (modifier < 0.80 || modifier > 1.20) {
            return false;
        }
        return property.setEnvironmentalDayModifier(date, modifier);
    }

}
