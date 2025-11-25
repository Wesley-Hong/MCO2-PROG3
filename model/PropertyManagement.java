package model;

import java.util.List;

public class PropertyManagement {

    private PropertySystem system;

    public PropertyManagement() {
        this.system = new PropertySystem();
    }

    // validating property name
    public boolean createProperty(String name, int type) {
        if (name == null || name.isEmpty()) {
            return false;
        }

        return system.createProperty(name, type);
    }

    // choosing property
    public String[] getPropertyNames() {
        List<Property> properties =system.getAllProperties();
        String[] nameAndType = new String[properties.size()];
        for (int i = 0; i < properties.size(); i++) {
            Property p = properties.get(i);
            String type = p.getClass().getSimpleName();
            nameAndType[i] = String.format("%s (%s)", p.getName(), type);
        }
        return nameAndType;
    }

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


}
