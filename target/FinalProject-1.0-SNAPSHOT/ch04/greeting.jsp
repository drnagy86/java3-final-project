<%-- This is a JSP Comment --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    private final String DEFAULT_USER = "Guest";
%>

<%
    String user = request.getParameter("user");
    if(user == null || user.equals("")) {
        user = DEFAULT_USER;
    }
    String[] fruits = request.getParameterValues("fruit");
%>


<!DOCTYPE html>
<html lang="en-US">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Greeting Page</title>
    <link href="../styles/main.css" rel="stylesheet">
</head>
<body>
<% if(fruits == null) { %>
<h1>Hello <%= user %>!</h1>
<form action="greeting.jsp" method="POST">

    <% if(user.equals(DEFAULT_USER)) { %>
    <label for="user">Enter your name:</label>
    <input type="text" name="user" id="user">
    <% } else { %>
    <input type="hidden" name="user" value="<%= user %>">
    <% } %>
    <p>Select your favorite fruits:</p>
    <input type="checkbox" name="fruit" value="Apple" id="apple">
    <label for="apple">Apple</label><br>
    <input type="checkbox" name="fruit" value="Banana" id="banana">
    <label for="banana">Banana</label><br>
    <input type="checkbox" name="fruit" value="Orange" id="orange">
    <label for="orange">Orange</label><br>
    <input type="checkbox" name="fruit" value="Pineapple" id="pineapple">
    <label for="pineapple">Pineapple</label>

    <p><input type="submit" value="Go"></p>
</form>
<% } else { %>
<h1><%= user %>'s Favorite Fruits</h1>
<ul>
    <% for(String fruit: fruits) { %>
    <li><%= fruit %></li>
    <% } %>
</ul>
<a href="greeting.jsp?user=<%= user %>">Go back</a>
<% } %>
</body>
</html>
