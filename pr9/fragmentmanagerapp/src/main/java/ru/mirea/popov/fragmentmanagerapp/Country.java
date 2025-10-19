package ru.mirea.popov.fragmentmanagerapp;

public class Country {
    private String name;
    private String description;

    public Country(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
