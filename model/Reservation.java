package model;

import java.util.Map;

/**
 * Represent a reservation made by a guest for a specific property
 * Each Reservation contains guest's name, check in / out date,
 * total price, reference to the property reserved, and price breakdown per day
 */

public class Reservation {

    private final String GUEST_NAME;
    private final int CHECK_IN_DATE;  // inclusive
    private final int CHECK_OUT_DATE; // exclusive
    private final double TOTAL_PRICE;
    private final Map<Integer, Double> PRICE_BREAKDOWN;

    /**
     * Constructor for Reservation
     * @param guestName the name of the guest
     * @param checkInDate the check in date
     * @param checkOutDate the check out date
     * @param totalPrice the total reservation price
     * @param priceBreakdown a map that represents the daily price breakdown
     */
    public Reservation(String guestName, int checkInDate, int checkOutDate, double totalPrice,
                        Map<Integer, Double> priceBreakdown) {
        this.GUEST_NAME = guestName;
        this.CHECK_IN_DATE = checkInDate;
        this.CHECK_OUT_DATE = checkOutDate;
        this.TOTAL_PRICE = totalPrice;
        this.PRICE_BREAKDOWN = priceBreakdown;
    }

    /**
     * Returns the name of the guest
     * @return the guest's name
     */
    public String getGuestName() {
        return GUEST_NAME;
    }

    /**
     * Returns the check in date
     * @return date of check in
     */
    public int getCheckInDate() {
        return CHECK_IN_DATE;
    }

    /**
     * Returns the check out date
     * @return date of check out
     */
    public int getCheckOutDate() {
        return CHECK_OUT_DATE;
    }

    /**
     * Returns the total price of reservation
     * @return reservation total price
     */
    public double getTotalPrice() {
        return TOTAL_PRICE;
    }

    /**
     * Returns the price breakdown for each day
     * @return map containing date/price
     */
    public Map<Integer, Double> getPriceBreakdown() {
        return PRICE_BREAKDOWN;
    }

    /**
     * Visual representation of price breakdown of the reservation
     * @return formatted string of date/price table representation
     */
    public String getPriceBreakdownString() {
        StringBuilder sb = new StringBuilder();
        int count = 0;

        for (Map.Entry<Integer, Double> e : PRICE_BREAKDOWN.entrySet()) {
            sb.append(String.format("Day %d: %.2f", e.getKey(), e.getValue()));
            count++;

            if (count % 5 == 0) {
                sb.append("\n"); // new line after every 5 entries
            } else {
                sb.append(", ");
            }
        }

        return sb.toString();
    }
}
