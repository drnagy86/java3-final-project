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


        try {
            if (convertTo.equals("f")){
                temperature.setDegreesFahrenheit(new BigDecimal(degrees));
            }
            else {
                temperature.setDegreesCelsius(new BigDecimal(degrees));
            }
        }
        catch (IllegalArgumentException ex){
            request.setAttribute("error", true);
            request.setAttribute("errorMsg", "There was a problem with the value you were trying to convert: </br>" + ex.getMessage());
        }




        request.setAttribute("temperature", temperature);
        request.setAttribute("degrees", null);

        request.getRequestDispatcher("/WEB-INF/ch04b/tempConverter.jsp").forward(request, response);

//        String degreesCelsius = request.getParameter("degreesCelsius");
//        String degreesFahrenheit = request.getParameter("degreesFahrenheit");
//
//        Temperature temperature = new Temperature();
//
//        try {
//            temperature = new Temperature();
//
//            if (degreesCelsius == "" && degreesFahrenheit == "") {
//
//            }
//            else if (degreesCelsius == ""){
//                temperature.setDegreesFahrenheit(new BigDecimal(degreesFahrenheit));
//            }
//            else if (degreesFahrenheit == ""){
//                temperature.setDegreesCelsius(new BigDecimal(degreesCelsius));
//            }
//
//            request.setAttribute("temperature", temperature);
//
//        } catch (IllegalArgumentException ex){
//
//        }
//
//        request.setAttribute("temperature", temperature);
//        request.setAttribute("degreesCelsius", null);
//        request.setAttribute("degreesFahrenheit", null);
//
//        request.getRequestDispatcher("/WEB-INF/ch04b/tempConverter.jsp").forward(request, response);
    }
}
