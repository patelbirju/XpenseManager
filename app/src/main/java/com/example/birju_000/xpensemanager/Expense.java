package com.example.birju_000.xpensemanager;

/**
 * Created by Bpatel0967 on 11/23/2017.
 */

public class Expense {


    private int expenseId;
    private String date;
    private  double amount;
    private  String location;
    private  String description;
    private  String category;

    public  Expense(){

    }

    public Expense(String date, double amount, String location, String description, String category){
        this.date = date;
        this.amount = amount;
        this.location = location;
        this.description = description;
        this.category = category;
    }

    //getters and setters
    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
