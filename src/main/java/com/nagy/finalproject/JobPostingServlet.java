package com.nagy.finalproject;

import com.nagy.ch06.User;
import com.nagy.ch06.Users;
import com.nagy.support.Attachment;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@WebServlet(name = "JobPostingServlet", value = "/final/jobs")
@MultipartConfig (
        fileSizeThreshold = 1_000_000, // 1 MB
        maxFileSize = 5_000_000 //5 MB
)
public class JobPostingServlet extends HttpServlet {

    private IJobPostManager jobPostManager = null;
    private IJobApplicationManager jobApplicationManager = null;

    @Override
    public void init() throws ServletException {
        jobPostManager = new JobPostManagerFake();
        jobApplicationManager = new JobApplicationManagerFake();
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
            case "applications":
                applications(request, response);
                break;
            case "list":
            default:
                viewAllJobPostings(request,response);
                break;
        }
    }

    private void applications(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        if (session.getAttribute("username")== null){
            response.sendRedirect("../support/login");
            session.setAttribute("pageBeforeLogIn", "final/jobs?go=applications");
            return;
        }

        // find user
        String userToFind = (String) session.getAttribute("username");
        User currentUser = null;
        String errors = "";

        for(User user : Users.THE_USER_DB){
            String currentUserName = user.getUsername();
            if (currentUserName.equals(userToFind)){
                currentUser = user;
                break;
            }
        }

        if (currentUser == null){
            //set error message
            errors += "Can not find user information.<br>";
        }

        // find out if user is admin
        boolean isAdmin = false;

        Map<String, Boolean> permissions = currentUser.getPermissions();
        if (!permissions.isEmpty()) isAdmin = permissions.get("admin");



        request.setAttribute("applications", jobApplicationManager.retrieveJobApplications());

        request.setAttribute("isAdmin", isAdmin);
        request.setAttribute("user", currentUser);
        request.setAttribute("errorMessage", errors);
        request.getRequestDispatcher("/WEB-INF/final/applications.jsp").forward(request,response);


    }

    private void applyToJob(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String errors = "";
        String success = "";
        HttpSession session = request.getSession();
        boolean noErrors = true;
        JobApplication jobApplication = null;

        // get everything, create an object, add to list
        // Type var = session.getAttribute("name");
        int jobID = Integer.parseInt(request.getParameter("jobID"));
        String firstName =request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("emailAddress");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String postalString = request.getParameter("postal");
        int postal = 0;
        try {
            postal = Integer.parseInt(postalString);
        } catch (Exception ex) {
            errors += "Postal code must be a number." + ex.getMessage();
            noErrors = false;
        }

        if (noErrors){
            try {
                jobApplication = new JobApplication(
                        0,
                        jobPostManager.retrieveJobPost(jobID),
                        new User(
                                0L,
                                email,
                                firstName,
                                lastName
                        ),
                        address,
                        city,
                        state,
                        postal,
                        new Attachment(),
                        new Attachment()
                );
            } catch (Exception ex){
                errors += ex.getMessage();
                noErrors = false;
            }
        }

        try{
            for(Part part: request.getParts()) {
                if(part.getName().equals("coverLetter") && part != null && part.getSize() > 0) {
                    Attachment attachment = Attachment.processAttachment(part);
                    if(attachment != null) {
                        jobApplication.setCoverLetter(attachment);
                    }
                }
                if(part.getName().equals("resume") && part != null && part.getSize() > 0) {
                    Attachment attachment = Attachment.processAttachment(part);
                    if(attachment != null) {
                        jobApplication.setResume(attachment);
                    }
                }
            }
        } catch (Exception ex){
            errors += "Problem uploading files" + ex.getMessage();
            noErrors = false;
        }

        if (noErrors){
            success = "Your application has been submitted. Thank you and please wait to be contacted.";
        }

        request.setAttribute("errorMessage", errors);
        request.setAttribute("successMessage", success);

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
