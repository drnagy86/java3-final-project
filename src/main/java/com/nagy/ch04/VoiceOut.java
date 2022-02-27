package com.nagy.ch04;

import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;
import com.twilio.type.Twiml;
import io.github.cdimascio.dotenv.Dotenv;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "VoiceOut", value = "/ch04/voice-out")
public class VoiceOut extends HttpServlet {
    Dotenv dotenv = Dotenv.load();
    public final String ACCOUNT_SID = dotenv.get("TWILIO_SID");
    public final String AUTH_TOKEN = dotenv.get("TWILIO_TOKEN");
    public final String TWILIO_PHONE = dotenv.get("TWILIO_PHONE");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("./twilio.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phone = request.getParameter("phone");
        String msg = request.getParameter("message");
        request.setAttribute("callError", false);
        if(phone != null && !phone.equals("")) {
            String phoneNoChars = phone.replaceAll("[^\\d.]", "");
            if(phoneNoChars.length() != 10) {
                // Error - invalid phone number (too short or too long)
                request.setAttribute("callError", true);
                request.setAttribute("callErrorMsg", "Invalid phone number");
            } else {
                if(msg != null && msg.length() > 0) {
                    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                    String twiml = ""
                            + "<Response>"
                            + "<Pause length=\"2\" />"
                            + "<Say voice=\"Polly.Joanna\">" + msg + "</Say>"
                            + "<Play loop=\"5\">https://demo.twilio.com/docs/classic.mp3</Play>"
                            + "</Response>";
                    try {
                        Call call = Call.creator(
                                new PhoneNumber("+1" + phone),
                                new PhoneNumber(TWILIO_PHONE),
                                new Twiml(twiml)
                        ).create();
                    } catch(final ApiException e) {
                        request.setAttribute("callErrorMsg", "Cannot make phone call right now. Try again later.");
                        request.setAttribute("callError", true);
                        System.out.println(e.getMessage());
                    }
                } else {
                    // Error - message missing
                    request.setAttribute("callErrorMsg", "Message required");
                    request.setAttribute("callError", true);
                }
            }
        } else {
            // Error - phone number missing
            request.setAttribute("callErrorMsg", "Phone number required");
            request.setAttribute("message", msg);
            request.setAttribute("callError", true);
        }

        request.getRequestDispatcher("/ch04/twilio.jsp").forward(request, response);
    }
}
