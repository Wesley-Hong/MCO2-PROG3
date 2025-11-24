import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PropertySystem {

    private List<Property> properties = new ArrayList<>();

    public boolean createProperty(String name) {
        if (!isNameUnique(name)) return false;
        Property p = new DefaultProperty(name);  // Use subclass
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
