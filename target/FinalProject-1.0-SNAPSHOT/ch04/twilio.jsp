<%--
  Created by IntelliJ IDEA.
  User: k0519415
  Date: 2/14/2022
  Time: 1:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Twilio Demo</title>
</head>
<body>
<h1>Send a Text Message</h1>
<form action="sms-out" method="POST">
    <input type="tel" name="phone" placeholder="123-456-7890" pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}"><br><br>
    <input type="text" name="message" placeholder="Enter a message" maxlength="160" size="100"><br><br>
    <input type="submit" value="Send">
</form>
<%
    Boolean smsError = (Boolean)request.getAttribute("smsError");
    String errorMsg = (String)request.getAttribute("errorMsg");
    if(smsError != null && smsError) {
%>
<p>Error: <%= errorMsg %></p>
<%
} else if (smsError != null && !smsError) {
%>
<p>Message Sent!</p>
<%
    }
%>
<%
    Boolean callError = (Boolean)request.getAttribute("callError");
    String callErrorMsg = (String)request.getAttribute("callErrorMsg");
    String phone = (String)request.getAttribute("phone");
    String message = (String)request.getAttribute("message");
    if (phone == null) phone = "";
    if (message == null) message = "";
%>

<h2>Voice Message</h2>
<form action="voice-out" method="POST">
    <label for="phone">Enter a phone number</label>
    <input type="tel" name="phone" id="phone" placeholder="123-456-7890" value="<%=phone%>" ><br><br>
    <label for="message">Enter a message</label>
    <input type="text" name ="message" id="message" maxlength="160" value="<%=message%>" ><br><br>
    <input type="submit" value="Send">
</form>
<%
    if(callError != null && callError) {
%>
<p>Error: <%= callErrorMsg %></p>
<%
} else if(callError != null && !callError) {
%>
<p>Call sent!</p>
<%
    }
%>


</body>
</html>
