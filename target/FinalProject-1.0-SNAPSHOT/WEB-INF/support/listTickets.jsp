<%@ page import="com.nagy.support.Ticket" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Map<Integer, Ticket> ticketDatabase = (Map<Integer, Ticket>)request.getAttribute("ticketData");
    int numTickets = ticketDatabase.size();
%>

<jsp:include page="/WEB-INF/include/header.jsp" />
<h1 class="mt-5">Support Tickets</h1>
<p class="lead">There <%= numTickets == 1 ? "is" : "are" %> <%= numTickets %> <%= numTickets == 1 ? "ticket" : "tickets" %> in the system</p>
<%
    if(ticketDatabase.size() > 0) {
%>
<ul>
    <%
        for(int id : ticketDatabase.keySet()) {
            Ticket ticket = ticketDatabase.get(id);
    %>
    <li><a href="tickets?action=view&ticketId=<%= id %>">Ticket #<%= id %></a> -
        From: <%= ticket.getCustomerName() %>
    </li>
    <%
        }
    %>
</ul>
<%
    }
%>
<jsp:include page="/WEB-INF/include/footer.jsp" />