package model;

public class EcoGlamping extends Property {
    private static final double PRICE_MULTIPLIER = 1.5;

    public EcoGlamping(String name) {
        super(name);
        // Apply multiplier to default base price (1500 * 1.5 = 2250.0)
        super.updateBasePrice(getBasePrice() * PRICE_MULTIPLIER);
    }

    @Override
    public boolean updateBasePrice(double newPrice) {
        // When updating price, always apply the multiplier
        return super.updateBasePrice(newPrice * PRICE_MULTIPLIER);
    }
}