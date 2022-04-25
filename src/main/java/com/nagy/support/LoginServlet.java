package com.nagy.support;

import com.nagy.ch06.User;
import com.nagy.ch06.Users;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@WebServlet(name = "LoginServlet", value = "/support/login")
public class LoginServlet extends HttpServlet {

//    private static final Map<String, String> users = new Hashtable<>();
    private List<User> USER_DB = null;

    @Override
    public void init() throws ServletException {
        //Users users = new Users();
        Users.populateDB();
        USER_DB = Users.THE_USER_DB;

//        users.put("Marc", "password");
//        users.put("Amy", "password");
//        users.put("derrick.nagy@kirkwood.edu", "password");
//        users.put("random@kirkwood.edu", "P@ssw0rd");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
//
        // trying to logout
        if (request.getParameter("logout") != null) {
            // destroy session
            //session.invalidate();
            session.removeAttribute("username");
            request.setAttribute("loggedOut", true);
        }

        if (session.getAttribute("username") != null){
            response.sendRedirect("");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/support/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        request.setAttribute("loginFailed", false);

        User currentUser = null;
        boolean userAndPasswordMatch = false;
        

        for(User user : USER_DB){
            String currentUserName = user.getUsername();

            if (currentUserName.equals(username)){
                currentUser = user;
                userAndPasswordMatch = currentUser.getPassword().equals(password);
                break;
            }
        }
        if(
                username == null || username.length() == 0 ||
                        password == null || password.length() == 0
        ){
            request.setAttribute("loginFailed", true);
            request.setAttribute("errorMsg", "Login attempt failed.");
            request.getRequestDispatcher("/WEB-INF/support/login.jsp").forward(request, response);
        }
        else if (!userAndPasswordMatch){
            request.setAttribute("loginFailed", true);
            request.setAttribute("errorMsg", "Login attempt failed.");
            request.getRequestDispatcher("/WEB-INF/support/login.jsp").forward(request, response);
        }
        else {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            request.changeSessionId();

            if (session.getAttribute("pageBeforeLogIn") != null){
                response.sendRedirect(request.getContextPath() + "/" + session.getAttribute("pageBeforeLogIn").toString());
            }else {
                response.sendRedirect(request.getContextPath() + "/support/register?go=profile");
            }
        }
    }
}
