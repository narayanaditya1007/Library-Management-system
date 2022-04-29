package com.example.bookmybook.Models;

public class BookModel {
    private String name,author,Description;
    private int BookID;
    public BookModel(String name, String author, String description, int bookID) {
        this.name = name;
        this.author = author;
        Description = description;
        BookID = bookID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getBookID() {
        return BookID;
    }

    public void setBookID(int bookID) {
        BookID = bookID;
    }
}
