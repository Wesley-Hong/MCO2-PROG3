package model;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * The Driver class acts as the main entry point for the Green Property Exchange system
 * Text-based interface for creating, viewing, managing and simulating bookings for list of properties
 */
public class Driver {
    private static Scanner sc = new Scanner(System.in);
    private static PropertySystem system = new PropertySystem();

    /**
     * Main method that runs the system menu loop
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("===== Green Property Exchange =====");

        while (true) {
            System.out.println("\n======= Main Menu =======");
            System.out.println("1. Create Property");
            System.out.println("2. View Property");
            System.out.println("3. Manage Property");
            System.out.println("4. Simulate Booking");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = readInt();
            switch (choice) {
                case 1 -> createProperty();
                case 2 -> viewProperty();
                case 3 -> manageProperty();
                case 4 -> bookingProperty();
                case 5 -> {
                    System.out.println("Exiting system......");
                    return;
                }
                default -> System.out.println("Invalid option! ");
            }
        }
    }

    /* Main four functions */
    /**
     * Create new property by asking the user for its name
     * It automatically capitalized the first letter of the name
     */
    private static void createProperty() {
        System.out.print("Enter property name: ");
        String input = sc.nextLine().trim();
        String propertyName = capitalizedFirstLetter(input);

        if (input.isEmpty()) {
            System.out.println("Error: Property name can not be empty");
            return;
        }

        // New Menu for Type Selection
        System.out.println("Select Property Type:");
        System.out.println("1. Eco-Apartment");
        System.out.println("2. Sustainable House");
        System.out.println("3. Green Resort");
        System.out.println("4. Eco-Glamping");
        System.out.print("Choice: ");
        int type = readInt();

        // Pass both name and type to the system
        if (system.createProperty(propertyName, type)) {
            System.out.println("Property '" + propertyName + "' created successfully.");
        } else {
            System.out.println("Error: The property name already exists.");
        }
    }

    /**
     * Display property (calendar, summary, details)
     * For a specific property
     */
    private static void viewProperty() {

        List<Property> properties = system.getAllProperties();
        if (properties.isEmpty()) {
            System.out.println("No properties available.");
            return;
        }
        System.out.println("\n=== Property List ===");
        for (Property p : properties) {
            System.out.println(p.getName());
        }

        System.out.print("Enter property name: ");
        String name = sc.nextLine().trim();
        Property property = system.findProperty(name);

        if (property == null) {
            System.out.println("Property not found.");
            return;
        }

        System.out.println("\nManaging Property: " + property.getName());

        while (true) {
            System.out.println("===========================");
            System.out.println("1. View Calendar");
            System.out.println("2. View Property Summary");
            System.out.println("3. View Details");
            System.out.println("4. Back to Main Menu");
            System.out.println("===========================");
            System.out.print("Choose an option: ");

            int choice = readInt();
            switch (choice) {
                case 1 -> System.out.println(property.getCalendarView());
                case 2 -> propertyInfo(property);
                case 3 -> details(property);
                case 4 -> { return; }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    /**
     * Displays and manages property settings such as:
     * change property name, update price, remove reservation, delete property
     */
    private static void manageProperty() {

        List<Property> properties = system.getAllProperties();
        if (properties.isEmpty()) {
            System.out.println("No properties available.");
            return;
        }
        System.out.println("\n=== Property List ===");
        for (Property p : properties) {
            System.out.printf("- %s | ₱%.2f | Available Dates: %d | Earnings: ₱%.2f\n",
                    p.getName(), p.getBasePrice(), p.getAvailableDateCount(), p.getEstimatedEarnings());
        }

        System.out.print("Enter property name: ");
        String name = sc.nextLine().trim();
        Property property = system.findProperty(name);

        if (property == null) {
            System.out.println("Property not found.");
            return;
        }

        while (true) {
            System.out.println("==== Manage Property ====");
            System.out.println("1. Change Property Name");
            System.out.println("2. Update Base Price");
            System.out.println("3. Remove Reservation");
            System.out.println("4. Remove Property");
            System.out.println("5. Exit");
            System.out.println("=========================");

            int choice = readInt();
            switch (choice) {
                case 1 -> changePropertyName(property);
                case 2 -> updateBasePrice(property);
                case 3 -> {
                    reservationInfo(property, true);
                    return;
                }
                case 4 -> {
                    removingProperty(property);
                    return;
                }
                case 5 -> {
                    System.out.println("Exiting system......");
                    return;
                }
                default -> System.out.println("Invalid option! ");
            }
        }
    }

    /**
     * Booking simulation
     * Let user input guest name, check in / out dates
     * Validate dates and display booking summary
     */
    private static void bookingProperty () {

        List<Property> properties = system.getAllProperties();
        if (properties.isEmpty()) {
            System.out.println("No properties available.");
            return;
        }
        System.out.println("\n=== Property List ===");
        for (Property p : properties) {
            System.out.println(p.getName());
        }

        System.out.print("Enter property name: ");
        String name = sc.nextLine().trim();
        Property property = system.findProperty(name);

        if (property == null) {
            System.out.println("Property not found.");
            return;
        }

        System.out.print("Enter guest name: ");
        String guestName = sc.nextLine().trim();
        System.out.print("Enter check-in date (1–29): ");
        int checkIn = readInt();
        System.out.print("Enter check-out date (2–30): ");
        int checkOut = readInt();

        if (guestName.isEmpty()) {
            System.out.println("Guest name should not be empty. Please try again");
            return;
        }

        Reservation res = property.createBooking(guestName, checkIn, checkOut);
        if (res == null) {
            System.out.println("Booking failed: invalid or unavailable dates.");
        } else {
            System.out.println("Booking successful for " + res.getGuestName());
            System.out.printf("Total: ₱%.2f\n", res.getTotalPrice());
            System.out.println("Price Breakdown:\n" + res.getPriceBreakdownString());
        }


    }

    /**
     * Display detailed property information such as:
     * Availability across date range, specific date information, reservation list and breakdown price
     * @param property the selected property
     */
    private static void details(Property property) {

        while (true) {
            System.out.println("\n====== View Details ======");
            System.out.println("1. View availability for a date range");
            System.out.println("2. View information of specific date");
            System.out.println("3. View reservation details");
            System.out.println("==========================");
            System.out.print("Choose an option: ");

            int choice = readInt();
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter check-in date (1–29): ");

                    int checkIn = readInt();
                    System.out.print("Enter check-out date (2–30): ");
                    int checkOut = readInt();
                    System.out.println("===========================");

                    int totalNights = checkOut - checkIn;
                    int availableNights = property.getAvailableDateCount(checkIn, checkOut);
                    int bookedNights = totalNights - availableNights;

                    System.out.println("Available Dates: " + availableNights);
                    System.out.println("Booked Dates: " + bookedNights);

                    return;
                }

                case 2 -> {
                    System.out.print("Enter date number (1 to 30): ");

                    int date = readInt();

                    Day day = property.getDayInfo(date);
                    System.out.println("===== Date Information =====");
                    System.out.println("Date number: " + day.getDateNumber());
                    System.out.println("Price per night: " + day.getPrice());
                    System.out.println("Status: " + day.getStatus());
                    System.out.println();
                    return;
                }

                case 3 -> {
                    reservationInfo(property, false);
                    return;
                }

            }
        }


    }

    /**
     * Display active reservation for a property with guest details
     * Also has option to delete reservation
     * @param property the selected property
     * @param deleteReservation true -> delete reservation, false -> don't delete reservation
     */
    private static void reservationInfo (Property property, boolean deleteReservation) {
        System.out.println("===== Reservation Information =====");
        System.out.println("Active Reservation for " + property.getName() + " :");

        if (property.getReservations().isEmpty()) {
            System.out.println("No reservations found");
            return;
        }

        System.out.println("Total Reservation: " + property.getReservations().size());
        System.out.println("================================");
        int count = 1;
        for (Reservation res : property.getReservations()) {
            System.out.println("Reservation #" + count);
            System.out.println("Guest Name: " + res.getGuestName());
            System.out.println("Check in Date: " + res.getCheckInDate());
            System.out.println("Check out Date: " + res.getCheckOutDate());
            System.out.printf("Total Price: PHP %.2f%n", res.getTotalPrice());
            System.out.println();

            System.out.println("Price breakdown: ");
            Map<Integer, Double> breakdown = res.getPriceBreakdown();
            for (int date = res.getCheckInDate(); date < res.getCheckOutDate(); date++) {
                System.out.printf("Date %d: PHP %.2f%n", date, breakdown.get(date));
            }
            System.out.println();
            count++;

        }

        if (deleteReservation) {

            System.out.print("Enter reservation number to remove (1-" + property.getReservations().size() + ") or 0 to cancel: ");
            int choice = readInt();

            if (choice == 0) {
                System.out.println("Operation cancelled");
                return;
            }

            if (choice < 1 || choice > property.getReservations().size()) {
                System.out.println("Invalid selection");
                return;
            }

            Reservation res = property.getReservations().get(choice - 1);

            boolean success = property.removeReservation(res);

            if (success) {
                System.out.println("Reservation removed");
            } else {
                System.out.println("Failed to remove reservation");
            }
        }
    }

    /**
     * Change property name and validate for duplicate
     * @param property the property to rename
     */
    private static void changePropertyName (Property property) {
        System.out.print("Change property name to: ");
        String name = sc.nextLine();
        String propertyName = capitalizedFirstLetter(name);
        if (property.setName(propertyName, system)) {
            System.out.println("Property name changed successfully");
        } else {
            System.out.println("Failed to change property name");
        }
    }

    /**
     * Display property summary info
     * @param property the selected property
     */
    private static void propertyInfo (Property property) {

        System.out.println("Property: " + property.getName());

        System.out.println("Total number of available dates: " + property.getAvailableDateCount());

        System.out.println("Estimated earnings: " + property.getEstimatedEarnings());

    }

    /**
     * Updates the base price of a property
     * Ensure price validity and no active reservations
     * @param property the selected property
     */
    private static void updateBasePrice(Property property) {
        System.out.print("Enter new base price: ");
        double price = readDouble();
        if (property.updateBasePrice(price)) {
            System.out.println("Base price updated successfully.");
        } else {
            System.out.println("Update failed. Ensure no active reservations and price ≥ 100.0.");
        }
    }

    /**
     * Remove property
     * Ensure there is no active reservation
     * @param property the selected property
     */
    private static void removingProperty(Property property) {
        if (system.removeProperty(property.getName())) {
            System.out.println("Property removed successfully.");
        } else {
            System.out.println("Cannot remove. Ensure property exists and has no reservations.");
        }
    }

    // Utility readers

    /**
     * Safely read integer input
     * Ask user to try again if invalid input
     * @return a valid integer
     */
    private static int readInt() {
        while (true) {
            try {
                String line = sc.nextLine();
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.out.print("Enter a valid number: ");
            }
        }
    }

    /**
     * Safely read double input
     * Ask user to try again if invalid input
     * @return a valid double
     */
    private static double readDouble() {
        while (true) {
            try {
                String line = sc.nextLine();
                return Double.parseDouble(line.trim());
            } catch (NumberFormatException e) {
                System.out.print("Enter a valid number: ");
            }
        }
    }

    /**
     * Capitalize the first letter of a string and converts the rest to lowercase
     * @param string the input string
     * @return formated string capital first letter
     */
    private static String capitalizedFirstLetter (String string){
        if (string == null || string.isEmpty()) return string;
        return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
    }
}
