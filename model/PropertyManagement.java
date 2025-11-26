package model;

import java.util.List;

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




    // choose property - for manage property




















    // validating property name

    // choosing property


    public String[] getFullInformation() {
        List<Property> properties =system.getAllProperties();
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
    public boolean changePropertyName (String oldName, String newName) {
        Property p = system.findProperty(oldName);
        if (p == null) {
            return false;
        }
        return p.setName(newName, system);
    }

    public boolean updateBasePrice (String propertyName, double newPrice) {
        Property p = system.findProperty(propertyName);
        if (p == null) {
            return false;
        }
        return p.updateBasePrice(newPrice);
    }

    public boolean removeProperty(String propertyName) {
        return system.removeProperty(propertyName);
    }

    public boolean removeReservation (String propertyName, int reservationIndex) {
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

    public Reservation createBooking (String propertyName, String guestName, int checkIn, int checkOut) {

        Property property = system.findProperty(propertyName);

        if (property == null) {
            return null;
        }

        if (guestName == null || guestName.trim().isEmpty()) {
            return null;
        }

        return property.createBooking(guestName, checkIn, checkOut);


    }

    public boolean isDateRangeAvailable (String propertyName, int checkIn, int checkOut) {
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
}
