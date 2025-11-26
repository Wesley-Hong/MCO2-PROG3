package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represent property listing in the property system.
 * Each property contains property name, base price per night,
 * array of days and array list of reservations.
 */
public abstract class Property {   

    private String name;
    protected double basePrice = 1500.0; // Changed to protected
    protected Day[] days = new Day[30]; // Changed to protected
    private List<Reservation> reservations = new ArrayList<>();

    /**
     * Constructor for property
     * @param name the unique name of the property
     */
    public Property(String name) {
        this.name = name;
        for (int i = 0; i < 30; i++) {
            days[i] = new Day(i + 1, basePrice);
        }
    }

    // ---------- View Property Methods ----------

    /**
     * Calculate the total estimated earnings of the property based on
     * existing reservations
     * @return the sum of all reservation totals
     */
    public double getEstimatedEarnings() {
        double total = 0.0;
        for (Reservation r : reservations) {
            total += r.getTotalPrice();
        }
        return total;
    }

    /**
     * Counts how many days in the property's calendar is available
     * @return the number of unbooked days
     */
    public int getAvailableDateCount() {
        int count = 0;
        for (Day d : days) if (!d.isBooked()) count++;
        return count;
    }

    /**
     * Counts how many days in the property's calendar is available (with specific date range)
     * @param checkIn check in date
     * @param checkOut check out date
     * @return the number of unbooked days
     */
    public int getAvailableDateCount(int checkIn, int checkOut) {
        int count = 0;
        for (int i = checkIn; i < checkOut; i++) {
            if (!days[i - 1].isBooked()) count++;
        }
        return count;
    }

    /**
     * Generate the calendar view that show day, booking status, and price
     * @return a formatted string showing all days, booking status, and price
     */
    public String getCalendarView() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Calendar for property: %s%n", name));
        for (int i = 0; i < days.length; i++) {
            Day d = days[i];
            sb.append(String.format("%2d: %-7s (%.2f)", d.getDateNumber(), d.getStatus(), d.getPrice()));
            if ((i + 1) % 5 == 0) sb.append(System.lineSeparator()); 
            else sb.append(" | ");
        }
        return sb.toString();
    }

    /**
     * Retrieves the specific date
     * @param dateNumber the day number (1 - 30)
     * @return the day object for the given date
     */
    public Day getDayInfo(int dateNumber) {
        if (dateNumber < 1 || dateNumber > 30) throw new IllegalArgumentException("dateNumber must be 1..30");
        return days[dateNumber - 1];
    }

    // ---------- Manage Property Methods ----------

    /**
     * Change the name of property while ensuring uniqueness
     * @param newName new name of property
     * @param system check whole property to see if unique
     * @return true -> success; false -> otherwise
     */
    public boolean setName(String newName, PropertySystem system) {
        if (newName == null || newName.isEmpty()) return false;
        if (newName.equals(this.name)) return true; 
        if (!system.isNameUnique(newName)) return false;
        this.name = newName;
        return true;
    }

    /**
     * Update base price of property
     * @param newPrice new base price
     * @return true -> success; false -> otherwise
     */
    public boolean updateBasePrice(double newPrice) {
        if (!reservations.isEmpty()) return false;
        if (newPrice < 100.0) return false;
        this.basePrice = newPrice;
        for (Day d : days) d.setPrice(newPrice);
        return true;
    }

    /**
     * Remove reservation from property
     * @param res reservation object to remove
     * @return true -> success; false -> otherwise
     */
    public boolean removeReservation(Reservation res) {
        if (res == null) return false;
        boolean removed = reservations.remove(res);
        if (!removed) return false;
        int start = res.getCheckInDate();
        int end = res.getCheckOutDate();
        for (int d = start; d < end; d++) {
            Day day = days[d - 1];
            if (day.getReservation() == res) day.clearReservation();
        }
        return true;
    }

    /**
     * Applies a pricing modifier to a specific day
     * @param dateNumber date to modify
     * @param modifier modifier value
     * @return true -> success; false -> otherwise
     */
    public boolean setEnvironmentalDayModifier(int dateNumber, double modifier) {
        if (dateNumber < 1 || dateNumber > 30) return false;

        Day targetDay = days[dateNumber - 1];

        try {
            targetDay.setEnvironmentalModifier(modifier);
            return true;
        } catch (IllegalArgumentException e) {
            // Handle validation error if modifier is out of range
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }

    // ---------- Booking / Simulation Methods ----------

    /**
     * Check date range information
     * @param checkIn check in date
     * @param checkOut check out date
     * @return true -> success; false -> otherwise
     */
    public boolean isDateRangeAvailable(int checkIn, int checkOut) {
        if (checkIn < 1 || checkIn > 29) return false;
        if (checkOut < 2 || checkOut > 30) return false;
        if (checkIn >= checkOut) return false;

        for (int d = checkIn; d < checkOut; d++) {
            if (days[d - 1].isBooked()) return false;
        }
        return true;
    }

    /**
     * Creates booking reservation for the property
     * @param guestName guest name
     * @param checkIn check in date
     * @param checkOut check out date
     * @return true -> success; false -> otherwise
     */
    public Reservation createBooking(String guestName, int checkIn, int checkOut) {
        if (!isDateRangeAvailable(checkIn, checkOut)) return null;

        Map<Integer, Double> breakdown = new HashMap<>();
        double total = 0.0;
        for (int d = checkIn; d < checkOut; d++) {
            double p = days[d - 1].getPrice();
            breakdown.put(d, p);
            total += p;
        }

        Reservation res = new Reservation(guestName, checkIn, checkOut, total, breakdown);
        reservations.add(res);
        for (int d = checkIn; d < checkOut; d++) {
            days[d - 1].setReservation(res);
        }
        return res;
    }

    // ---------- Getters ----------

    public String getName() {
        return name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public Day[] getDays() {
        return days;
    }

    public String getType() {
        return this.getClass().getSimpleName();
    }
}
