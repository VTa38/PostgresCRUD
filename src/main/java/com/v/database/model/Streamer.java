package com.v.database.model;

public class Streamer {
    // Модель таблицы "Streamers"
    private int id;
    private String name;
    private int followers;

    public Streamer(){

    }

    public Streamer(int id, String name, int followers) {
        this.id = id;
        this.name = name;
        this.followers = followers;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getId() {return id;}

    public String getName() {return name;}

    public int getFollowers() {return followers;}

    @Override
    public String toString() {
        return "Streamer {" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", followers = " + followers +
                '}';
    }
}