package model;

public class SustainableHouse extends Property {
    public SustainableHouse(String name) {
        super(name);
        // 1500 * 1.2 = 1800.0
        double basePrice = getBasePrice();
        this.updateBasePrice(basePrice * 1.2);
    }
}
