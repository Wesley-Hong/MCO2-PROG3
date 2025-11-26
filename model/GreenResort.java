package model;

public class GreenResort extends Property {
    private static final double PRICE_MULTIPLIER = 1.35;

    public GreenResort(String name) {
        super(name);
        // Apply multiplier to default base price (1500 * 1.35 = 2025.0)
        super.updateBasePrice(getBasePrice() * PRICE_MULTIPLIER);
    }

    @Override
    public boolean updateBasePrice(double newPrice) {
        // When updating price, always apply the multiplier
        return super.updateBasePrice(newPrice * PRICE_MULTIPLIER);
    }
}