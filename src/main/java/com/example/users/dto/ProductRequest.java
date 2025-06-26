package com.example.users.dto;

public class ProductRequest {
    private int id;
    private String name;
    private String release_date;
    private int userId;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRelease_date() {
        return release_date;
    }

    public int getUserId() {
        return userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }
}