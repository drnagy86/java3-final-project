package com.nagy.ch06;

import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import io.github.cdimascio.dotenv.Dotenv;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "RegisterServlet", value = "/support/register")
public class RegisterServlet extends HttpServlet {

    private List<User> USER_DB = null;
    //Users _users = null;
    Dotenv dotenv = Dotenv.load();
    public final String TWILIO_PHONE = dotenv.get("TWILIO_PHONE");
    public final String TWILIO_SID = dotenv.get("TWILIO_SID");
    public final String TWILIO_TOKEN = dotenv.get("TWILIO_TOKEN");

    @Override
    public void init() throws ServletException {

        Users.populateDB();
        USER_DB = Users.THE_USER_DB;
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String go = request.getParameter("go");
        if (go == null){
            go = "browse";
        }
        // add log in

        switch (go) {
            case "register":
                registerNewUser(request,response);
                break;
            case "edit":
                // support/register?go=edit
                editProfile(request, response);
                break;
            case "testPhone":
                // support/register?go=testPhone&phone=1234567890
                testPhone(request, response);
                break;
            case "messageToSend":
                // support/register?go=message&messageToSend=whatever
                sendMessages(request, response);
                break;
            case "profile":
                // support/register?go=message&messageToSend=whatever
                profileDetails(request, response);
                break;
            default:
                // log in
                editProfile(request, response);
                break;
        }
    }

    private void profileDetails(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        if (session.getAttribute("username")== null){
            response.sendRedirect("login");
            session.setAttribute("pageBeforeLogIn", "/support/register?go=profile");
            return;
        }

        // find user
        String userToFind = (String) session.getAttribute("username");
        User currentUser = null;
        String errors = "";


        for(User user : USER_DB){
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
        if (!permissions.isEmpty())isAdmin = permissions.get("admin");


        request.setAttribute("isAdmin", isAdmin);
        request.setAttribute("user", currentUser);
        request.setAttribute("errorMessage", errors);
        request.getRequestDispatcher("/WEB-INF/ch06/profileDetails.jsp").forward(request,response);


    }

    private void sendMessages(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        if (session.getAttribute("username")== null){
            response.sendRedirect("login");
            session.setAttribute("pageBeforeLogIn", "/support/register?go=edit");
            return;
        }

        String phone = "";
        String messageToSend = request.getParameter("messageToSend");

        request.getRequestDispatcher("/WEB-INF/ch06/register.jsp").forward(request,response);

    }

    private void testPhone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (session.getAttribute("username")== null){
            response.sendRedirect("login");
            session.setAttribute("pageBeforeLogIn", "/support/register?go=edit");
            return;
        }

        // find user
        String userToFind = (String) session.getAttribute("username");
        User currentUser = null;
        String errors = "";

        for(User user : USER_DB){
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

        if (session.getAttribute("username")== null){
            response.sendRedirect("login");
            session.setAttribute("pageBeforeLogIn", "/support/register?go=edit");
            return;
        }

        String phone = request.getParameter("phone");
        String messageToSend = "Your phone number: " + phone + " has been verified. Thank you";


        if(phone != null && !phone.equals("")) {
            phone = phone.replaceAll("[^\\d.]", "");
        } else {
            request.setAttribute("errorMsg", "Phone number required");
            request.setAttribute("smsError", true);
            request.setAttribute("smsSuccess", false);
        }
        if(phone.length() != 10) {
            request.setAttribute("errorMsg", "Invalid phone number");
            request.setAttribute("smsError", true);
            request.setAttribute("smsSuccess", false);
        } else if(messageToSend.length() == 0 || messageToSend == null) {
            request.setAttribute("errorMsg", "Message required");
            request.setAttribute("smsError", true);
            request.setAttribute("smsSuccess", false);
        } else {
            // Everything is Good!
            Twilio.init(TWILIO_SID, TWILIO_TOKEN);
            try {
                Message message = Message.creator(
                        new PhoneNumber("+1" + phone),
                        new PhoneNumber(TWILIO_PHONE),
                        messageToSend
                ).create();
                request.setAttribute("smsError", false);
                request.setAttribute("smsSuccess", true);
            } catch (final ApiException e) {
                System.out.println(e.getMessage());
                request.setAttribute("errorMsg", "Message failed to send. Try again later.");
                request.setAttribute("smsError", true);
                request.setAttribute("smsSuccess", false);
            }
        }


        request.setAttribute("user", currentUser);
        request.setAttribute("errorMessage", errors);
        request.getRequestDispatcher("/WEB-INF/ch06/register.jsp").forward(request,response);

        request.getRequestDispatcher("/WEB-INF/ch06/register.jsp").forward(request,response);

    }

    private void registerNewUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long id = Users.addUser();

        User currentUser = null;
        for(User user : Users.THE_USER_DB){
            if (user.getUserId() == id){
                currentUser = user;
                break;
            }
        }

        request.setAttribute("user", currentUser);
        request.setAttribute("isRegistering", true);


        request.getRequestDispatcher("/WEB-INF/ch06/register.jsp").forward(request,response);

    }

    private void editProfile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        if (session.getAttribute("username")== null){
            response.sendRedirect("login");
            session.setAttribute("pageBeforeLogIn", "/support/register?go=edit");
            return;
        }

        // find user
        String userToFind = (String) session.getAttribute("username");
        User currentUser = null;
        String errors = "";


        for(User user : USER_DB){
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
        if (!permissions.isEmpty())isAdmin = permissions.get("admin");


        request.setAttribute("isAdmin", isAdmin);
        request.setAttribute("user", currentUser);
        request.setAttribute("errorMessage", errors);
        request.getRequestDispatcher("/WEB-INF/ch06/register.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        // find user
        String userToFind = (String) session.getAttribute("username");
        User currentUser = null;
        String errors = "";
        boolean usersMatch = false;

        long userID = Long.parseLong(request.getParameter("userID"));

        for(User user : USER_DB){
            //String currentUserName = user.getUsername();

            //if (currentUserName.equals(userToFind)){
            if (user.getUserId() == userID){
                currentUser = user;
                usersMatch = true;
                break;
            }
        }

        if (!usersMatch){
            //set error message
            errors += "User's don't match.<br>";
        }else {

            // make sure that the passwords match
            String password = (String) request.getParameter("password");
            String passwordRetype = (String) request.getParameter("passwordRetype");


            if ( password.equals(passwordRetype)){


                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String userName = request.getParameter("userName");
                String phoneNumber = request.getParameter("phoneNumber");

                System.out.println(phoneNumber);

                try {
                    currentUser.setUserId(userID);
                    currentUser.setFirstName(firstName);
                    currentUser.setLastName(lastName);
                    currentUser.setUsername(userName);
                    currentUser.setPhoneNumber(phoneNumber);
                    currentUser.setPassword(password);

                    session.setAttribute("username", currentUser.getUsername());
                    request.changeSessionId();

                } catch (Exception e){
                    errors += e.getMessage();
                }

            }
            else {
                errors += "Passwords don't match. Please try again.<br>";
            }
        }

        if (currentUser == null){
            errors += "No user information in form.<br>";
        }


        request.setAttribute("user", currentUser);
        request.setAttribute("errorMessage", errors);
        request.getRequestDispatcher("/WEB-INF/ch06/register.jsp").forward(request,response);

    }


}

