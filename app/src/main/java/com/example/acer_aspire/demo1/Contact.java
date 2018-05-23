package com.example.acer_aspire.demo1;

public class Contact {

    private String name;
    private String number;
    private String key;

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;

    }

    public Contact(){

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
