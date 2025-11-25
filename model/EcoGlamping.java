package model;

public class EcoGlamping extends Property {
    public EcoGlamping(String name) {
        super(name);
        // 1500 * 1.5 = 2250.0
        this.updateBasePrice(getBasePrice());
    }
    public boolean updateBasePrice(double newPrice) {
        return super.updateBasePrice(newPrice * 1.5);
    }
}
