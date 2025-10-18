package ru.mirea.popov.recyclerviewapp.model;

public class Country {
    private String name;
    private String flag;
    private int population;

    public Country(String name, String flag, int population) {
        this.name = name;
        this.flag = flag;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public String getFlag() {
        return flag;
    }

    public int getPopulation() {
        return population;
    }
}
