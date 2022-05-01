package com.example.bookmybook.Models;

public class BookModel {
    private String name,author,Description;
    private int BookID,BookCnt;

    public BookModel(String name, String author, String description, int bookID, int bookCnt) {
        this.name = name;
        this.author = author;
        Description = description;
        BookID = bookID;
        BookCnt = bookCnt;
    }

    public BookModel(String name, String author, String description, int bookCnt) {
        this.name = name;
        this.author = author;
        Description=description;
        BookCnt=bookCnt;
    }

    public BookModel() {
    }

    @Override
    public String toString() {
        return "BookModel{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", Description='" + Description + '\'' +
                ", BookID=" + BookID +
                ", BookCnt=" + BookCnt +
                '}';
    }

    public int getBookCnt() {
        return BookCnt;
    }

    public void setBookCnt(int bookCnt) {
        BookCnt = bookCnt;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
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
