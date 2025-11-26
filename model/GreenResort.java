package model;

public class GreenResort extends Property {
    public GreenResort(String name) {
        super(name);
        // 1500 * 1.35 = 2025.0
        basePrice = 2025.0;
        for (Day d : days) {
            d.setPrice(basePrice);
        }
    }
}
