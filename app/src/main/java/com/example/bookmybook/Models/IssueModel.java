package com.example.bookmybook.Models;

public class IssueModel {
    private String UserEmail,IssueDate,ReturnDate;
    private int BookID,issueID;

    public IssueModel() { ReturnDate=null;}
    public IssueModel(String userEmail, String issueDate, String returnDate, int bookID) {
        UserEmail = userEmail;
        IssueDate = issueDate;
        ReturnDate = returnDate;
        BookID = bookID;

    }

    public IssueModel(String userEmail, String issueDate, String returnDate, int bookID, int issueID) {
        UserEmail = userEmail;
        IssueDate = issueDate;
        ReturnDate = returnDate;
        BookID = bookID;
        this.issueID = issueID;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public void setIssueDate(String issueDate) {
        IssueDate = issueDate;
    }

    public void setReturnDate(String returnDate) {
        ReturnDate = returnDate;
    }

    public void setBookID(int bookID) {
        BookID = bookID;
    }

    public void setIssueID(int issueID) {
        this.issueID = issueID;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public String getIssueDate() {
        return IssueDate;
    }

    public String getReturnDate() {
        return ReturnDate;
    }

    public int getBookID() {
        return BookID;
    }

    public int getIssueID() {
        return issueID;
    }
}
