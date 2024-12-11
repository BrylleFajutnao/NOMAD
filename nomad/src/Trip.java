import java.util.List;

public class Trip {
    private int tripId;
    private int userId;
    private List<String> countries;
    private List<String> accommodations;
    private List<String> transports;
    private List<String> activities;
    private double totalPrice;
    private double totalCarbonFootprint;

    public Trip(int tripId, int userId, List<String> countries, List<String> accommodations,
                List<String> transports, List<String> activities, double totalPrice, double totalCarbonFootprint) {
        this.tripId = tripId;
        this.userId = userId;
        this.countries = countries;
        this.accommodations = accommodations;
        this.transports = transports;
        this.activities = activities;
        this.totalPrice = totalPrice;
        this.totalCarbonFootprint = totalCarbonFootprint;
    }

    public int getTripId() { return tripId; }
    public void setTripId(int tripId) { this.tripId = tripId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public List<String> getCountries() { return countries; }
    public void setCountries(List<String> countries) { this.countries = countries; }
    public List<String> getAccommodations() { return accommodations; }
    public void setAccommodations(List<String> accommodations) { this.accommodations = accommodations; }
    public List<String> getTransports() { return transports; }
    public void setTransports(List<String> transports) { this.transports = transports; }
    public List<String> getActivities() { return activities; }
    public void setActivities(List<String> activities) { this.activities = activities; }
    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
    public double getTotalCarbonFootprint() { return totalCarbonFootprint; }
    public void setTotalCarbonFootprint(double totalCarbonFootprint) { this.totalCarbonFootprint = totalCarbonFootprint; }

    @Override
    public String toString() {
        return "Trip [tripId=" + tripId + ", userId=" + userId + ", countries=" + countries +
                ", accommodations=" + accommodations + ", transports=" + transports +
                ", activities=" + activities + ", totalPrice=" + totalPrice +
                ", totalCarbonFootprint=" + totalCarbonFootprint + "]";
    }
}
