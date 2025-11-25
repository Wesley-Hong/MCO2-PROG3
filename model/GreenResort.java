package model;

public class GreenResort extends Property {
    public GreenResort(String name) {
        super(name);
        // 1500 * 1.35 = 2025.0
        this.updateBasePrice(getBasePrice());
    }
    public boolean updateBasePrice(double newPrice) {
        return super.updateBasePrice(newPrice * 1.35);
    }
}
