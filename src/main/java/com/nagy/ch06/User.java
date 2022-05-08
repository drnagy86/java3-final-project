package com.nagy.ch06;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User implements Serializable, Comparable<User> {
    private long userId;
    private String username;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDate birthday;
    private Instant lastUpdated;
    private BigDecimal balance;
    private String password;
    private Map<String, Boolean> permissions;
    private List<String> roles;

    public static int STRING_MIN_LENGTH = 0;
    public static int STRING_MAX_LENGTH = 50;
    public static String ERROR_MSG_TOO_SHORT = "Name fields cannot be empty";
    public static String ERROR_MSG_TOO_LONG = "Name fields cannot be longer than 50 characters";
    public static String ERROR_MSG_FOR_EMAIL = "The email address must be like \"example@email.com\" ";
    public static String ERROR_MSG_FOR_PHONE = "Phone number must be a real phone number, like \"123-456-7890\" ";
    public static String ERROR_MSG_FOR_PASSWORD = "Password must have one: digit, lower case letter, upper case letter, special character. Must be between 8 and 20 characters.";


    // source for email validation helper
    // https://stackoverflow.com/questions/8204680/java-regex-email
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    public User(){
        this.userId = 0L;
        this.username = "";
        this.firstName = "F";
        this.lastName = "F";
        this.phoneNumber = "";
        this.birthday = LocalDate.now();
        this.lastUpdated = Instant.now();
        this.balance = BigDecimal.ZERO;
        this.password = "";

        Map<String, Boolean> permissions = new HashMap<>();
        permissions.put("active", true);
        permissions.put("admin", false);

        this.permissions = permissions;

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
        this.password = "newUser";


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
        this.password = "newUser";
    }

    public User(long userId, String username, String firstName, String lastName, String phoneNumber, String password) {
        this.userId = userId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.birthday = LocalDate.now();
        this.lastUpdated = Instant.now();
        this.balance = BigDecimal.ZERO;
        this.password = password;
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
        if (!validateInputMinLength(username)) throw new IllegalArgumentException(ERROR_MSG_TOO_SHORT);
        else if (!validateInputMaxLength(username)) throw new IllegalArgumentException(ERROR_MSG_TOO_LONG);
        else if (!validateEmail(username)) throw new IllegalArgumentException(ERROR_MSG_FOR_EMAIL);
        else {
            this.username = username;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {

        if (!validateInputMinLength(firstName)) throw new IllegalArgumentException(ERROR_MSG_TOO_SHORT);
        else if (!validateInputMaxLength(firstName)) throw new IllegalArgumentException(ERROR_MSG_TOO_LONG);
        else {
            this.firstName = firstName;
        }

    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {

        if (!validateInputMinLength(lastName)) throw new IllegalArgumentException(ERROR_MSG_TOO_SHORT);
        else if (!validateInputMaxLength(lastName)) throw new IllegalArgumentException(ERROR_MSG_TOO_LONG);
        else {
            this.lastName = lastName;
        }

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

        phoneNumber = formatPhoneNumber(phoneNumber);

        if (!validatePhoneNumber(phoneNumber)) throw new IllegalArgumentException(ERROR_MSG_FOR_PHONE);
        else {this.phoneNumber = phoneNumber;}

    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (!validatePassword(password)) throw new IllegalArgumentException(ERROR_MSG_FOR_PASSWORD);
        else this.password = password;
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

    private boolean validateInputMinLength(String string){
        return string.length() > 0;
    }

    private boolean validateInputMaxLength(String string){
        return string.length() < 50;
    }

    private boolean validatePhoneNumber(String phoneNumber){
        boolean result = false;

        try{
            Long.parseLong(phoneNumber);
            result = phoneNumber.length() == 10;
        } catch (Exception e){
            result = false;
        }

        return result;
    }

    private String formatPhoneNumber(String phoneNumber){

        phoneNumber = phoneNumber.replaceAll("[^\\d.]", "");

        return phoneNumber;
    }


    // source for email validation helper
    // https://stackoverflow.com/questions/8204680/java-regex-email
    private static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }


    // source code for password checker
    // https://www.geeksforgeeks.org/how-to-validate-a-password-using-regular-expressions-in-java/
    private static boolean validatePassword(String password)
    {
        boolean result = false;
        if (password == null) {
            result = false;
        } else {
            /*
            ^ represents starting character of the string.
            (?=.*[0-9]) represents a digit must occur at least once.
            (?=.*[a-z]) represents a lower case alphabet must occur at least once.
            (?=.*[A-Z]) represents an upper case alphabet that must occur at least once.
            (?=.*[@#$%^&-+=()] represents a special character that must occur at least once.
            (?=\\S+$) white spaces don’t allowed in the entire string.
            .{8, 20} represents at least 8 characters and at most 20 characters.
            $ represents the end of the string.
             */

            String regex = "^(?=.*[0-9])"
                    + "(?=.*[a-z])(?=.*[A-Z])"
                    + "(?=.*[@#$%^&+=])"
                    + "(?=\\S+$).{8,20}$";

            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(password);
            result = m.matches();
        }
        return result;

    }
}
