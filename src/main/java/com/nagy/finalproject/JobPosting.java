package com.nagy.finalproject;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class JobPosting implements Comparable<JobPosting> {
    private int id;
    private boolean active;
    private LocalDate dateCreated;
    private String title;
    private String city;
    private String state;
    private boolean fullTime;
    private String department;
    private String experience;
    private String wageCategory;
    private double salary;

    // default values


    public JobPosting() {
    }

    public JobPosting(int id, boolean active, LocalDate dateCreated, String title, String city, String state, boolean fullTime, String department, String experience, String wagecategory, double salary) {
        this.id = id;
        this.active = active;
        this.dateCreated = dateCreated;
        this.title = title;
        this.city = city;
        this.state = state;
        this.fullTime = fullTime;
        this.department = department;
        this.experience = experience;
        this.wageCategory = wagecategory;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public boolean isActive() {
        return active;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public String getTitle() {
        return title;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public boolean isFullTime() {
        return fullTime;
    }

    public String getDepartment() {
        return department;
    }

    public String getExperience() {
        return experience;
    }

    public String getWageCategory() {
        return wageCategory;
    }

    public double getSalary() {
        return salary;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setFullTime(boolean fullTime) {
        this.fullTime = fullTime;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setWageCategory(String wageCategory) {
        this.wageCategory = wageCategory;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    private void newDateCreated(){

        Date newDate = new Date();

        // https://stackoverflow.com/questions/33066904/localdate-to-java-util-date-and-vice-versa-simplest-conversion


        newDate =java.util.Date.from(this.dateCreated.atStartOfDay(
                // Specify time zone using proper name in `continent/region` format, never 3-4 letter pseudo-zones such as “PST”, “CST”, “IST”.
                ZoneId.of( "America/Montreal" )).toInstant());


    }


    @Override
    public int compareTo(JobPosting o) {
        int result;
        result = this.title.compareTo(o.title);
        return result;
    }
}
