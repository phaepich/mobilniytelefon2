package ru.mirea.popov.domain.models;

public class Movie {
    private final String title;
    private final String description;
    private final int posterResId;

    public Movie(String title, String description, int posterResId) {
        this.title = title;
        this.description = description;
        this.posterResId = posterResId;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public int getPosterResId() { return posterResId; }
}
