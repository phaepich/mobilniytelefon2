package ru.mirea.popov.domain.models;

public class WeatherInfo {
    private final String city;
    private final double temperature;
    private final String description;
    private final String iconCode;

    public WeatherInfo(String city, double temperature, String description, String iconCode) {
        this.city = city;
        this.temperature = temperature;
        this.description = description;
        this.iconCode = iconCode;
    }

    public String getCity() { return city; }
    public double getTemperature() { return temperature; }
    public String getDescription() { return description; }
    public String getIconCode() { return iconCode; }
}
