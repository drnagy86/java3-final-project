package com.nagy.finalproject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class JobPosting implements Comparable<JobPosting>, Serializable {
    private int id;
    private boolean active;
    private LocalDate dateCreated;
    private Date dateCreatedAsDate;
    private String title;
    private String city;
    private String state;
    private boolean fullTime;
    private String department;
    private String experience;
    private String wageCategory;
    private String reportsTo;
    private BigDecimal salary;
    private String jobStatement;
    private String jobDuties;
    private String jobRequirements;


    // default values
    public static final int DEFAULT_ID = 0;
    public static final boolean DEFAULT_ACTIVE = true;
    public static final LocalDate DEFAULT_DATE_CREATED = LocalDate.now();
    public static final String DEFAULT_TITLE = "Default job title";
    public static final String DEFAULT_CITY = "Coralville";
    public static final String DEFAULT_STATE = "Iowa";
    public static final boolean DEFAULT_FULL_TIME = true;
    public static final String DEFAULT_DEPARTMENT = "HR";
    public static final String DEFAULT_EXPERIENCE = "Entry";
    public static final String DEFAULT_WAGE_CATEGORY = "Tier 1";
    public static final String DEFAULT_REPORTS_TO = "Mark";
    public static final BigDecimal DEFAULT_SALARY = new BigDecimal(40000.00);
    public static final String DEFAULT_JOB_STATEMENT = "Default job statement";
    public static final String DEFAULT_JOB_DUTIES = "Default job duties";
    public static final String DEFAULT_JOB_REQUIREMENTS = "Default job requirements";




    public static final String ERROR_MESSAGE_STRING_TOO_SHORT = "This is too short";
    public static final String ERROR_MESSAGE_STRING_TOO_LONG = "This is too long";
    public static final String ERROR_MESSAGE_DATE_BEFORE_NOW = "This date is in the past";
    public static final String ERROR_MESSAGE_DATE_YEAR_FROM_NOW = "This date is too far into the future";
    public static final String ERROR_MESSAGE_SALARY_TOO_HIGH = "This salary is too high";
    public static final String ERROR_MESSAGE_SALARY_TOO_LOW = "This salary is too low";

    public static final int MAX_SHORT_STRING_LENGTH = 50;
    public static final int MAX_LONG_STRING_LENGTH = 255;

    public static final BigDecimal MAX_SALARY = new BigDecimal(100000);
    public static final BigDecimal MIN_SALARY = new BigDecimal(15);

    public JobPosting() {
        this.id = DEFAULT_ID;
        this.active = DEFAULT_ACTIVE;
        this.dateCreated = DEFAULT_DATE_CREATED;
        this.dateCreatedAsDate = dateCreatedAsDate();
        this.title = DEFAULT_TITLE;
        this.city = DEFAULT_CITY;
        this.state = DEFAULT_STATE;
        this.fullTime = DEFAULT_FULL_TIME;
        this.department = DEFAULT_DEPARTMENT;
        this.experience = DEFAULT_EXPERIENCE;
        this.wageCategory = DEFAULT_WAGE_CATEGORY;
        this.reportsTo = DEFAULT_REPORTS_TO;
        this.salary = DEFAULT_SALARY;
        this.jobStatement = DEFAULT_JOB_STATEMENT;
        this.jobDuties = DEFAULT_JOB_DUTIES;
        this.jobRequirements = DEFAULT_JOB_REQUIREMENTS;
    }

    public JobPosting(
            int id,
            boolean active,
            LocalDate dateCreated,
            String title,
            String city,
            String state,
            boolean fullTime,
            String department,
            String experience,
            String wageCategory,
            String reportsTo,
            BigDecimal salary,
            String jobStatement,
            String jobDuties,
            String jobRequirements) {

        // string validations
        validateStringLength(title, MAX_SHORT_STRING_LENGTH);
        validateStringLength(city, MAX_SHORT_STRING_LENGTH);
        validateStringLength(state, MAX_SHORT_STRING_LENGTH);
        validateStringLength(department, MAX_SHORT_STRING_LENGTH);
        validateStringLength(experience, MAX_SHORT_STRING_LENGTH);
        validateStringLength(wageCategory, MAX_SHORT_STRING_LENGTH);
        validateStringLength(reportsTo, MAX_SHORT_STRING_LENGTH);
        validateStringLength(jobStatement, MAX_LONG_STRING_LENGTH);
        validateStringLength(jobDuties, MAX_LONG_STRING_LENGTH);
        validateStringLength(jobRequirements, MAX_LONG_STRING_LENGTH);

        // date
        validateDate(dateCreated);

        // salary
        validateSalary(salary);

        this.id = id;
        this.active = active;
        this.dateCreated = dateCreated;
        this.dateCreatedAsDate = dateCreatedAsDate();
        this.title = title;
        this.city = city;
        this.state = state;
        this.fullTime = fullTime;
        this.department = department;
        this.experience = experience;
        this.wageCategory = wageCategory;
        this.reportsTo = reportsTo;
        this.salary = salary;
        this.jobStatement = jobStatement;
        this.jobDuties = jobDuties;
        this.jobRequirements = jobRequirements;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDate getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        validateDate(dateCreated);
        this.dateCreated = dateCreated;
    }

    public Date getDateCreatedAsDate() {
        return dateCreatedAsDate();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        validateStringLength(title, MAX_SHORT_STRING_LENGTH);
        this.title = title;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        validateStringLength(city, MAX_SHORT_STRING_LENGTH);
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        validateStringLength(state, MAX_SHORT_STRING_LENGTH);
        this.state = state;
    }

    public boolean isFullTime() {
        return fullTime;
    }

    public void setFullTime(boolean fullTime) {
        this.fullTime = fullTime;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        validateStringLength(department, MAX_SHORT_STRING_LENGTH);
        this.department = department;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        validateStringLength(experience, MAX_SHORT_STRING_LENGTH);
        this.experience = experience;
    }

    public String getWageCategory() {
        return wageCategory;
    }

    public void setWageCategory(String wageCategory) {
        validateStringLength(wageCategory, MAX_SHORT_STRING_LENGTH);
        this.wageCategory = wageCategory;
    }

    public String getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(String reportsTo) {
        validateStringLength(reportsTo, MAX_SHORT_STRING_LENGTH);
        this.reportsTo = reportsTo;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        validateSalary(salary);
        this.salary = salary;
    }

    public String getJobStatement() {
        return jobStatement;
    }

    public void setJobStatement(String jobStatement) {
        validateStringLength(jobStatement, MAX_LONG_STRING_LENGTH);
        this.jobStatement = jobStatement;
    }

    public String getJobDuties() {
        return jobDuties;
    }

    public void setJobDuties(String jobDuties) {
        validateStringLength(jobDuties, MAX_LONG_STRING_LENGTH);
        this.jobDuties = jobDuties;
    }

    public String getJobRequirements() {
        return jobRequirements;
    }

    public void setJobRequirements(String jobRequirements) {
        validateStringLength(jobRequirements, MAX_LONG_STRING_LENGTH);
        this.jobRequirements = jobRequirements;
    }

    public Date dateCreatedAsDate(){

        Date newDate = new Date();

        // https://stackoverflow.com/questions/33066904/localdate-to-java-util-date-and-vice-versa-simplest-conversion

        newDate =java.util.Date.from(this.dateCreated.atStartOfDay(
                // Specify time zone using proper name in `continent/region` format, never 3-4 letter pseudo-zones such as “PST”, “CST”, “IST”.
                ZoneId.of( "America/Chicago" )).toInstant());

        return newDate;
    }

    @Override
    public String toString() {
        return "JobPosting{" +
                "id=" + id +
                ", active=" + active +
                ", dateCreated=" + dateCreated +
                ", title='" + title + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", fullTime=" + fullTime +
                ", department='" + department + '\'' +
                ", experience='" + experience + '\'' +
                ", wageCategory='" + wageCategory + '\'' +
                ", salary=" + salary +
                '}';
    }

    @Override
    public int compareTo(JobPosting o) {
        int result = 0;

        result = this.getTitle().compareToIgnoreCase(o.getTitle());

        return result;
    }

    private void validateStringLength(String string, int maxLength){
        if (string.length() == 0){
            throw new IllegalArgumentException(ERROR_MESSAGE_STRING_TOO_SHORT);
        }

        if (string.length() > maxLength){
            throw new IllegalArgumentException(ERROR_MESSAGE_STRING_TOO_LONG);
        }
    }

    private void validateDate(LocalDate date){
        if (date.isBefore(LocalDate.now())){
            throw new IllegalArgumentException(ERROR_MESSAGE_DATE_BEFORE_NOW);
        }
        if (date.isAfter(LocalDate.now().plusYears(1))){
            throw new IllegalArgumentException(ERROR_MESSAGE_DATE_YEAR_FROM_NOW);
        }
    }

    private void validateSalary(BigDecimal salary){
        if (salary.compareTo(MAX_SALARY) > 0){
            throw new IllegalArgumentException(ERROR_MESSAGE_SALARY_TOO_HIGH);
        }
        if (salary.compareTo(MIN_SALARY) < 0){
            throw new IllegalArgumentException(ERROR_MESSAGE_SALARY_TOO_LOW);
        }
    }

}
