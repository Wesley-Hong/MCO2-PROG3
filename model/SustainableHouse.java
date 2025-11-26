package model;

/**
 * Extends property class and add 1.2 multiplier to base price
 */
public class SustainableHouse extends Property {
    private static final double PRICE_MULTIPLIER = 1.2;

    /**
     * Constructor for new Sustainable House Property
     * @param name property name
     */
    public SustainableHouse(String name) {
        super(name);
        super.updateBasePrice(getBasePrice() * PRICE_MULTIPLIER);
    }

    /**
     * Updates the base price of the property
     * @param newPrice new base price
     * @return true -> successfully changed, false -> otherwise
     */
    @Override
    public boolean updateBasePrice(double newPrice) {
        return super.updateBasePrice(newPrice * PRICE_MULTIPLIER);
    }
}