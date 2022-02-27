package com.nagy.ch04;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SmsIIn", value = "/ch04/sms-in")
public class SmsIIn extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/xml");

        try(PrintWriter out = response.getWriter()){
            out.println("<Response>");
            out.println("<Message>A message in xml </Message>");
            out.println("</Response>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
