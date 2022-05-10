package com.nagy.finalproject;

import com.nagy.ch06.User;
import com.nagy.helpers.Helpers;
import com.nagy.support.Attachment;

import java.io.Serializable;

public class JobApplication implements Comparable<JobApplication>, Serializable {
    private int jobApplicationID;
    private JobPosting jobPosting;
    private User applicant;
    private String address;
    private String city;
    private String state;
    private int postal;
    private Attachment coverLetter;
    private Attachment resume;

    public static final int DEFAULT_JOB_APP_ID = 0;
    public static final String DEFAULT_ADDRESS = "123 Fake Street";
    public static final String DEFAULT_CITY = "Fake City";
    public static final String DEFAULT_STATE = "Iowa";
    public static final int DEFAULT_POSTAL = 52241;

    public JobApplication() {

        this.jobApplicationID = DEFAULT_JOB_APP_ID;
        this.jobPosting = new JobPosting();
        this.applicant = new User();
        this.address = DEFAULT_ADDRESS;
        this.city = DEFAULT_CITY;
        this.state = DEFAULT_STATE;
        this.postal = DEFAULT_POSTAL;
        this.coverLetter = null;
        this.resume = null;
    }

    public JobApplication(int jobApplicationID, JobPosting jobPosting, User user, String address, String city, String state, int postal, Attachment coverLetter, Attachment resume) {

        Helpers.validateStringLength(address, Helpers.MAX_SHORT_STRING_LENGTH);
        Helpers.validateStringLength(city, Helpers.MAX_SHORT_STRING_LENGTH);
        Helpers.validateStringLength(state, Helpers.MAX_SHORT_STRING_LENGTH);
        Helpers.validatePostal(postal);

        this.jobApplicationID = jobApplicationID;
        this.jobPosting = jobPosting;
        this.applicant = user;
        this.address = address;
        this.city = city;
        this.state = state;
        this.postal = postal;
        this.coverLetter = coverLetter;
        this.resume = resume;
    }

    public int getJobApplicationID() {
        return jobApplicationID;
    }

    public void setJobApplicationID(int jobApplicationID) {
        this.jobApplicationID = jobApplicationID;
    }

    public JobPosting getJobPosting() {
        return jobPosting;
    }

    public void setJobPosting(JobPosting jobPosting) {
        this.jobPosting = jobPosting;
    }

    public User getApplicant() {
        return applicant;
    }

    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        Helpers.validateStringLength(address,Helpers.MAX_SHORT_STRING_LENGTH);
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        Helpers.validateStringLength(city, Helpers.MAX_SHORT_STRING_LENGTH);
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        Helpers.validateStringLength(state, Helpers.MAX_SHORT_STRING_LENGTH);
        this.state = state;
    }

    public int getPostal() {
        return postal;
    }

    public void setPostal(int postal) {
        Helpers.validatePostal(postal);
        this.postal = postal;
    }

    public Attachment getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(Attachment coverLetter) {
        this.coverLetter = coverLetter;
    }

    public Attachment getResume() {
        return resume;
    }

    public void setResume(Attachment resume) {
        this.resume = resume;
    }

    @Override
    public int compareTo(JobApplication o) {
        int last = this.applicant.getLastName().compareTo(o.applicant.getLastName());
        if (last == 0) {
            return this.applicant.getFirstName().compareTo(o.applicant.getFirstName());
        }
        return last;
    }
}
