package com.nagy.ch04;

import io.github.cdimascio.dotenv.Dotenv;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;
import com.twilio.Twilio;

//public final String TWILIO_ACCOUNT_SID = dotenv.get("TWILIO_ACCOUNT_SID");
//public final String TWILIO_AUTH_TOKEN = dotenv.get("TWILIO_AUTH_TOKEN");
//public final String TWILIO_PHONE_NUMBER = dotenv.get("TWILIO_PHONE_NUMBER");

@WebServlet(name = "SmsOut", value = "/ch04/sms-out")
public class SmsOut extends HttpServlet {
    Dotenv dotenv = Dotenv.load();
    public final String TWILIO_PHONE = dotenv.get("TWILIO_PHONE");
    public final String TWILIO_SID = dotenv.get("TWILIO_SID");
    public final String TWILIO_TOKEN = dotenv.get("TWILIO_TOKEN");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("ch04/twilio.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phone = request.getParameter("phone");
        String msg = request.getParameter("message");

        if(phone != null && !phone.equals("")) {
            phone = phone.replaceAll("[^\\d.]", "");
        } else {
            request.setAttribute("errorMsg", "Phone number required");
            request.setAttribute("smsError", true);
        }
        if(phone.length() != 10) {
            request.setAttribute("errorMsg", "Invalid phone number");
            request.setAttribute("smsError", true);
        } else if(msg.length() == 0 || msg == null) {
            request.setAttribute("errorMsg", "Message required");
            request.setAttribute("smsError", true);
        } else {
            // Everything is Good!
            Twilio.init(TWILIO_SID, TWILIO_TOKEN);
            try {
                Message message = Message.creator(
                        new PhoneNumber("+1" + phone),
                        new PhoneNumber(TWILIO_PHONE),
                        msg
                ).create();
                request.setAttribute("smsError", false);
            } catch (final ApiException e) {
                System.out.println(e.getMessage());
                request.setAttribute("errorMsg", "Message failed to send. Try again later.");
                request.setAttribute("smsError", true);
            }
        }
        request.getRequestDispatcher("/ch04/twilio.jsp").forward(request, response);
    }
}
