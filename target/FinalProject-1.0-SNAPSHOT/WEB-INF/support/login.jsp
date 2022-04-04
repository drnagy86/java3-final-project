<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--set the title to something--%>
<jsp:include page="../include/header-homepage.jsp" />
<main class="flex-shrink-0 m-3" >

    <div class="mx-auto p-2">

    <h2 class="mt-3">Login</h2>
        <%
            Boolean loggedOut = (Boolean) request.getAttribute("loggedOut");
            if ( loggedOut != null && loggedOut){
        %>

        <%}%>
    <p>You must log in to access the customer support site.</p>
    <form method="POST" action="login">
        <label class="form-label" for="username">Username</label>
        <input class="form-control" type="text" name="username" id="username" /><br><br>
        <label class="form-label" for="password">Password</label>
        <input class="form-control" type="password" name="password" id="password" /><br><br>

        <%
            Boolean loginFailed = (Boolean)request.getAttribute("loginFailed");
            if(loginFailed == null) loginFailed = false;
            if(loginFailed) {
        %>
        <div>
            <p class="text-danger"><%= request.getAttribute("errorMsg")%></p>
        </div>
        <% } %>


        <input class="btn btn-primary" type="submit" value="Log In" />
    </form>

    </div>
</main>


<jsp:include page="../include/footer.jsp" />