package com.example.bookmybook.Models;

public class BookModel {
    private String name,author,image,available_count;
    private int BookID;
    public BookModel(String name, String author, int bookID,String image,String available_count) {
        this.name = name;
        this.author = author;
        this.image=image;
        BookID = bookID;
        this.available_count=available_count;
    }

    public String getAvailable_count() {
        return available_count;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public int getBookID() {
        return BookID;
    }

    public void setBookID(int bookID) {
        BookID = bookID;
    }
}
