package com.StudentsDemo.Students.stud;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class StudentDisplay {

    @Id
    private int Id;
    private String lname;
    private String fname;
    private String mname;
    private String email;
    private int age;
    private String YrLvl;

    public StudentDisplay() {
    }

    public StudentDisplay(int id, String lname, String fname, String mname, String email, int age, String yrLvl) {
        Id = id;
        this.lname = lname;
        this.fname = fname;
        this.mname = mname;
        this.email = email;
        this.age = age;
        YrLvl = yrLvl;
    }

    // Getters and Setters
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getYrLvl() {
        return YrLvl;
    }

    public void setYrLvl(String yrLvl) {
        YrLvl = yrLvl;
    }

    @Override
    public String toString() {
        return "StudentDisplay{" +
                "Id=" + Id +
                ", lname='" + lname + '\'' +
                ", fname='" + fname + '\'' +
                ", mname='" + mname + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", YrLvl='" + YrLvl + '\'' +
                '}';
    }
}
