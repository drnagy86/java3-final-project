package com.nagy.ch04;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "VoiceIn", value = "/ch04/voice-in")
public class VoiceIn extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/xml");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<Response>");
            out.println("<Say>Please stay on the line for the next available representative.</Say>");
            out.println("<Play>https://demo.twilio.com/docs/classic.mp3</Play>");
            out.println("</Response>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
