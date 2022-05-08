package com.nagy.finalproject;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class JobPostManagerFake implements IJobPostManager {

    private static Set<JobPosting> fakeJobList = new TreeSet<>();

    private static int NEXT_AVAILABLE_ID = 100001;

    public static final String ERROR_MESSAGE_NO_JOB_FOUND = "Can not find the job";

    public JobPostManagerFake() {
        // populate db if not already
        if (fakeJobList.isEmpty()){
            fakeJobList.add(new JobPosting());
            fakeJobList.add(new JobPosting(
                    getNextAvailableId(),
                    true,
                    LocalDate.now(),
                    "Baker 1",
                    "Coralville",
                    "Iowa",
                    false,
                    "Baking",
                    "No experience",
                    "Tier 1",
                    "Lead Baker",
                    new BigDecimal(15),
                    "Entry level Cupcake Baker for Coralvile, Iowa",
                    "Following the recipe, bakes cupcakes, assist lead baker as needed",
                    "Lift 20-30lbs, flow directions from a recipe independently"
            ));

            fakeJobList.add(new JobPosting(
                    getNextAvailableId(),
                    true,
                    LocalDate.now(),
                    "Baker 2",
                    "Coralville",
                    "Iowa",
                    true,
                    "Baking",
                    "2-3 years",
                    "Tier 2",
                    "Lead Baker",
                    new BigDecimal(45000),
                    "Cupcake Baker for Coralvile, Iowa",
                    "Following the recipe, bake cupcakes, assist lead baker as needed",
                    "Lift 20-30lbs, flow directions from a recipe independently, 2-3 of food-service experience"
            ));

            fakeJobList.add(new JobPosting(
                    getNextAvailableId(),
                    true,
                    LocalDate.now(),
                    "Baker 3",
                    "Coralville",
                    "Iowa",
                    true,
                    "Baking",
                    "4-5 years",
                    "Tier 3",
                    "Store Manager, CEO",
                    new BigDecimal(65000),
                    "Entry level Cupcake Baker for Coralvile, Iowa",
                    "Following the recipe, bakes cupcakes. Manages other bakers. Creates new flavors.",
                    "Lift 20-30lbs, flow directions from a recipe independently, 4-5 years food-service experience, management experience a plus."
            ));

            fakeJobList.add(new JobPosting(
                    getNextAvailableId(),
                    true,
                    LocalDate.now(),
                    "Decorator 1",
                    "Coralville",
                    "Iowa",
                    false,
                    "Baking",
                    "No experience",
                    "Tier 1",
                    "Lead Baker",
                    new BigDecimal(15),
                    "Entry level Cupcake Decorator for Coralvile, Iowa",
                    "Following the recipe, decorates cupcakes, assist lead baker as needed",
                    "Lift 20-30lbs, decorate cupcakes based on the picture or description from lead"
            ));

            fakeJobList.add(new JobPosting(
                    getNextAvailableId(),
                    true,
                    LocalDate.now(),
                    "Decorator 2",
                    "Coralville",
                    "Iowa",
                    true,
                    "Baking",
                    "2-3 years",
                    "Tier 2",
                    "Lead Baker",
                    new BigDecimal(45000),
                    "Cupcake Decorator for Coralvile, Iowa",
                    "Following the recipe, bake cupcakes, assist lead baker as needed",
                    "Lift 20-30lbs, decorate cupcakes based on the picture or description from lead"
            ));

            fakeJobList.add(new JobPosting(
                    getNextAvailableId(),
                    true,
                    LocalDate.now(),
                    "Decorator 3",
                    "Coralville",
                    "Iowa",
                    true,
                    "Baking",
                    "4-5 years",
                    "Tier 3",
                    "Store Manager, CEO",
                    new BigDecimal(65000),
                    "Entry level Cupcake Decorator for Coralvile, Iowa",
                    "Following the recipe, bakes cupcakes. Manages other bakers. Creates new flavors.",
                    "Lift 20-30lbs, decorate cupcakes based on the picture or description, create new designs for cupcake decorating, 4-5 years food-service experience, management experience a plus."
            ));

            fakeJobList.add(new JobPosting(
                    getNextAvailableId(),
                    true,
                    LocalDate.now(),
                    "Cashier 1",
                    "Coralville",
                    "Iowa",
                    false,
                    "Front House",
                    "No experience",
                    "Tier 1",
                    "Store Manager",
                    new BigDecimal(15),
                    "Entry level Cupcake Decorator for Coralvile, Iowa",
                    "Assist guests with cupcake selection, box cupcakes, use cash register for payments",
                    "Lift 20-30lbs, decorate cupcakes based on the picture or description from lead"
            ));

            fakeJobList.add(new JobPosting(
                    getNextAvailableId(),
                    true,
                    LocalDate.now(),
                    "Cashier 2",
                    "Coralville",
                    "Iowa",
                    true,
                    "Front House",
                    "2-3 years",
                    "Tier 2",
                    "Store Manager",
                    new BigDecimal(45000),
                    "Cupcake Decorator for Coralvile, Iowa",
                    "Assist guests with cupcake selection, box cupcakes, use cash register for payments",
                    "Lift 20-30lbs, decorate cupcakes based on the picture or description from lead"
            ));


            fakeJobList.add(new JobPosting(
                    getNextAvailableId(),
                    true,
                    LocalDate.now(),
                    "Store Manager",
                    "Coralville",
                    "Iowa",
                    true,
                    "Management",
                    "4-5 years",
                    "Tier 3",
                    "CEO",
                    new BigDecimal(75000),
                    "Cupcake Decorator for Coralvile, Iowa",
                    "Assist guests with cupcake selection, box cupcakes, use cash register for payments, manage cashiers, bakers, decorators",
                    "Lift 20-30lbs, 4-5 years in food-service, management experience a plus, passion for cupcakery"
            ));

            fakeJobList.add(new JobPosting(
                    getNextAvailableId(),
                    true,
                    LocalDate.now(),
                    "Delivery",
                    "Coralville",
                    "Iowa",
                    false,
                    "Baking",
                    "No experience",
                    "Tier 1",
                    "Store Manager",
                    new BigDecimal(15),
                    "Part time delivery driver for Coralvile, Iowa",
                    "Deliver cupcakes around town when needed",
                    "Lift 20-30lbs, driver's license"
            ));
        }
    }

    @Override
    public JobPosting retrieveJobPost(int id) {

        JobPosting jobToReturn = null;
        for(JobPosting jobPosting: fakeJobList){
            int tempID = jobPosting.getId();
            if (tempID == id){
                jobToReturn = jobPosting;
                break;
            }
        }

        if (jobToReturn == null) {
            throw new IllegalArgumentException(ERROR_MESSAGE_NO_JOB_FOUND);
        }

        return  jobToReturn;

    }

    @Override
    public Set<JobPosting> retrieveJobPostSet() {
        return fakeJobList;
    }

    private int getNextAvailableId(){
        return NEXT_AVAILABLE_ID++;
    }
}
