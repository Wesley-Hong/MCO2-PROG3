import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represent property listing in the property system
 * Each property contains property name, base price per night
 * array of days and array list of reservations
 */
public class Property {

    private String name;
    private double basePrice = 1500.0;
    private Day[] days = new Day[30];
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
            if ((i + 1) % 5 == 0) sb.append(System.lineSeparator()); // break into rows of 5 for readability
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
     * Changing the property name if it is unique
     * @param newName the new property name
     * @param system the property system used to validate uniqueness
     * @return true -> name successfully update, false -> name is not unique
     */
    public boolean setName(String newName, PropertySystem system) {
        if (newName == null || newName.isEmpty()) return false;
        if (newName.equals(this.name)) return true; // no change
        if (!system.isNameUnique(newName)) return false;
        this.name = newName;
        return true;
    }

    /**
     * Update base price if no active reservation
     * The new price should be higher than 100 pesos
     * @param newPrice new base price to apply
     * @return true -> successfully updated, false -> won't update
     */
    public boolean updateBasePrice(double newPrice) {
        if (!reservations.isEmpty()) return false;
        if (newPrice < 100.0) return false;
        this.basePrice = newPrice;
        for (Day d : days) d.setPrice(newPrice);
        return true;
    }

    /**
     * Removes specific reservation and updates the calendar
     * @param res the reservation to remove
     * @return true -> successfully removed, false -> won't remove
     */
    public boolean removeReservation(Reservation res) {
        if (res == null) return false;
        boolean removed = reservations.remove(res);
        if (!removed) return false;
        // clear day links
        int start = res.getCheckInDate();
        int end = res.getCheckOutDate();
        for (int d = start; d < end; d++) {
            Day day = days[d - 1];
            if (day.getReservation() == res) day.clearReservation();
        }
        return true;
    }

    // ---------- Booking / Simulation Methods ----------

    /**
     * Check if the given date range is available for booking
     * @param checkIn check in day number
     * @param checkOut check out day number
     * @return true -> all days in range are available, false -> one of the days in range is not available
     */
    public boolean isDateRangeAvailable(int checkIn, int checkOut) {
        // Validate boundaries
        if (checkIn < 1 || checkIn > 30) return false;
        if (checkOut < 1 || checkOut > 31) return false;
        if (!(checkIn < checkOut)) return false;

        for (int d = checkIn; d < checkOut; d++) {
            if (days[d - 1].isBooked()) return false;
        }
        return true;
    }

    /**
     * Booking for the guest if the specified date range is valid and available
     * @param guestName the guest making the reservation
     * @param checkIn check in day number
     * @param checkOut check out day number
     * @return true -> new reservation, false -> no reservation being made
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

    /**
     * Returns the property name
     * @return the name of the property
     */
    public String getName() {
        return name;
    }

    /**
     * Return the current based price of the property
     * @return the base price
     */
    public double getBasePrice() {
        return basePrice;
    }

    /**
     * Return the list of all reservations associated with this property
     * @return reservation list
     */
    public List<Reservation> getReservations() {
        return reservations;
    }
}