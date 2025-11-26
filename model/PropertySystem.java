package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Property system class manages the overall property
 */
public class PropertySystem {

    private List<Property> properties = new ArrayList<>();

    /**
     * Creates new property with specified name and type
     * @param name property name
     * @param type property type
     * @return true -> success, false -> otherwise;
     */
    public boolean createProperty(String name, int type) {
        if (!isNameUnique(name)) return false;

        Property p;
        switch (type) {
            case 1 -> p = new EcoApartment(name);
            case 2 -> p = new SustainableHouse(name);
            case 3 -> p = new GreenResort(name);
            case 4 -> p = new EcoGlamping(name);
            default -> p = new EcoApartment(name); // Default fallback
        }
    
        properties.add(p);
        return true;
    }

    /**
     * Finds property by its name
     * @param name property name
     * @return the property object
     */
    public Property findProperty(String name) {
        for (Property p : properties) {
            if (p.getName().equalsIgnoreCase(name)) return p;
        }
        return null;
    }

    /**
     * Remove property
     * @param name property name
     * @return true -> success, false -> otherwise;
     */
    public boolean removeProperty(String name) {
        Property p = findProperty(name);
        if (p == null) return false;
        if (!p.getReservations().isEmpty()) return false;
        return properties.remove(p);
    }

    /**
     * Check if property name is unique
     * @param name property name
     * @return true -> success, false -> otherwise;
     */
    public boolean isNameUnique(String name) {
        for (Property p : properties) {
            if (p.getName().equalsIgnoreCase(name)) return false;
        }
        return true;
    }

    /**
     * Return all properties in property system
     * @return all property objects in property system
     */
    public List<Property> getAllProperties() {
        return Collections.unmodifiableList(properties);
    }
}
