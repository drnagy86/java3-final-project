package com.nagy.finalproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class JobPostManagerFakeTest {

    private IJobPostManager jobPostManager = null;

    @BeforeEach
    void setUp() {
        jobPostManager = new JobPostManagerFake();
    }

    @Test
    void testRetrieveJobPostReturnsJobPosting() {
        // arrange
        final int expectedID = 100004;
        JobPosting actualJobPosting = null;
        int actualID = 0;

        // act
        actualJobPosting = jobPostManager.retrieveJobPost(expectedID);
        actualID = actualJobPosting.getId();

        // assert
        assertEquals(expectedID, actualID);
    }

    @Test
    void testRetrieveJobPostThrowsExceptionIfNotFound() {
        // arrange
        final int test = -1;
        final String expected = JobPostManagerFake.ERROR_MESSAGE_NO_JOB_FOUND;
        String actual = null;

        // act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->{jobPostManager.retrieveJobPost(test);});
        actual = exception.getMessage();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void testRetrieveJobPostSetReturnsCorrectCount(){
        // arrange
        final int expected = 11;
        int actual = 0;
        Set<JobPosting> actualJobPostings = null;

        // act
        actualJobPostings = jobPostManager.retrieveJobPostSet();
        actual = actualJobPostings.size();

        // assert
        assertEquals(expected, actual);

    }
}