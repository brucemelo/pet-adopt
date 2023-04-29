package com.github.brucemelo.petadopt.integration;

public class CatApi {

    public CatApi(String breed) {
        this.breed = breed;
    }

    private String breed;

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

}
