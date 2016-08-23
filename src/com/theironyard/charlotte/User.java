package com.theironyard.charlotte;

import java.util.ArrayList;

/**
 * Created by meekinsworks on 8/23/16.
 */
public class User {
    String username;
    String password;
    ArrayList<Message> messages = new ArrayList<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;


    }
}