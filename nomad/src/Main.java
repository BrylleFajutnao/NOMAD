import java.util.*;
import java.sql.*;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser = null;

    public static void main(String[] args) {
        while (true) {
            if (currentUser == null) {
                System.out.println("         _   _  ____  __  __          _____  ");
                System.out.println("        | \\ | |/ __ \\|  \\/  |   /\\   |  __ \\ ");
                System.out.println("        |  \\| | |  | | \\  / |  /  \\  | |  | |");
                System.out.println("        | . ` | |  | | |\\/| | / /\\ \\ | |  | |");
                System.out.println("        | |\\  | |__| | |  | |/ ____ \\| |__| |");
                System.out.println("        |_| \\_|\\____/|_|  |_/_/    \\_\\_____/ ");
                System.out.println("======Navigating Open Minds Across Destinations======\n");
                System.out.println("[1] Register");
                System.out.println("[2] Login");
                System.out.println("[3] Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        registerUser();
                        break;
                    case 2:
                        loginUser();
                        break;
                    case 3:
                        System.exit(0);
                }
            } else {
                System.out.println("         _   _  ____  __  __          _____  ");
                System.out.println("        | \\ | |/ __ \\|  \\/  |   /\\   |  __ \\ ");
                System.out.println("        |  \\| | |  | | \\  / |  /  \\  | |  | |");
                System.out.println("        | . ` | |  | | |\\/| | / /\\ \\ | |  | |");
                System.out.println("        | |\\  | |__| | |  | |/ ____ \\| |__| |");
                System.out.println("        |_| \\_|\\____/|_|  |_/_/    \\_\\_____/ ");
                System.out.println("======Navigating Open Minds Across Destinations======\n");
                System.out.println("[1] Plan a Trip");
                System.out.println("[2] View Trip History");
                System.out.println("[3] Logout");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                switch (choice) {
                    case 1:
                        planTrip();
                        break;
                    case 2:
                        viewTripHistory();
                        clearScreen();
                        break;
                    case 3:
                        currentUser = null;
                        break;
                }
            }
        }
    }

    private static void registerUser() {
        clearScreen();
        System.out.println("Register a new user:");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Full Name: ");
        String fullName = scanner.nextLine();

        UserDAO.registerUser(username, password, email, fullName);
    }

    private static void loginUser() {
        clearScreen();
        System.out.println("Login:");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        currentUser = UserDAO.authenticateUser(username, password);
        if (currentUser != null) {
            clearScreen();
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    private static void planTrip() {
        clearScreen();


        List<String> countries = chooseCountries();

        List<String> accommodations = chooseAccommodations();

        List<String> transports = chooseTransportation();

        List<String> activities = chooseActivities();

        double totalPrice = calculateTotalPrice(accommodations, transports, activities);
        double totalCarbonFootprint = calculateTotalCarbonFootprint(accommodations, transports, activities);

        displayTripPlan(countries, accommodations, transports, activities, totalPrice, totalCarbonFootprint);

        Trip trip = new Trip(0, currentUser.getUserId(), countries, accommodations, transports, activities, totalPrice, totalCarbonFootprint);
        TripDAO.saveTrip(trip);

        System.out.println("\nPress Enter to return to the main menu.");
        scanner.nextLine();
        clearScreen();
    }

    private static void displayTripPlan(List<String> countries, List<String> accommodations, List<String> transports, List<String> activities, double totalPrice, double totalCarbonFootprint) {
        clearScreen();

        System.out.println("Trip Plan Summary:");
        System.out.println("Countries: " + String.join(", ", countries));
        System.out.println("Accommodations: " + String.join(", ", accommodations));
        System.out.println("Transportation: " + String.join(", ", transports));
        System.out.println("Activities: " + String.join(", ", activities));
        System.out.println("Total Price: " + totalPrice);
        System.out.println("Total Carbon Footprint: " + totalCarbonFootprint);
    }

    private static List<String> chooseCountries() {
        List<String> countries = new ArrayList<>();
        List<String> countryNames = getCountryNames();
        System.out.println("Select countries (Enter comma-separated numbers to choose):");
        for (int i = 0; i < countryNames.size(); i++) {
            System.out.println("["+ (i + 1) + "] " + countryNames.get(i));
        }

        String[] selectedCountryIndexes = scanner.nextLine().split(",");
        for (String index : selectedCountryIndexes) {
            int countryIndex = Integer.parseInt(index.trim()) - 1;
            if (countryIndex >= 0 && countryIndex < countryNames.size()) {
                countries.add(countryNames.get(countryIndex));
            }
        }

        return countries;
    }

    private static List<String> getCountryNames() {
        List<String> countryNames = new ArrayList<>();
        String query = "SELECT country_name FROM Countries";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                countryNames.add(rs.getString("country_name"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving countries: " + e.getMessage());
        }
        return countryNames;
    }

    private static List<String> chooseAccommodations() {
        List<String> accommodations = new ArrayList<>();
        List<String> accommodationNames = getAccommodationNames();
        System.out.println("Select accommodations (Enter comma-separated numbers to choose):");
        for (int i = 0; i < accommodationNames.size(); i++) {
            System.out.println("["+ (i + 1) + "] " + accommodationNames.get(i));
        }

        String[] selectedAccommodationIndexes = scanner.nextLine().split(",");
        for (String index : selectedAccommodationIndexes) {
            int accommodationIndex = Integer.parseInt(index.trim()) - 1;
            if (accommodationIndex >= 0 && accommodationIndex < accommodationNames.size()) {
                accommodations.add(accommodationNames.get(accommodationIndex));
            }
        }

        return accommodations;
    }

    private static List<String> getAccommodationNames() {
        List<String> accommodationNames = new ArrayList<>();
        String query = "SELECT accommodation_name FROM Accommodations";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                accommodationNames.add(rs.getString("accommodation_name"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving accommodations: " + e.getMessage());
        }
        return accommodationNames;
    }

    private static List<String> chooseTransportation() {
        List<String> transports = new ArrayList<>();
        List<String> transportTypes = getTransportTypes();
        System.out.println("Select transportation (Enter comma-separated numbers to choose):");
        for (int i = 0; i < transportTypes.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + transportTypes.get(i));
        }

        String[] selectedTransportIndexes = scanner.nextLine().split(",");
        for (String index : selectedTransportIndexes) {
            int transportIndex = Integer.parseInt(index.trim()) - 1;
            if (transportIndex >= 0 && transportIndex < transportTypes.size()) {
                transports.add(transportTypes.get(transportIndex));
            }
        }

        return transports;
    }

    private static List<String> getTransportTypes() {
        List<String> transportTypes = new ArrayList<>();
        String query = "SELECT transport_type FROM Transportation";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                transportTypes.add(rs.getString("transport_type"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving transportation: " + e.getMessage());
        }
        return transportTypes;
    }

    private static List<String> chooseActivities() {
        List<String> activities = new ArrayList<>();
        List<String> activityNames = getActivityNames();
        System.out.println("Select activities (Enter comma-separated numbers to choose):");
        for (int i = 0; i < activityNames.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + activityNames.get(i));
        }

        String[] selectedActivityIndexes = scanner.nextLine().split(",");
        for (String index : selectedActivityIndexes) {
            int activityIndex = Integer.parseInt(index.trim()) - 1;
            if (activityIndex >= 0 && activityIndex < activityNames.size()) {
                activities.add(activityNames.get(activityIndex));
            }
        }

        return activities;
    }

    private static List<String> getActivityNames() {
        List<String> activityNames = new ArrayList<>();
        String query = "SELECT activity_name FROM Activities";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                activityNames.add(rs.getString("activity_name"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving activities: " + e.getMessage());
        }
        return activityNames;
    }

    private static double calculateTotalPrice(List<String> accommodations, List<String> transports, List<String> activities) {
        double totalPrice = 0.0;

        totalPrice += calculatePriceFromDatabase(accommodations, "Accommodations");
        totalPrice += calculatePriceFromDatabase(transports, "Transportation");
        totalPrice += calculatePriceFromDatabase(activities, "Activities");

        return totalPrice;
    }

    private static double calculatePriceFromDatabase(List<String> items, String tableName) {
        double totalPrice = 0.0;
        for (String item : items) {
            String query = "SELECT price FROM " + tableName + " WHERE " + tableName.substring(0, tableName.length() - 1) + "_name = ?";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, item);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    totalPrice += rs.getDouble("price");
                }
            } catch (SQLException e) {
                System.out.println("Error calculating total price: " + e.getMessage());
            }
        }
        return totalPrice;
    }

    private static double calculateTotalCarbonFootprint(List<String> accommodations, List<String> transports, List<String> activities) {
        double totalCarbonFootprint = 0.0;

        totalCarbonFootprint += calculateCarbonFootprintFromDatabase(accommodations, "Accommodations");
        totalCarbonFootprint += calculateCarbonFootprintFromDatabase(transports, "Transportation");
        totalCarbonFootprint += calculateCarbonFootprintFromDatabase(activities, "Activities");

        return totalCarbonFootprint;
    }

    private static double calculateCarbonFootprintFromDatabase(List<String> items, String tableName) {
        double totalCarbonFootprint = 0.0;
        for (String item : items) {
            String query = "SELECT carbon_footprint FROM " + tableName + " WHERE " + tableName.substring(0, tableName.length() - 1) + "_name = ?";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, item);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    totalCarbonFootprint += rs.getDouble("carbon_footprint");
                }
            } catch (SQLException e) {
                System.out.println("Error calculating total carbon footprint: " + e.getMessage());
            }
        }
        return totalCarbonFootprint;
    }

    private static void clearScreen() {
        System.out.println("\n".repeat(50));
    }

    private static void viewTripHistory() {
        List<Trip> trips = TripDAO.getTripHistory(currentUser);
        clearScreen();

        if (trips.isEmpty()) {
            System.out.println("You have no trip history.");
        } else {
            for (Trip trip : trips) {
                System.out.println("Trip ID: " + trip.getTripId());
                System.out.println("Countries: " + String.join(", ", trip.getCountries()));
                System.out.println("Accommodations: " + String.join(", ", trip.getAccommodations()));
                System.out.println("Transportation: " + String.join(", ", trip.getTransports()));
                System.out.println("Activities: " + String.join(", ", trip.getActivities()));
                System.out.println("Total Price: " + trip.getTotalPrice());
                System.out.println("Total Carbon Footprint: " + trip.getTotalCarbonFootprint());
                System.out.println("-------------------------");
            }
        }

        System.out.println("\nPress Enter to go back to the menu.");
        scanner.nextLine();
        clearScreen();
    }
}
