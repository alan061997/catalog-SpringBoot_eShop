package com.cdis.microservice.example.auth.event;

import java.io.Serializable;

public class UserSolvedEvent implements Serializable {
    private String username, lastName, firstName;

    public UserSolvedEvent() {
    }

    public UserSolvedEvent(String username, String lastName, String firstName) {
        this.username = username;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
