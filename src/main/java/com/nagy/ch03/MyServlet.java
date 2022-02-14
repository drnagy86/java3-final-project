package com.nagy.ch03;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//@WebServlet(name = "MyServlet", value = "/MyServlet")
@WebServlet(name = "MyServlet", urlPatterns = {"/MyServlet" , "/Test/MyServlet"})
public class MyServlet extends HttpServlet {

    private final String DEFAULT_USER = "Guest";
    //private String default_user;

    private String getNow(){

        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("LLLL d, yyyy HH:MM:SS");


        String time = dateTime.format(formatter);


        return time;
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String user = request.getParameter("user");
        if (user == null){
            user = DEFAULT_USER;
        }

        response.setContentType("text/html; charset=UTF-8");
        //response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {



            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en-US\">");
            out.println("<head>");
            out.println("<title>MyServlet</title>");
            out.println("<link href=\"" + request.getContextPath() + "/styles/main.css\" rel=\"stylesheet\"");
            out.println("</head>");
            out.println("<body>");

            out.println("<h1>Hello, " +
                    user +
                    "</h1>");

            out.println("<form action=\"\" method=\"POST\"/>");
            out.println("<label for=\"user\">Enter your name</label>");
            out.println("<input type=\"text\" name=\"user\" id=\"user\"/>");
            out.println("<input type=\"submit\" value=\"Go\"/>");


            out.println("</form>");

            out.println("<h1>MyServlet at " + request.getContextPath() + "</h1>");
            out.println("<p>Today is: " + getNow() + "</p>");
            out.println("<img source=\""+ request.getContextPath() +"/images/download.jpg\" alt=\"java icon\">");
            out.println("</body>");
            out.println("</html>");
        }






    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
