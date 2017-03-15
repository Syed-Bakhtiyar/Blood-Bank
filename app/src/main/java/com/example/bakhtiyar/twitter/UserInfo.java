package com.example.bakhtiyar.twitter;

/**
 * Created by Bakhtiyar on 2/26/2017.
 */
public class UserInfo {

    String name;

    String email;

    String bloodgroup;

    String uid;


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public String getUid() {
        return uid;
    }


    public UserInfo() {

    }

    public UserInfo(String name, String email, String bloodgroup, String uid) {

        this.name = name;
        this.email = email;
        this.bloodgroup = bloodgroup;
        this.uid = uid;

    }
}
