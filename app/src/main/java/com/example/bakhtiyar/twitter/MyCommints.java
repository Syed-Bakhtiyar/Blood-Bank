package com.example.bakhtiyar.twitter;

/**
 * Created by Bakhtiyar on 2/26/2017.
 */
public class MyCommints {
    String commint;

    String name;

    String push;

    public MyCommints(String commint, String push,String name) {
        this.commint = commint;
        this.push = push;
        this.name = name;
    }

    public MyCommints() {
    }

    public String getName() {
        return name;
    }

    public String getCommint() {
        return commint;
    }

    public String getPush() {
        return push;
    }
}
