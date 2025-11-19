import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * PropertySystem manages a collection of properties
 * Each property system contains array list of properties
 */
public class PropertySystem {

    private List<Property> properties = new ArrayList<>();

    /**
     * Create new property if the name is unique
     * @param name the name of the property to create
     * @return true -> successfully made, false -> won't make new property
     */
    public boolean createProperty(String name) {
        if (!isNameUnique(name)) return false;
        //Property p = new Property(name);

        properties.add(p);
        return true;
    }

    /**
     * Find and returns a property based on its property name
     * @param name the name of the property to find
     * @return true -> the property object, false -> null
     */
    public Property findProperty(String name) {
        for (Property p : properties) {
            if (p.getName().equalsIgnoreCase(name)) return p;
        }
        return null;
    }

    /**
     * Removes a property from the system if it exists and has no reservations
     * @param name the name of the property to remove
     * @return true -> remove the property, false -> won't remove the property
     */
    public boolean removeProperty(String name) {
        Property p = findProperty(name);
        if (p == null) return false;
        if (!p.getReservations().isEmpty()) return false;
        return properties.remove(p);
    }

    /**
     * Check if the property name is unique
     * @param name the name to check
     * @return true -> no property has used the name, false -> the name has been use by other property
     */
    public boolean isNameUnique(String name) {
        for (Property p : properties) {
            if (p.getName().equalsIgnoreCase(name)) return false;
        }
        return true;
    }

    /**
     * Returns the list of all properties currently managed by the system
     * @return read-only list of properties
     */
    public List<Property> getAllProperties() {
        return Collections.unmodifiableList(properties);
    }
}
