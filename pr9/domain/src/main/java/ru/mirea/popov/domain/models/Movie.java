package ru.mirea.popov.domain.models;

public class Movie {
    private final String title;
    private final String genre;
    private final int year;

    public Movie(String title, String genre, int year) {
        this.title = title;
        this.genre = genre;
        this.year = year;
    }

    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public int getYear() { return year; }
}
