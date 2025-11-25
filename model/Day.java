package model;

/**
 * Represents the single day in a property's availability calendar.
 * Each Day contains date number (from 1 to 30), the base price, reservation status
 */

public class Day {

    private final int DATE_NUMBER; // 1..30
    private double price;
    private Reservation reservation; // null if available

    /**
     * Constructor for Day
     * @param dateNumber day number (1 to 30)
     * @param price base price of the property
     */
    public Day(int dateNumber, double price) {
        if (dateNumber < 1 || dateNumber > 30) throw new IllegalArgumentException("dateNumber must be 1..30");
        this.DATE_NUMBER = dateNumber;
        this.price = price;
        this.reservation = null;
    }

    /**
     * Check if this day is already booked
     * @return true -> has reservation, false -> has no reservation
     */
    public boolean isBooked() {
        return reservation != null;
    }

    /**
     * Assigning reservation to this day
     * @param res Reservation object to assign
     */
    public void setReservation(Reservation res) {
        this.reservation = res;
    }

    /**
     * Clearing the reservation assigned to this day
     */
    public void clearReservation() {
        this.reservation = null;
    }

    /**
     * Updating the price for this day
     * @param price new base price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns the day number
     * @return the date number (1 to 30)
     */
    public int getDateNumber() {
        return DATE_NUMBER;
    }

    /**
     * Return the price for this day
     * @return current base price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Return reservation assigned to this day
     * @return reservation object
     */
    public Reservation getReservation() {
        return reservation;
    }

    /**
     * Return booking status of this day
     * @return Booked if reserved, else available
     */
    public String getStatus() {
        return isBooked() ? "Booked" : "Available";
    }
}
