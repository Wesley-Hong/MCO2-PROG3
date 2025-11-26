package model;

import java.util.List;
import java.util.Map;

public class PropertyManagement {

    private PropertySystem system;

    public PropertyManagement() {
        this.system = new PropertySystem();
    }

    // create property
    public boolean createProperty(String name, int type) {
        if (name == null || name.isEmpty()) {
            return false;
        }
        return system.createProperty(name, type);
    }

    // choose property - for view property
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

    public Property findPropertyByName(String name) {
        List<Property> properties = system.getAllProperties();
        for (Property p : properties) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

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

    public Property getProperty(String name) {
        return system.findProperty(name);
    }

    // managing property
    public boolean changePropertyName(String oldName, String newName) {
        Property p = system.findProperty(oldName);
        if (p == null) {
            return false;
        }
        return p.setName(newName, system);
    }

    public boolean updateBasePrice(String propertyName, double newPrice) {
        Property p = system.findProperty(propertyName);
        if (p == null) {
            return false;
        }
        return p.updateBasePrice(newPrice);
    }

    public boolean removeProperty(String propertyName) {
        return system.removeProperty(propertyName);
    }

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

    public boolean isDateRangeAvailable(String propertyName, int checkIn, int checkOut) {
        Property property = system.findProperty(propertyName);

        if (property == null) {
            return false;
        }
        return property.isDateRangeAvailable(checkIn, checkOut);
    }

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
