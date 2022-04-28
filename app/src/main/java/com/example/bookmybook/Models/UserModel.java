package com.example.bookmybook.Models;



import java.util.Vector;

public class UserModel {
    private String name,email,password;
    private Vector<Integer> IssueID;
    public UserModel(){}
    public UserModel(String name, String email, String password, Vector<Integer> IssueID) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.IssueID = IssueID;
    }
    public int CountOfCurrentBooks(){
        return IssueID.size();
    }
    public Vector<Integer> CurrentIssuedBook(){
        return  IssueID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Vector<Integer> getIssueID() {
        return IssueID;
    }

    public void setIssueID(Vector<Integer> issueID) {
        IssueID = issueID;
    }
}
