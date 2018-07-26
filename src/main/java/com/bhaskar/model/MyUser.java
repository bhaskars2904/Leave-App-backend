package com.bhaskar.model;

public class MyUser {
    private String uname;
    private String token;
    private int role;

    public MyUser() {
    }

    public MyUser(String uname, String token, int role) {
        this.uname = uname;
        this.token = token;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
