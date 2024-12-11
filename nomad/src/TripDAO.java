import java.sql.*;
import java.util.*;

public abstract class TripDAO {
    public static void saveTrip(Trip trip) {
        String query = "INSERT INTO Trips (user_id, total_price, total_carbon_footprint) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, trip.getUserId());
            stmt.setDouble(2, trip.getTotalPrice());
            stmt.setDouble(3, trip.getTotalCarbonFootprint());
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int tripId = generatedKeys.getInt(1);
                    trip.setTripId(tripId);

                    saveTripDetails(trip);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error saving trip: " + e.getMessage());
        }
    }

    private static void saveTripDetails(Trip trip) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            for (String country : trip.getCountries()) {
                String countryQuery = "SELECT country_id FROM Countries WHERE country_name = ?";
                try (PreparedStatement stmt = connection.prepareStatement(countryQuery)) {
                    stmt.setString(1, country);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        int countryId = rs.getInt("country_id");
                        insertTripCountry(connection, trip.getTripId(), countryId);
                    }
                }
            }

            for (String accommodation : trip.getAccommodations()) {
                String accommodationQuery = "SELECT accommodation_id FROM Accommodations WHERE accommodation_name = ?";
                try (PreparedStatement stmt = connection.prepareStatement(accommodationQuery)) {
                    stmt.setString(1, accommodation);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        int accommodationId = rs.getInt("accommodation_id");
                        insertTripAccommodation(connection, trip.getTripId(), accommodationId);
                    }
                }
            }

            for (String transport : trip.getTransports()) {
                String transportQuery = "SELECT transport_id FROM Transportation WHERE transport_type = ?";
                try (PreparedStatement stmt = connection.prepareStatement(transportQuery)) {
                    stmt.setString(1, transport);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        int transportId = rs.getInt("transport_id");
                        insertTripTransportation(connection, trip.getTripId(), transportId);
                    }
                }
            }

            for (String activity : trip.getActivities()) {
                String activityQuery = "SELECT activity_id FROM Activities WHERE activity_name = ?";
                try (PreparedStatement stmt = connection.prepareStatement(activityQuery)) {
                    stmt.setString(1, activity);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        int activityId = rs.getInt("activity_id");
                        insertTripActivity(connection, trip.getTripId(), activityId);
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error saving trip details: " + e.getMessage());
        }
    }

    private static void insertTripCountry(Connection connection, int tripId, int countryId) throws SQLException {
        String query = "INSERT INTO Trip_Countries (trip_id, country_id) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, tripId);
            stmt.setInt(2, countryId);
            stmt.executeUpdate();
        }
    }

    private static void insertTripAccommodation(Connection connection, int tripId, int accommodationId) throws SQLException {
        String query = "INSERT INTO Trip_Accommodations (trip_id, accommodation_id) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, tripId);
            stmt.setInt(2, accommodationId);
            stmt.executeUpdate();
        }
    }

    private static void insertTripTransportation(Connection connection, int tripId, int transportId) throws SQLException {
        String query = "INSERT INTO Trip_Transportation (trip_id, transport_id) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, tripId);
            stmt.setInt(2, transportId);
            stmt.executeUpdate();
        }
    }

    private static void insertTripActivity(Connection connection, int tripId, int activityId) throws SQLException {
        String query = "INSERT INTO Trip_Activities (trip_id, activity_id) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, tripId);
            stmt.setInt(2, activityId);
            stmt.executeUpdate();
        }
    }


    public static List<Trip> getTripHistory(User user) {
        List<Trip> trips = new ArrayList<>();
        String query = "SELECT * FROM Trips WHERE user_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, user.getUserId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int tripId = rs.getInt("trip_id");
                double totalPrice = rs.getDouble("total_price");
                double totalCarbonFootprint = rs.getDouble("total_carbon_footprint");

                List<String> countries = getTripCountries(tripId);

                List<String> accommodations = getTripAccommodations(tripId);

                List<String> transportation = getTripTransportation(tripId);

                List<String> activities = getTripActivities(tripId);

                trips.add(new Trip(tripId, user.getUserId(), countries, accommodations, transportation, activities, totalPrice, totalCarbonFootprint));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving trip history: " + e.getMessage());
        }

        return trips;
    }


    private static List<String> getTripCountries(int tripId) {
        List<String> countries = new ArrayList<>();
        String query = "SELECT c.country_name FROM Trip_Countries tc JOIN Countries c ON tc.country_id = c.country_id WHERE tc.trip_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, tripId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                countries.add(rs.getString("country_name"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving trip countries: " + e.getMessage());
        }

        return countries;
    }


    private static List<String> getTripAccommodations(int tripId) {
        List<String> accommodations = new ArrayList<>();
        String query = "SELECT a.accommodation_name FROM Trip_Accommodations ta JOIN Accommodations a ON ta.accommodation_id = a.accommodation_id WHERE ta.trip_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, tripId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                accommodations.add(rs.getString("accommodation_name"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving trip accommodations: " + e.getMessage());
        }

        return accommodations;
    }


    private static List<String> getTripTransportation(int tripId) {
        List<String> transportation = new ArrayList<>();
        String query = "SELECT t.transport_type FROM Trip_Transportation tt JOIN Transportation t ON tt.transport_id = t.transport_id WHERE tt.trip_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, tripId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                transportation.add(rs.getString("transport_type"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving trip transportation: " + e.getMessage());
        }

        return transportation;
    }


    private static List<String> getTripActivities(int tripId) {
        List<String> activities = new ArrayList<>();
        String query = "SELECT a.activity_name FROM Trip_Activities ta JOIN Activities a ON ta.activity_id = a.activity_id WHERE ta.trip_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, tripId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                activities.add(rs.getString("activity_name"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving trip activities: " + e.getMessage());
        }

        return activities;
    }
}
