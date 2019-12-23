package com.maciejg.moviecatalogservice.models;

import java.util.List;

public class UserRating {
    private List<Rating> userReting;

    public List<Rating> getUserReting() {
        return userReting;
    }

    public void setUserReting(List<Rating> userReting) {
        this.userReting = userReting;
    }
}
