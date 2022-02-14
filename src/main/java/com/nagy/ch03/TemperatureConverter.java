package com.nagy.ch03;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;

@WebServlet(name = "TemperatureConverter", value = "/temp-converter")
public class TemperatureConverter extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = writeHeader(request, response);
        showForm(writer, "", "");
        getBlankCelsius(writer);
        writeFooter(writer, true);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = writeHeader(request, response);
        String stringTypeOfTemp = request.getParameter("radConversion");
        String stringTemperatureToConvert = request.getParameter("temp");

        boolean resultIsCelsius = (stringTypeOfTemp.equals("f")) ? true : false;
        String label = resultIsCelsius ? "&#8457;" : "&#8451;";
        double temp = 0.0;

        // validation for absolute zero and for not numbers
        try{
            temp = Double.parseDouble(stringTemperatureToConvert);
            double absolute0F = -459.6701;
            double absoluteOC = -273.0001;
            if((temp <= absolute0F && resultIsCelsius) ||(temp <= absoluteOC && !resultIsCelsius)){
                stringTemperatureToConvert = "0";
            }
        } catch (NumberFormatException ex){
            stringTemperatureToConvert = "0";
        }

        showForm(writer, label, stringTemperatureToConvert);

        if (stringTemperatureToConvert == null || stringTemperatureToConvert == "") stringTemperatureToConvert = "0";

        insertResult(writer, resultOfConversion(resultIsCelsius, stringTemperatureToConvert));

        writeFooter(writer, resultIsCelsius);
    }

    private PrintWriter writeHeader(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");
//        This page was started with template code from
//        https://getbootstrap.com/docs/5.1/getting-started/introduction/#starter-template
        writer.append("<!doctype html>\r\n")
                .append("<html lang=\"en\">\r\n")
                .append("<head>\r\n")
                .append("<meta charset=\"utf-8\">\r\n")
                .append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n")
                .append("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">\r\n")
                .append("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css\">\r\n")
                .append("<link href=\"styles/ch03Style.css\" rel=\"stylesheet\">\r\n")
                .append("<title>Temperature Converter</title>\r\n")
                .append("</head>\r\n")
                .append("<body>\r\n")
                .append("<div class=\"container-fluid p-5\">\r\n")
                .append("<div class=\"container-sm p-5 bg-dark text-white text-center border\">\r\n")
                .append("<h1>Convert Temperature</h1>\r\n")
                .append("<p>Convert from Fahrenheit to Celsius or from Celsius to Fahrenheit</p>\r\n")
                .append("</div>\r\n")
                .append("<div class=\"container-sm p-5 border text-center \">\r\n")

        ;

        return writer;
    }

    private void getBlankCelsius(PrintWriter writer){
        // Result in Celsius
        writer.append("<div class=\"col-auto text-center pt-3\">\r\n")
                .append("<p id=\"pResult\" >&#8451;</p>\r\n")
                .append("</div>\r\n")
        ;
    }

    private void showForm (PrintWriter writer, String label, String previousValue) throws ServletException, IOException {

        if(label == "" || label == null) label = "&#8457;";

        writer.append("    <form action=\"\" method=\"post\">\r\n")
                .append("<div class=\"row g-3 align-items-center\" >\r\n")
                .append("<div class=\"d-flex justify-content-center\">\r\n")
                .append("<div class=\"col-auto form-floating mb-3\">\r\n")
                .append("<input type=\"text\" class=\"form-control\" name=\"temp\" id=\"temp\" value=\"" +
                        previousValue +
                        "\" >\r\n")
                .append("<label for=\"temp\" id=\"lblTempType\">" +
                        label +
                        "</label>\r\n")
                .append("</div>\r\n")
                .append("<div class=\"col-sm-2\">\r\n")
                .append("<button type=\"submit\" class=\"btn btn-light\" id=\"submitButton\">\r\n")
                .append("<i class=\"bi bi-chevron-double-right\"></i>\r\n")
                .append("</button>\r\n")
                .append("</div>\r\n")
        ;
    }

    private void insertResult(PrintWriter writer, String string) {
        // Result in Celsius
        writer.append("<div class=\"col-auto text-center pt-3\">\r\n")
                .append("<p id=\"pResult\">" +
                        string +
                        "</p>\r\n")
                .append("</div>\r\n")
        ;
    }

    private void writeFooter(PrintWriter writer, boolean resultIsCelsius) throws ServletException, IOException {

        String fahChecked = "";
        String celChecked = "";
        if (resultIsCelsius){
            fahChecked += "checked";
        }
        else {
            celChecked += "checked";
        }

        writer.append("</div>\r\n")
                .append("<div class=\"d-flex justify-content-center\">\r\n")
                .append("<div class=\"form-check mx-3\">\r\n")
                .append("<input type=\"radio\" class=\"form-check-input\" id=\"radF\" name=\"radConversion\" value=\"f\"" +
                        fahChecked +
                        ">\r\n")
                .append("<label class=\"form-check-label\" for=\"radF\">&#8457</label>\r\n")
                .append("<div class=\"invalid-feedback\">\r\n")
                .append("<p id=\"fahValidation\">Please enter a number.</p>\r\n")
                .append("</div>\r\n")
                .append("</div>\r\n")
                .append("<p>|</p>\r\n")
                .append("<div class=\"form-check mx-3\">\r\n")
                .append("<input type=\"radio\" class=\"form-check-input\" id=\"radC\" name=\"radConversion\" value=\"c\"" +
                        celChecked +
                        ">\r\n")
                .append("<label class=\"form-check-label\" for=\"radC\">&#8451;</label>\r\n")
                .append("<div class=\"invalid-feedback\">\r\n")
                .append("<p id=\"celValidation\">Please enter a number.</p>\r\n")
                .append("</div>\r\n")
                .append("</div>\r\n")
                .append("</div>\r\n")
                .append("</div>\r\n")
                .append("\r\n")
                .append("</form>\r\n")
                .append("</div>\r\n")
                .append("</div>\r\n")
                .append("\r\n")
                .append("<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p\" crossorigin=\"anonymous\"></script>\r\n")
                .append("\r\n")
                .append("<script src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\" integrity=\"sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj\" crossorigin=\"anonymous\"></script>")
                .append("<script src=\"js/index.js\"></script>")
                .append("</body>\r\n")
                .append("</html>\r\n")
        ;
    }

    private String resultOfConversion(boolean resultIsCelsius, String stringTemperatureToConvert) {
        if (resultIsCelsius){
            return fahrenheitToCelsius(stringTemperatureToConvert);
        } else {
            return celsiusToFahrenheit(stringTemperatureToConvert);
        }
    }

    private String celsiusToFahrenheit(String stringTemperatureToConvert) {
        BigDecimal tempInCelsius = new BigDecimal(stringTemperatureToConvert);
        // (0°C × 9/5) + 32 = 32°F
        BigDecimal five = new BigDecimal(5.0);
        BigDecimal nine = new BigDecimal(9.0);
        BigDecimal conversion = nine.divide(five, 666666, RoundingMode.HALF_EVEN);
        BigDecimal tempInFahrenheit = tempInCelsius.multiply(conversion).setScale(2, RoundingMode.HALF_EVEN);
        tempInFahrenheit = (tempInFahrenheit.add(new BigDecimal(32)));

        String result = formatTemp(tempInFahrenheit);

        result += " &#8457;";
        return result;
    }

    private String fahrenheitToCelsius(String stringTemperatureToConvert) {
        BigDecimal tempInFahrenheit = new BigDecimal(stringTemperatureToConvert);
        // (32°F − 32) × 5/9 = 0°C
        tempInFahrenheit = (tempInFahrenheit.subtract(new BigDecimal(32)));
        BigDecimal five = new BigDecimal(5.0);
        BigDecimal nine = new BigDecimal(9.0);
        BigDecimal conversion = five.divide(nine, 666, RoundingMode.HALF_EVEN);
        BigDecimal tempInCelsius = tempInFahrenheit.multiply(conversion).setScale(2, RoundingMode.HALF_EVEN);

        String result = formatTemp(tempInCelsius);
        result += " &#8451;";
        return result;
    }

    private String formatTemp(BigDecimal tempInFahrenheit) {
        String result = tempInFahrenheit.toString();
        if (result.charAt(result.length() - 1) == '0' && result.charAt(result.length() - 2) == '0'){
            result = result.substring(0, result.length()-3);
        }
        return result;
    }
}
