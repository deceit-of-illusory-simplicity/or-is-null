package com.evilcorp.orisnull.filter;

public class BookFilterBuilder {
    private String name;
    private String country;
    private String author;
    private Integer rating;

    public BookFilterBuilder name(String name) {
        this.name = name;
        return this;
    }

    public BookFilterBuilder country(String country) {
        this.country = country;
        return this;
    }

    public BookFilterBuilder author(String author) {
        this.author = author;
        return this;
    }

    public BookFilterBuilder rating(Integer rating) {
        this.rating = rating;
        return this;
    }

    public BookFilter build() {
        return new BookFilter(name, country, author, rating);
    }

    public static BookFilterBuilder book() {
        return new BookFilterBuilder();
    }
}