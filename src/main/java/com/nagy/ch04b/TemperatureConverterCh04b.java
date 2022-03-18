package com.nagy.ch04b;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

@WebServlet(name = "TemperatureConverterCh04b", value = "/TemperatureConverterCh04b")
public class TemperatureConverterCh04b extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/ch04b/tempConverter.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("error", false);
        String degrees = request.getParameter("degrees");
        String convertTo = request.getParameter("radConversion");
        Temperature temperature = new Temperature();

        // regular expression found here
        //https://stackoverflow.com/questions/15814592/how-do-i-include-negative-decimal-numbers-in-this-regular-expression
        String regexOnlyDigits = "^-?[0-9]\\d*(\\.\\d+)?$";

        if (degrees == null || degrees.equals("") || !degrees.matches(regexOnlyDigits)){
            request.setAttribute("error", true);
            request.setAttribute("errorMsg",
                    "Please enter a number as a temperature to convert.");
        } else if (convertTo == null || convertTo.equals("")){
            request.setAttribute("error", true);
            request.setAttribute("errorMsg",
                    "Please select either Fahrenheit or Celsius");
        } else {
            try {
                if (convertTo.equals("f")){
                    temperature.setDegreesFahrenheit(new BigDecimal(degrees));
                    request.setAttribute("temperature", temperature);
                    request.setAttribute("degrees", null);
                }
                else {
                    temperature.setDegreesCelsius(new BigDecimal(degrees));
                    request.setAttribute("temperature", temperature);
                    request.setAttribute("degrees", null);
                }
            }
            catch (IllegalArgumentException ex){
                request.setAttribute("error", true);
                request.setAttribute("errorMsg", "There was a problem with the value you were trying to convert: </br>" + ex.getMessage());
            }
        }

        request.getRequestDispatcher("/WEB-INF/ch04b/tempConverter.jsp").forward(request, response);

    }
}
