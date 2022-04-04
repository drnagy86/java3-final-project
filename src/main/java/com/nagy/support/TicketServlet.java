package com.nagy.support;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet(name = "TicketServlet", value = "/support/tickets", loadOnStartup = 1)
@MultipartConfig (
        fileSizeThreshold = 1_000_000, // 1 MB
        maxFileSize = 5_000_000 //5 MB
)
public class TicketServlet extends HttpServlet {
    private Map<Integer, Ticket> ticketDatabase;
    private volatile int TICKET_ID = 1;

    @Override
    public void init() throws ServletException {
        ticketDatabase = new LinkedHashMap<>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        int numPages = 0;
        if (session.getAttribute("numPagesVisited") != null){
            try {
                numPages = Integer.parseInt(session.getAttribute("numPagesVisited").toString());
                numPages++;
            } catch (NumberFormatException ex){
                System.out.println(ex);
            }
        }
        session.setAttribute("numPagesVisited", numPages);

        if(action == null) action = "list";
        switch(action) {
            case "create":
                showTicketForm(request, response);
                break;
            case "view":
                viewTicket(request, response);
                break;
            case "download":
                downloadAttachment(request,response);
                break;
            default:
                listTickets(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Ticket ticket = new Ticket();
        ticket.setCustomerName(request.getParameter("name"));
        ticket.setSubject(request.getParameter("subject"));
        ticket.setBody(request.getParameter("message"));



        for(Part part: request.getParts()) {
            if(part.getName().equals("files") && part != null && part.getSize() > 0) {
                Attachment attachment = processAttachment(part);
                if(attachment != null) {
                    ticket.addAttachment(attachment);
                }
            }
        }

        int id;
        synchronized (this){
            id = this.TICKET_ID++;
        }
        this.ticketDatabase.put(id, ticket);
        request.setAttribute("ticketSubmitted", true);
        request.getRequestDispatcher("/WEB-INF/support/ticketForm.jsp").forward(request, response);

//        response.sendRedirect("tickets?action=view&ticketId=" + id);
    }

    private void downloadAttachment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // login
        HttpSession session = request.getSession();
        if (session.getAttribute("username")== null){
            response.sendRedirect("login");
            return;
        }

        String ticketId = request.getParameter("ticketId");
        String fileName = request.getParameter("attachment");
        Ticket ticket = getTicket(ticketId);
        if(ticket == null || fileName == null || fileName.length() == 0) {
            response.sendRedirect("tickets");
            return;
        }
        Attachment attachment = ticket.getAttachment(fileName);
        if(attachment == null) {
            response.sendRedirect("tickets");
            return;
        }
        response.setHeader("Content-Disposition", "attachment; filename=" + attachment.getName());
        response.setContentType("application/octet-stream");
        try(ServletOutputStream stream = response.getOutputStream()) {
            stream.write(attachment.getContents());
        }
    }

    private Attachment processAttachment(Part part) throws IOException {
        Attachment attachment = new Attachment();
        try(
                InputStream inputStream = part.getInputStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ) {
            int read;
            final byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            attachment.setName(part.getSubmittedFileName());
            attachment.setContents(outputStream.toByteArray());
        }
        return attachment;
    }

    private void showTicketForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/support/ticketForm.jsp").forward(request, response);
    }

    private void listTickets(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // login
        HttpSession session = request.getSession();
        if (session.getAttribute("username")== null){
            response.sendRedirect("login");
            return;
        }

        request.setAttribute("ticketData", this.ticketDatabase);
        request.getRequestDispatcher("/WEB-INF/support/listTickets.jsp").forward(request, response);
    }

    private void viewTicket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // login
        HttpSession session = request.getSession();
        if (session.getAttribute("username")== null){
            response.sendRedirect("login");
            return;
        }
        String idString = request.getParameter("ticketId");
        Ticket ticket = getTicket(idString);
        request.setAttribute("ticketId", idString);
        request.setAttribute("ticket", ticket);
        request.getRequestDispatcher("/WEB-INF/support/viewTicket.jsp").forward(request, response);
    }

    private Ticket getTicket(String idStr) {
        if(idStr == null || idStr.length() == 0) {
            return null;
        }
        try {
            int id = Integer.parseInt(idStr);
            return ticketDatabase.get(id);
        } catch(NumberFormatException e) {
            return null;
        }
    }
}
