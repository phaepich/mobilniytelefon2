package ru.mirea.popov.data.storage.models;

public class Movie {
    private final String title;
    private final String description;
    private final String posterName; /

    public Movie(String title, String description, String posterName) {
        this.title = title;
        this.description = description;
        this.posterName = posterName;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPosterName() {
        return posterName;
    }
}
