package model;

public class SustainableHouse extends Property {
    public SustainableHouse(String name) {
        super(name);
        // 1500 * 1.2 = 1800.0
        this.updateBasePrice(getBasePrice());
    }
    public boolean updateBasePrice(double newPrice) {
        return super.updateBasePrice(newPrice * 1.2);
    }
}
