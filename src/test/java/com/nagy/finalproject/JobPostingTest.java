package com.nagy.finalproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAmount;

import static org.junit.jupiter.api.Assertions.*;

class JobPostingTest {

    private JobPosting jobPosting = null;

    @BeforeEach
    void setUp(){
        jobPosting = new JobPosting();
    }


    @Test
    void getIdSucceeds() {
//         arrange
        final int expected = 0;
        int actual = 0;
//
        // act
        actual = jobPosting.getId();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void setId() {
        //         arrange
        final int expected = 100001;
        int actual = 0;
//
        // act
        jobPosting.setId(100001);
        actual = jobPosting.getId();

        // assert
        assertEquals(expected, actual);

    }

    @Test
    void isActive() {
        //         arrange
        final boolean expected = true;
        boolean actual = false;
//
        // act
        actual = jobPosting.isActive();

        // assert
        assertEquals(expected, actual);

    }

    @Test
    void setActive() {
        //         arrange
        final boolean expected = true;
        boolean actual = false;
//
        // act
        jobPosting.setActive(expected);
        actual = jobPosting.isActive();

        // assert
        assertEquals(expected, actual);

    }

    @Test
    void getDateCreated() {
        // arrange
        final LocalDate expected = JobPosting.DEFAULT_DATE_CREATED;
        LocalDate actual = null;

        // act
        actual = jobPosting.getDateCreated();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void setDateCreatedSuccess() {
        // arrange
        final LocalDate expected = LocalDate.now().plusDays(1);
        LocalDate actual = null;

        // act
        jobPosting.setDateCreated(expected);
        actual = jobPosting.getDateCreated();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void setDateCreatedThrowsExceptionIfBeforeNow() {
        // arrange
        final LocalDate test = LocalDate.now().minusDays(1);
        final String expected = JobPosting.ERROR_MESSAGE_DATE_BEFORE_NOW;
        String actual = null;

        // act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->{jobPosting.setDateCreated(test);});
        actual = exception.getMessage();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void setDateCreatedThrowsExceptionIfNextYear() {
        // arrange
        final LocalDate test = LocalDate.now().plusYears(1).plusDays(1);
        final String expected = JobPosting.ERROR_MESSAGE_DATE_YEAR_FROM_NOW;
        String actual = null;

        // act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->{jobPosting.setDateCreated(test);});
        actual = exception.getMessage();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void getTitle() {
        // arrange
        final String expected = JobPosting.DEFAULT_TITLE;
        String actual = "";

        // act
        actual = jobPosting.getTitle();

        // assert
        assertEquals(expected, actual);

    }

    @Test
    void setTitle() {
        // arrange
        final String expected = "Test";
        String actual = "";

        // act
        jobPosting.setTitle(expected);
        actual = jobPosting.getTitle();

        // assert
        assertEquals(expected, actual);

    }

    @Test
    void setTitleThrowsExceptionTooShort() {
        // arrange
        final String test = "";
        final String expected = JobPosting.ERROR_MESSAGE_STRING_TOO_SHORT;
        String actual = null;

        // act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->{jobPosting.setTitle(test);});
        actual = exception.getMessage();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void setTitleThrowsExceptionTooLong() {
        // arrange
        final String test = "dSvJe7FpKdxga0e2SfPRoqhDmS5Xcq45w31wxqBPenw3l8EoZIt";
        final String expected = JobPosting.ERROR_MESSAGE_STRING_TOO_LONG;
        String actual = null;

        // act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->{jobPosting.setTitle(test);});
        actual = exception.getMessage();

        // assert
        assertEquals(expected, actual);
    }


    @Test
    void getCity() {
        // arrange
        final String expected = JobPosting.DEFAULT_CITY;
        String actual = "";

        // act
        actual = jobPosting.getCity();

        // assert
        assertEquals(expected, actual);


    }

    @Test
    void setCity() {
        // arrange
        final String expected = "Test";
        String actual = "";

        // act
        jobPosting.setCity(expected);
        actual = jobPosting.getCity();

        // assert
        assertEquals(expected, actual);

    }

    @Test
    void getState() {
        // arrange
        final String expected = JobPosting.DEFAULT_STATE;
        String actual = "";

        // act
        actual = jobPosting.getState();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void setState() {
        // arrange
        final String expected = "Test";
        String actual = "";

        // act
        jobPosting.setState(expected);
        actual = jobPosting.getState();

        // assert
        assertEquals(expected, actual);

    }

    @Test
    void isFullTime() {
        // arrange
        final boolean expected = true;
        boolean actual = false;

        // act
        actual = jobPosting.isFullTime();

        // assert
        assertEquals(expected, actual);

    }

    @Test
    void setFullTime() {
        // arrange
        final boolean expected = true;
        boolean actual = false;

        // act
        jobPosting.setFullTime(true);
        actual = jobPosting.isFullTime();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void getDepartment() {
        // arrange
        final String expected = JobPosting.DEFAULT_DEPARTMENT;
        String actual = "";

        // act
        actual = jobPosting.getDepartment();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void setDepartment() {
        // arrange
        final String expected = JobPosting.DEFAULT_DEPARTMENT;
        String actual = "";

        // act
        jobPosting.setDepartment(expected);
        actual = jobPosting.getDepartment();

        // assert
        assertEquals(expected, actual);

    }

    @Test
    void getExperience() {
        // arrange
        final String expected = JobPosting.DEFAULT_EXPERIENCE;
        String actual = "";

        // act
        actual = jobPosting.getExperience();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void setExperience() {

        // arrange
        final String expected = JobPosting.DEFAULT_EXPERIENCE;
        String actual = "";

        // act
        jobPosting.setExperience(expected);
        actual = jobPosting.getExperience();

        // assert
        assertEquals(expected, actual);

    }

    @Test
    void getWageCategory() {
        // arrange
        final String expected = JobPosting.DEFAULT_WAGE_CATEGORY;
        String actual = "";

        // act
        actual = jobPosting.getWageCategory();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void setWageCategory() {
        // arrange
        final String expected = JobPosting.DEFAULT_WAGE_CATEGORY;
        String actual = "";

        // act
        jobPosting.setWageCategory(expected);
        actual = jobPosting.getWageCategory();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void getReportsTo() {
        // arrange
        final String expected = JobPosting.DEFAULT_REPORTS_TO;
        String actual = "";

        // act
        actual = jobPosting.getReportsTo();

        // assert
        assertEquals(expected, actual);

    }

    @Test
    void setReportsTo() {
        // arrange
        final String expected = JobPosting.DEFAULT_REPORTS_TO;
        String actual = "";

        // act
        jobPosting.setReportsTo(expected);
        actual = jobPosting.getReportsTo();

        // assert
        assertEquals(expected, actual);

    }

    @Test
    void getSalary() {
        // arrange
        final BigDecimal expected = JobPosting.DEFAULT_SALARY;
        BigDecimal actual = null;

        // act
        actual = jobPosting.getSalary();

        // assert
        assertEquals(expected, actual);

    }

    @Test
    void setSalary() {
        // arrange
        final BigDecimal expected = new BigDecimal( 60000);
        BigDecimal actual = null;

        // act
        jobPosting.setSalary(expected);
        actual = jobPosting.getSalary();

        // assert
        assertEquals(expected, actual);

    }

    @Test
    void setSalaryThrowsExceptionTooLow() {
        // arrange
        final BigDecimal test = new BigDecimal(14);
        final String expected = JobPosting.ERROR_MESSAGE_SALARY_TOO_LOW;
        String actual = null;

        // act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->{jobPosting.setSalary(test);});
        actual = exception.getMessage();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void setSalaryThrowsExceptionTooHigh() {
        // arrange
        final BigDecimal test = new BigDecimal(100001);;
        final String expected = JobPosting.ERROR_MESSAGE_SALARY_TOO_HIGH;
        String actual = null;

        // act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->{jobPosting.setSalary(test);});
        actual = exception.getMessage();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void getJobStatement() {
        // arrange
        final String expected = JobPosting.DEFAULT_JOB_STATEMENT;
        String actual = "";

        // act
        actual = jobPosting.getJobStatement();

        // assert
        assertEquals(expected, actual);

    }

    @Test
    void setJobStatement() {
        // arrange
        final String expected = "Test";
        String actual = "";

        // act
        jobPosting.setJobStatement(expected);
        actual = jobPosting.getJobStatement();

        // assert
        assertEquals(expected, actual);


    }

    @Test
    void setJobStatementThrowsExceptionTooLong() {
        // arrange
        final String test = "6pgtGhiw7IBZcai6YxrhmMsnxpkMY0wxUVuZX26Cooh7Sq3wn36hvylGRNSnSNTVQEQAd7VmoyU9IxBlrcJCx3zIN131Jzb0sUg7Jp8QN7o05l5zERc1YLGT2nNQ1SRoNSbgcimIkUjc3pIChTk93UYOM7KPK9d4LpEOsKW5ERZ2xAwxhUDVF16LLy67btc8UrnrnhkBjOJpfJQaGKiuKeCEJJGdsusl1RO2trcWlPSegVwOXmdxp3xUBKaHXVBL";
        final String expected = JobPosting.ERROR_MESSAGE_STRING_TOO_LONG;
        String actual = null;

        // act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->{jobPosting.setJobStatement(test);});
        actual = exception.getMessage();

        // assert
        assertEquals(expected, actual);
    }



    @Test
    void getJobDuties() {
        // arrange
        final String expected = JobPosting.DEFAULT_JOB_DUTIES;
        String actual = "";

        // act
        actual = jobPosting.getJobDuties();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void setJobDuties() {
        // arrange
        final String expected = "test";
        String actual = "";

        // act
        jobPosting.setJobDuties(expected);
        actual = jobPosting.getJobDuties();

        // assert
        assertEquals(expected, actual);

    }

    @Test
    void getJobRequirements() {
        // arrange
        final String expected = JobPosting.DEFAULT_JOB_REQUIREMENTS;
        String actual = "";

        // act
        actual = jobPosting.getJobRequirements();

        // assert
        assertEquals(expected, actual);

    }

    @Test
    void setJobRequirements() {
        // arrange
        final String expected = "Test";
        String actual = "";

        // act
        jobPosting.setJobRequirements(expected);
        actual = jobPosting.getJobRequirements();

        // assert
        assertEquals(expected, actual);

    }

    @Test
    void testCompareToJobPostingBeforeOther() {

        JobPosting other = new JobPosting();
        other.setTitle("Z");

        int result = jobPosting.compareTo(other);

        assertTrue(result < 0);
    }

    @Test
    void testCompareToJobPostingAfterOther() {

        JobPosting other = new JobPosting();
        other.setTitle("A");

        int result = jobPosting.compareTo(other);

        assertTrue(result > 0);
    }

    @Test
    void testCompareToJobPostingSame() {

        JobPosting other = new JobPosting();
        int result = jobPosting.compareTo(other);
        assertTrue(result == 0);

    }


}