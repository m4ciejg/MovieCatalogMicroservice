package com.maciejg.moviecatalogservice.models;

public class Movie {
    private String moveId;
    private String name;

    public Movie() {

    }

    public Movie(String moveId, String name) {
        this.moveId = moveId;
        this.name = name;
    }

    public String getMoveId() {
        return moveId;
    }

    public void setMoveId(String moveId) {
        this.moveId = moveId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

