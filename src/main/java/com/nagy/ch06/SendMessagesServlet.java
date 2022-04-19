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
import java.util.List;
import java.util.Map;

@WebServlet(name = "SendMessagesServlet", value = "/support/sendMessages")
public class SendMessagesServlet extends HttpServlet {

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

        HttpSession session = request.getSession();
        if (session.getAttribute("username")== null){
            response.sendRedirect("login");
            session.setAttribute("pageBeforeLogIn", "/support/sendMessages");
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
        request.getRequestDispatcher("/WEB-INF/ch06/sendMessages.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        // find user
        String userToFind = (String) session.getAttribute("username");
        User currentUser = null;
        String errors = "";
        boolean usersMatch = false;

//        long userID = Long.parseLong(request.getParameter("userID"));

        for(User user : USER_DB){
            String currentUserName = user.getUsername();

            if (currentUserName.equals(userToFind)){
//            if (user.getUserId() == userID){
                currentUser = user;
                usersMatch = true;
                break;
            }
        }

        if (!usersMatch){
            //set error message
            errors += "User's don't match.<br>";
        }else {
            String messageToSend = request.getParameter("messageToSend");

            if (messageToSend.length() < 0 && messageToSend.length()>50){
                errors += "Message must be greater than 0 and less than 50 characters in length.<br>";
            }
            else {
                // send messages
                errors += sendMessages(messageToSend);
            }
        }


        // find out if user is admin
        boolean isAdmin = false;
        Map<String, Boolean> permissions = currentUser.getPermissions();
        if (!permissions.isEmpty())isAdmin = permissions.get("admin");


        request.setAttribute("isAdmin", isAdmin);

        request.setAttribute("user", currentUser);
        request.setAttribute("errorMessage", errors);
        request.getRequestDispatcher("/WEB-INF/ch06/sendMessages.jsp").forward(request,response);


    }

    private String sendMessages(String messageToSend) {
        String results = "";
        for (User user : USER_DB){

            String phone = user.getPhoneNumber();
            if(phone != null && !phone.equals("")) {
                phone = phone.replaceAll("[^\\d.]", "");
            } else {
                 results += "Phone number required <br>";
            }
            if(phone.length() != 10) {
                results += "Invalid phone number " + phone + "<br>";
            } else {
                // Everything is Good!
                Twilio.init(TWILIO_SID, TWILIO_TOKEN);
                try {
                    Message message = Message.creator(
                            new PhoneNumber("+1" + phone),
                            new PhoneNumber(TWILIO_PHONE),
                            messageToSend
                    ).create();
                    results += phone + " was successfully sent the message. <br>";
                } catch (final ApiException e) {
                    System.out.println(e.getMessage());
                    results += "Message to " +phone + " failed to send. Try again later.<br>";
                }
            }

        }

        return results;
    }
}
