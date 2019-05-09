package com.model;

public class User_data {
    private String login;
    private int age;
    private String mail;
    private double height;
    private double weight;
    private int normCal;
    private int normFats;
    private int normProteins;
    private int normCarbohydrates;
    public User_data(){};

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getNormCal() {
        return normCal;
    }

    public void setNormCal(int normCal) {
        this.normCal = normCal;
    }

    public int getNormFats() {
        return normFats;
    }

    public void setNormFats(int normFats) {
        this.normFats = normFats;
    }

    public int getNormProteins() {
        return normProteins;
    }

    public void setNormProteins(int normProteins) {
        this.normProteins = normProteins;
    }

    public int getNormCarbohydrates() {
        return normCarbohydrates;
    }

    public void setNormCarbohydrates(int normCarbohydrates) {
        this.normCarbohydrates = normCarbohydrates;
    }
}
