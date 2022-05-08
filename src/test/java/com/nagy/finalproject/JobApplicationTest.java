package com.nagy.finalproject;

import com.nagy.ch06.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JobApplicationTest {

    private JobApplication jobApplication = null;

    @BeforeEach
    void setUp() {
        jobApplication = new JobApplication();
    }

    @Test
    void testGetJobApplicationIDSucceeds() {
        // arrange
        final  int expected = 0;
        int actual = 0;

        // act
        actual = jobApplication.getJobApplicationID();

        assertEquals(expected, actual);
    }

    @Test
    void setJobApplicationID() {
        // arrange
        final int expected = 100000;
        int actual = 0;

        // act
        jobApplication.setJobApplicationID(100000);
        actual = jobApplication.getJobApplicationID();

        // assert
        assertEquals(expected, actual);

    }

    @Test
    void getJobPosting() {
        // arrange
        JobPosting expected = new JobPosting();
        JobPosting actual = null;

        // act
        actual = jobApplication.getJobPosting();


        // assert
        assertEquals(expected.getId(), actual.getId());
    }

    @Test
    void setJobPosting() {
        // arrange
        JobPosting expected = new JobPosting();
        JobPosting actual = null;

        // act
        jobApplication.setJobPosting(new JobPosting());

        actual = jobApplication.getJobPosting();

        // assert
        assertEquals(expected.getId(), actual.getId());
    }

    @Test
    void testJobPostingSetNewID() {
        // arrange
        final String expected = "title";
        JobPosting actual = null;

        // act
        jobApplication.getJobPosting().setTitle(expected);
        actual = jobApplication.getJobPosting();

        // assert
        assertEquals(expected, actual.getTitle());
    }

    @Test
    void getApplicant() {
        // arrange
        User expected = new User();
        User actual = null;

        // act
        actual = jobApplication.getApplicant();


        // assert
        assertEquals(expected.getFirstName(), actual.getFirstName());
    }

    @Test
    void testSetApplicantFirstName() {
        // arrange
        final String expected = "test";
        String actual = null;

        // act
        jobApplication.getApplicant().setFirstName("test");
        actual = jobApplication.getApplicant().getFirstName();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void getAddress() {
        // arrange
        final String expected = JobApplication.DEFAULT_ADDRESS;
        String actual = "";

        // assert
        actual = jobApplication.getAddress();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void setAddress() {
        // arrange
        final String expected = "Test";
        String actual = "";

        // assert
        jobApplication.setAddress("Test");
        actual = jobApplication.getAddress();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void getCity() {
        // arrange
        final String expected = JobApplication.DEFAULT_CITY;
        String actual = "";

        // assert
        actual = jobApplication.getCity();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void setCity() {
        // arrange
        final String expected = "Test";
        String actual = "";

        // assert
        jobApplication.setCity("Test");
        actual = jobApplication.getCity();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void getState() {
    }

    @Test
    void setState() {
    }

    @Test
    void getPostal() {
    }

    @Test
    void setPostal() {
    }

    @Test
    void getCoverLetter() {
    }

    @Test
    void setCoverLetter() {
    }

    @Test
    void getResume() {
    }

    @Test
    void setResume() {
    }

    @Test
    void compareTo() {
    }
}