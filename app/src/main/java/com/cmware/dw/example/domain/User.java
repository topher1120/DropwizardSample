package com.cmware.dw.example.domain;

/**
 * User class representing a user of the system.
 */
public class User
{
    private final String username;

    public User(String username)
    {
        this.username = username;
    }

    public String getUsername()
    {
        return username;
    }
}
