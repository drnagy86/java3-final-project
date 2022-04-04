package com.nagy.support;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

@WebServlet(name = "LoginServlet", value = "/support/login")
public class LoginServlet extends HttpServlet {

    private static final Map<String, String> users = new Hashtable<>();

    @Override
    public void init() throws ServletException {
        users.put("Marc", "password");
        users.put("Amy", "password");
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
        if(
                username == null || username.length() == 0 ||
                        password == null || password.length() == 0 ||
                        !users.containsKey(username) ||
                        !password.equals(users.get(username))
        ){
            request.setAttribute("loginFailed", true);
            request.setAttribute("errorMsg", "Login attempt failed.");
            request.getRequestDispatcher("/WEB-INF/support/login.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            request.changeSessionId();
            response.sendRedirect(request.getContextPath());
        }
    }
}
