package com.nagy.support;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet(name = "TicketServlet", value = "/support/tickets", loadOnStartup = 1)
public class TicketServlet extends HttpServlet {
    private Map<Integer, Ticket> ticketDatabase;
    private volatile int TICKET_ID = 0;

    @Override
    public void init() throws ServletException {
        ticketDatabase = new LinkedHashMap<>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null) action = "list";
        switch(action) {
            case "create":
                showTicketForm(request, response);
                break;
            case "view":

                break;
            case "download":

                break;
            default:
                listTickets(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Ticket ticket = new Ticket();
        ticket.setCustomer(request.getParameter("name"));
        ticket.setSubject(request.getParameter("subject"));
        ticket.setBody(request.getParameter("message"));

        int id;
        synchronized (this){
            id = ++this.TICKET_ID;
            this.ticketDatabase.put(id, ticket);
        }

        response.sendRedirect("tickets?action=view&ticketId=" + id);
    }

    private void showTicketForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/support/ticketForm.jsp").forward(request, response);
    }

    private void listTickets(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("ticketData", this.ticketDatabase);
        request.getRequestDispatcher("/WEB-INF/support/listTickets.jsp").forward(request, response);
    }
}
