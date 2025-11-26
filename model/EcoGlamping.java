package model;

/**
 * Extends property class and add 1.5 multiplier to base price
 */
public class EcoGlamping extends Property {
    private static final double PRICE_MULTIPLIER = 1.5;

    /**
     * Constructor for new Eco Glamping Property
     * @param name property name
     */
    public EcoGlamping(String name) {
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