package com.evilcorp.orisnull.filter;

public class BookFilter {
    private final String name;
    private final String country;
    private final String author;
    private final Integer rating;

    public BookFilter(String name, String country, String author, Integer rating) {
        this.name = name;
        this.country = country;
        this.author = author;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getRating() {
        return rating;
    }

    public boolean isEmpty() {
        return rating == null
                && author == null
                && country == null
                && name == null;
    }
}
