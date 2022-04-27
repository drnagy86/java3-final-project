package com.nagy.ch07;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

@WebServlet(name = "DirectoryServlet", value = "/ch07/directory")
public class DirectoryServlet extends HttpServlet {

    private static final String FILE_PATH = "WEB-INF/people.csv";
    private static SortedSet<Person> people;

    private void readFromFile(HttpServletRequest request, HttpServletResponse response) throws ParserConfigurationException, MalformedURLException, IOException {

        if  (people == null){
            try(Scanner in = new Scanner(new File(getServletContext().getRealPath(FILE_PATH)))){

                people = new TreeSet<>();
                int lineCount = 0;
                String line = in.nextLine();
                String[] fields;
                String firstName;
                String lastName;
                String picture;
                while(in.hasNextLine()){
                    lineCount++;
                    line = in.nextLine();
                    fields = line.split(",");
                    firstName = fields[1];
                    lastName = fields[2];
                    picture = fields[3];
                    people.add(new Person(firstName, lastName, picture));
                }


            } catch(FileNotFoundException e){
                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    out.println("<!DOCTYPE html><html><head>");
                    out.println("<title>Data error</title>");
                    out.println("</head><body>");
                    out.println("<h1>Error reading data</h1>");
                    out.println("</body></html>");
                }
                return;

            }



        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            readFromFile(request, response);
        } catch (ParserConfigurationException | IOException ex) {
            return;
        }

        int page = 1;
        int itemsPerPage = 8;

        int maxPages = people.size() / itemsPerPage;
        if(people.size() % itemsPerPage != 0) {
            maxPages++;
        }

        String pageStr = request.getParameter("page");
        if(pageStr != null && !pageStr.equals("")) {
            try {
                page = Integer.parseInt(pageStr);
                if(page < 1){
                    page = 1;
                } else if(page > maxPages) {
                    page = maxPages;
                }
            } catch(NumberFormatException e) {
                page = 1;
            }
        }

        int beginItem = (page - 1) * itemsPerPage;
        int endItem = beginItem + itemsPerPage - 1;

        request.setAttribute("begin", beginItem);
        request.setAttribute("end", endItem);
        request.setAttribute("maxPages", maxPages);
        request.setAttribute("currentPage", page);

        request.setAttribute("people", people);
        request.getRequestDispatcher("/WEB-INF/ch07/directory.jsp").forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
