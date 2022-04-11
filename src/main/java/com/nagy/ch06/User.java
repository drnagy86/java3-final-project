package com.nagy.ch06;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class User implements Serializable, Comparable<User> {
    private long userId;
    private String username;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDate birthday;
    private Instant lastUpdated;
    private BigDecimal balance;



    private Map<String, Boolean> permissions =  new HashMap<>();

    public User(){
        this.userId = 0L;
        this.username = "";
        this.firstName = "";
        this.lastName = "";
        this.phoneNumber = "";
        this.birthday = LocalDate.now();
        this.lastUpdated = Instant.now();
        this.balance = BigDecimal.ZERO;

    }

    public User(long userId, String username, String firstName, String lastName) {
        this.userId = userId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = "";
        this.birthday = LocalDate.now();
        this.lastUpdated = Instant.now();
        this.balance = BigDecimal.ZERO;


    }


    public User(long userId, String username, String firstName, String lastName, String phoneNumber, LocalDate birthday, Instant lastUpdated, BigDecimal balance) {
        this.userId = userId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.lastUpdated = lastUpdated;
        this.balance = balance;
    }


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Map<String, Boolean> getPermissions() {
        return permissions;
    }

    public void setPermissions(Map<String, Boolean> permissions) {
        this.permissions = permissions;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Instant getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Instant lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Date getLastUpdatedDate(){
        return Date.from(lastUpdated);
    }

    public Date getBirthdayDate() {
        return java.sql.Date.valueOf(birthday);
    }

    @Override
    public int compareTo(User other) {
        int last = lastName.compareTo(other.lastName);
        if (last == 0) {
            return firstName.compareTo(other.firstName);
        }
        return last;
    }

    @Override
    public String toString() {
        return
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'';
    }


}
