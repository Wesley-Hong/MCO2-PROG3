package model;

public class SustainableHouse extends Property {
    private static final double PRICE_MULTIPLIER = 1.2;

    public SustainableHouse(String name) {
        super(name);

        super.updateBasePrice(getBasePrice() * PRICE_MULTIPLIER);
    }

    @Override
    public boolean updateBasePrice(double newPrice) {

        return super.updateBasePrice(newPrice * PRICE_MULTIPLIER);
    }
}