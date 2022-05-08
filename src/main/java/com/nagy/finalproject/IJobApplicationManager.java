package com.nagy.finalproject;

import java.util.List;

public interface IJobApplicationManager {

    JobApplication retrieveJobApplication(int jobApplicationID);
    List<JobApplication> retrieveJobApplications();
    JobApplication createJobApplication(JobApplication jobApplication);

}

