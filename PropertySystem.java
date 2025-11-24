import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PropertySystem {

    private List<Property> properties = new ArrayList<>();

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

    public Property findProperty(String name) {
        for (Property p : properties) {
            if (p.getName().equalsIgnoreCase(name)) return p;
        }
        return null;
    }

    public boolean removeProperty(String name) {
        Property p = findProperty(name);
        if (p == null) return false;
        if (!p.getReservations().isEmpty()) return false;
        return properties.remove(p);
    }

    public boolean isNameUnique(String name) {
        for (Property p : properties) {
            if (p.getName().equalsIgnoreCase(name)) return false;
        }
        return true;
    }

    public List<Property> getAllProperties() {
        return Collections.unmodifiableList(properties);
    }
}
