package com.model;

public class Statistic {
    long id;

    String login;
    String date;
    int currCal;
    int currFats;
    int currProteins;
    int currCarbohydrates;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public int getCurrCal() {
        return currCal;
    }

    public void setCurrCal(int currCal) {
        this.currCal = currCal;
    }

    public int getCurrFats() {
        return currFats;
    }

    public void setCurrFats(int currFats) {
        this.currFats = currFats;
    }

    public int getCurrProteins() {
        return currProteins;
    }

    public void setCurrProteins(int currProteins) {
        this.currProteins = currProteins;
    }

    public int getCurrCarbohydrates() {
        return currCarbohydrates;
    }

    public void setCurrCarbohydrates(int currCarbohydrates) {
        this.currCarbohydrates = currCarbohydrates;
    }
}
