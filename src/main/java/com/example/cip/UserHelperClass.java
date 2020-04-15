package com.example.cip;

public class UserHelperClass {
    String name,registerNumber,CGPA,email,password;

    public UserHelperClass() {
    }

    public UserHelperClass(String name, String registerNumber, String CGPA, String email, String password) {
        this.name = name;
        this.registerNumber=registerNumber;
        this.CGPA=CGPA;
        this.email=email;
        this.password=password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }

    public String getCGPA() {
        return CGPA;
    }

    public void setCGPA(String CGPA) {
        this.CGPA = CGPA;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
