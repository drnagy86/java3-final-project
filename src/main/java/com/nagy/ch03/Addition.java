package com.nagy.ch03;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

@WebServlet(name = "Addition", value = "/add")
public class Addition extends HttpServlet {

    private PrintWriter writeHeader(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
        PrintWriter writer = res.getWriter();
        res.setContentType("text/html");
        res.setCharacterEncoding("UtF-8");
        writer.append("<!DOCTYPE html>\r\n")
                .append("<html>\r\n")
                .append("<head>\r\n")
                .append("<title>Addition Servlet</title>\r\n")
                .append("<link href=\"" + req.getContextPath() + "/styles/main.css\" rel=\"stylesheet\"\r\n")
                .append("</head>\r\n")
                .append("<body>\r\n")
                .append("<h1>Add two numbers</h1>\r\n");
        return writer;
    }

    private void writerFooter(PrintWriter writer){
        writer.append("</body>\r\n")
                .append("</html>\r\n");
        writer.close();
    }

    private void showForm(PrintWriter writer){
        // ask for two numbers
        writer.append("<h2>Enter two numbers and press calculate to add them together.</h2>")
                .append("<form method=\"POST\" action\"add\">\r\n")
                .append("<input type=\"text\" name=\"num1\" placeholder\"Enter a number\">\r\n")
                .append("<input type=\"text\" name=\"num2\" placeholder\"Enter a number\">\r\n")
                .append("<input type=\"submit\" value=\"Calculate\">\r\n")
                .append("</form>");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = writeHeader(request, response);
        showForm(writer);
        writerFooter(writer);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String num1 = request.getParameter("num1");
        String num2 = request.getParameter("num2");

        PrintWriter writer = writeHeader(request, response);
        showForm(writer);


        writer.append(add(num1, num2));

        writerFooter(writer);
    }

    private String add(String num1, String num2) {
        if (isANumber(num1) && isANumber(num2)){
            BigDecimal n1 = new BigDecimal(num1);
            BigDecimal n2 = new BigDecimal(num2);

            BigDecimal total = n1.add(n2);

            return "<p>Result:" + num1 + " + " + num2 + " = " + total + "</p>";
        }
        else {
            return "<p> Invalid input </p>";
        }
    }

    private boolean isANumber(String num1) {

        try{
            Double.parseDouble(num1);
            return true;
        } catch (NumberFormatException ex){
            return false;
        }



    }
}
