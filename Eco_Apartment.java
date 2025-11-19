public class Eco_Apartment extends Property{

    /**
     * Constructor for property
     *
     * @param name the unique name of the property
     */
    public Eco_Apartment(String name) {
        super(name);
    }

    @Override
    public double getPriceMultiplier() {
        return 0;
    }
}
