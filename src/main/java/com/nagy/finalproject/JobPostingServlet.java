package com.nagy.finalproject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;
import java.util.Set;

@WebServlet(name = "JobPostingServlet", value = "/final/jobs")
public class JobPostingServlet extends HttpServlet {

    private IJobPostManager jobPostManager = null;

    @Override
    public void init() throws ServletException {
        jobPostManager = new JobPostManagerFake();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String go = request.getParameter("go");
        if (go == null){
            go = "list";
        }
        // add log in
        switch (go){
            case "details":
                jobPostingDetails(request, response);
                break;
            case "list":
            default:
                viewAllJobPostings(request,response);
                break;
        }
    }

    private void applyToJob(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        request.setAttribute("errorMessage", "Works");
        viewAllJobPostings(request, response);
    }

    private void jobPostingDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String stringID = request.getParameter("id");
        JobPosting job = null;

        try {
            int id = Integer.parseInt(stringID);
            job = jobPostManager.retrieveJobPost(id);
        } catch (Exception ex){
            request.setAttribute("errorMessage", "No job posting found with this ID");
            viewAllJobPostings(request, response);
        }

        request.setAttribute("job", job);

        request.getRequestDispatcher("/WEB-INF/final/details.jsp").forward(request,response);
    }

    private void viewAllJobPostings(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // try catch for errors
        Set<JobPosting> jobPostings = jobPostManager.retrieveJobPostSet();

        int page = 1;
        int itemsPerPage = 4;
        int maxPages = jobPostings.size() / itemsPerPage;
        if (jobPostings.size() % itemsPerPage != 0) maxPages ++;

        String pageStr = request.getParameter("page");
        if(pageStr != null && !pageStr.equals("")) {
            try {
                page = Integer.parseInt(pageStr);
                if(page < 1){
                    page = 1;
                } else if(page > maxPages) {
                    page = maxPages;
                }
            } catch(NumberFormatException e) {
                page = 1;
            }
        }

        int beginItem = (page - 1) * itemsPerPage;
        int endItem = beginItem + itemsPerPage - 1;

        request.setAttribute("begin", beginItem);
        request.setAttribute("end", endItem);
        request.setAttribute("maxPages", maxPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("jobPostSet", jobPostings);

        request.getRequestDispatcher("/WEB-INF/final/jobposting.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        applyToJob(request,response);
    }
}
