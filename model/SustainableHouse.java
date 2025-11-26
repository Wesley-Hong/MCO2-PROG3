package model;

public class SustainableHouse extends Property {
    public SustainableHouse(String name) {
        super(name);
        // 1500 * 1.2 = 1800.0
        basePrice = 1800.0;
        for (Day d : days) {
            d.setPrice(basePrice);
        }
    }
}
