package com.nagy.finalproject;

import com.nagy.ch06.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JobApplicationManagerFakeTest {

    private JobApplicationManagerFake jobApplicationManager = null;
    private JobPostManagerFake jobPostManagerFake = null;

    @BeforeEach
    void setUp(){
        jobApplicationManager = new JobApplicationManagerFake();
        jobPostManagerFake = new JobPostManagerFake();
    }

    @Test
    void retrieveJobApplication() {
        // arrange
        final int jobApplicationID = 100001;
        final int expected = 100001;
        JobApplication actualJobApplication = null;
        int actual = 0;

        // act
        actualJobApplication = jobApplicationManager.retrieveJobApplication(jobApplicationID);
        actual = actualJobApplication.getJobApplicationID();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void retrieveJobApplicationThrowsExceptionWhenNoneToBeFound() {
        // arrange
        final int jobApplicationID = -1;
        final String expected = JobApplicationManagerFake.ERROR_MESSAGE_NO_APPLICATION_FOUND;
        String actual = null;

        // act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> jobApplicationManager.retrieveJobApplication(jobApplicationID));
        actual = exception.getMessage();

        assertEquals(expected, actual);
    }

    @Test
    void retrieveJobApplications() {
        // arrange
        final int expected = 2;
        int actual = 0;

        // act
        List<JobApplication> jobApplications = jobApplicationManager.retrieveJobApplications();
        actual = jobApplications.size();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void createJobApplication() {
        // arrange
        JobApplication addApplication = new JobApplication(
                100002,
                jobPostManagerFake.retrieveJobPost(100002),
                Users.THE_USER_DB.get(0),
                JobApplication.DEFAULT_ADDRESS,
                JobApplication.DEFAULT_CITY,
                JobApplication.DEFAULT_STATE,
                JobApplication.DEFAULT_POSTAL,
                JobApplication.DEFAULT_COVER_LETTER,
                JobApplication.DEFAULT_RESUME
        );

        JobApplication actualApplication = null;

        final int expected = 100002;
        int actual = 0;

        // act
        actualApplication = jobApplicationManager.createJobApplication(addApplication);
        actual = actualApplication.getJobApplicationID();

        // assert
        assertEquals(expected, actual);

    }
}