package model;

public class GreenResort extends Property {
    public GreenResort(String name) {
        super(name);
        // 1500 * 1.35 = 2025.0
        double basePrice = getBasePrice();
        this.updateBasePrice(basePrice * 1.35);
    }
}
