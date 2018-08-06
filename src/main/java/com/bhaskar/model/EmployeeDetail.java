package com.bhaskar.model;

public class EmployeeDetail {
    private int empId;
    private String fname;
    private String lname;
    private String mail;

    public EmployeeDetail(){

    }

    public EmployeeDetail(String fname, String lname, String mail, int empId) {
        this.empId = empId;
        this.fname = fname;
        this.lname = lname;
        this.mail = mail;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }
}
