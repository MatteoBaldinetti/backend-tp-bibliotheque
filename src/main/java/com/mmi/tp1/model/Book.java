package com.mmi.tp1.model;

import com.mmi.tp1.model.enums.Category;
import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(unique = true)
    private String isbn;

    private int year;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    private Author author;

    public Book() {

    }

    public Book(String title, String isbn, int year, Category category, Author author) {
        this.title = title;
        this.isbn = isbn;
        checkYear(year);
        this.year = year;
        this.category = category;
        this.author = author;
    }

    private void checkYear(int year) {
        if (year < 1450 || year > 2025) {
            throw new IllegalArgumentException("Year must be between 1450 and 2025");
        }
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        checkYear(year);
        this.year = year;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
