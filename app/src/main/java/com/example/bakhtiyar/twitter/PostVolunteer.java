package com.example.bakhtiyar.twitter;

/**
 * Created by Syed_Bakhtiyar on 15/03/2017.
 */

public class PostVolunteer {

    String name;

    String push;

    public String getName() {
        return name;
    }

    public String getPush() {
        return push;
    }

    public PostVolunteer() {

    }

    public PostVolunteer(String name, String push) {

        this.name = name;
        this.push = push;
    }
}
