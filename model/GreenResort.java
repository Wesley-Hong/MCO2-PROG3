package model;

/**
 * Extends property class and add 1.35 multiplier to base price
 */
public class GreenResort extends Property {
    private static final double PRICE_MULTIPLIER = 1.35;

    /**
     * Constructor for new Green Resort Property
     * @param name property name
     */
    public GreenResort(String name) {
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