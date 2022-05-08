package com.nagy.finalproject;

import java.util.ArrayList;
import java.util.Set;

public interface IJobPostManager {

    JobPosting retrieveJobPost(int id);
    Set<JobPosting> retrieveJobPostSet();

}
