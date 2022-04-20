<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--set the title to something--%>
<jsp:include page="../include/header-homepage.jsp" />
<main class="flex-shrink-0 m-3" >

    <div class="p-2 row">

        <div class="col-md-6 mx-auto">
    <h2 class="mt-3">Login</h2>
        <%
            Boolean loggedOut = (Boolean) request.getAttribute("loggedOut");
            if ( loggedOut != null && loggedOut){
        %>

        <%}%>
    <p>You must log in to access this part of the site. For testing purposes, the username is "marc@school.com" and the password is "P@ssw0rd".</p>
<p>Or <a href="${pageContext.request.contextPath}/support/register?go=register">register</a> to create a new account.</p>


    <form method="POST" action="login" class="mt-3">
        <label class="form-label" for="username">Username</label>
        <input class="form-control" type="text" name="username" id="username" /><br><br>
<%--        <input class="form-control" type="text" name="username" id="username" value="marc@school.com" /><br><br>--%>
        <label class="form-label" for="password">Password</label>
        <input class="form-control" type="password" name="password" id="password" /><br><br>
<%--        <input class="form-control" type="password" name="password" id="password" value="P@ssw0rd" /><br><br>--%>

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


    </div>
</main>


<jsp:include page="../include/footer.jsp" />