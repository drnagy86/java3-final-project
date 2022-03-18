<%@ page import="com.nagy.support.Ticket" %>
<%@ page import="com.nagy.support.Attachment" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/include/header.jsp" />
<%
    String ticketId = (String)request.getAttribute("ticketId");
    Ticket ticket = (Ticket)request.getAttribute("ticket");
    if(ticket == null) {
%>
<h1 class="mt-5">The ticket you requested cannot be found.</h1>
<%
} else {
%>
<h1 class="mt-5">Ticket #<%= ticketId %></h1>
<dl class="row">
    <dt class="col-sm-4 col-md-3">Customer Name</dt>
    <dd class="col-sm-8 col-md-9"><%= ticket.getCustomerName() %></dd>
</dl>
<dl class="row">
    <dt class="col-sm-4 col-md-3">Subject</dt>
    <dd class="col-sm-8 col-md-9"><%= ticket.getSubject() %></dd>
</dl>
<dl class="row">
    <dt class="col-sm-4 col-md-3">Message Body</dt>
    <dd class="col-sm-8 col-md-9"><%= ticket.getBody() %></dd>
</dl>
<% if(ticket.getNumberOfAttachments() > 0) { %>
<dl class="row">
    <dt class="col-sm-4 col-md-3">Attachments</dt>
    <dd class="col-sm-8 col-md-9">
        <ul>
            <% for(Attachment attachment: ticket.getAttachments()) { %>
            <li><a href="tickets?action=download&ticketId=<%= ticketId %>&attachment=<%= attachment.getName() %>">
                <%= attachment.getName() %></a></li>
            <% } %>

<%--            <c:url value = "/tickets">--%>
<%--                <c:param name="action" value = "download"/>--%>
<%--                <c:param name="ticketId" value = "<%= ticketId %>"/>--%>
<%--                <c:param name="attachment" value = "<%= attachment.getName() %>"/>--%>
<%--                --%>
<%--            </c:url>--%>

        </ul>
    </dd>
</dl>
<% } %>
<%
    }
%>
<jsp:include page="/WEB-INF/include/footer.jsp" />