package com.nagy.finalproject;

import com.nagy.ch06.Users;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JobApplicationManagerFake implements IJobApplicationManager {

    private static List<JobApplication> fakeJobApplicationList = new ArrayList<>();
    private static int NEXT_AVAILABLE_ID = 100001;
    private JobPostManagerFake jobPostManagerFake = new JobPostManagerFake();

    public static final String ERROR_MESSAGE_NO_APPLICATION_FOUND =  "No application found for the job application ID";

    public JobApplicationManagerFake() {
        if (fakeJobApplicationList.isEmpty()){

            Users.populateDB();

            fakeJobApplicationList.add(new JobApplication());

            fakeJobApplicationList.add(new JobApplication(
                    getNextAvailableId(),
                    jobPostManagerFake.retrieveJobPost(100001),
                    Users.THE_USER_DB.get(0),
                    JobApplication.DEFAULT_ADDRESS,
                    JobApplication.DEFAULT_CITY,
                    JobApplication.DEFAULT_STATE,
                    JobApplication.DEFAULT_POSTAL,
                    JobApplication.DEFAULT_COVER_LETTER,
                    JobApplication.DEFAULT_RESUME
            ));
        }
    }

    @Override
    public JobApplication retrieveJobApplication(int jobApplicationID) {
        JobApplication application = null;

        for( JobApplication app: fakeJobApplicationList){
            if (app.getJobApplicationID() == jobApplicationID){
                application = app;
                break;
            }
        }

        if (application == null){
            throw new IllegalArgumentException(ERROR_MESSAGE_NO_APPLICATION_FOUND);
        }

        return application;
    }

    @Override
    public List<JobApplication> retrieveJobApplications() {
        return fakeJobApplicationList;
    }

    @Override
    public JobApplication createJobApplication(JobApplication jobApplication) {

        jobApplication.setJobApplicationID(getNextAvailableId());
        fakeJobApplicationList.add(jobApplication);
        return jobApplication;
    }

    private int getNextAvailableId(){
        return NEXT_AVAILABLE_ID++;
    }
}
