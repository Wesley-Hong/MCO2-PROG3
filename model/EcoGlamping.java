package model;

public class EcoGlamping extends Property {
    public EcoGlamping(String name) {
        super(name);
        // 1500 * 1.5 = 2250.0
        basePrice = 2250.0;
        for (Day d : days) {
            d.setPrice(basePrice);
        }
    }
}
