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
<jsp:include page="/WEB-INF/include/footer.jsp" />