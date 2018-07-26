package com.bhaskar.model;

public class User {
    private String uname;
    private int role;

    public User() {
    }

    public User(String uname, int role) {
        this.uname = uname;
        this.role = role;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
