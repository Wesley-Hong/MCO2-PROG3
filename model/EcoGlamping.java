package model;

public class EcoGlamping extends Property {
    public EcoGlamping(String name) {
        super(name);
        // 1500 * 1.5 = 2250.0
        double basePrice = getBasePrice();
        this.updateBasePrice(basePrice * 1.5);
    }
}
