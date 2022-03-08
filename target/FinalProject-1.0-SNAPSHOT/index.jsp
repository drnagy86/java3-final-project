<%--<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>JSP - Hello World</title>--%>
<%--    <link href="/styles/main.css" rel=\"stylesheet\"/>--%>
<%--</head>--%>
<%--<body>--%>

<jsp:include page="WEB-INF/include/header-homepage.jsp" />

<div class="container mt-2 mx-auto">
    <div class="row">
        <div class="col-md-5 mt-4 p-5 bg-dark text-white rounded">
            <h1>Derrick Nagy's Java III Final Project</h1>
            <p>A site showcasing coursework from Java III</p>
        </div>

        <div class="col-md-5 mt-4 mx-auto">
            <h2>Table of Contents</h2>
            <br/>
            <ul class="">
                <li><h3 class="te">Chapters 1 + 2</h3>
                    <ul>
                        <li><a href="register.html">Assignment1 - Registration Page</a></li>
                    </ul>
                </li>
                <br/>
                <li><h3>Chapter 3</h3>
                    <ul>
                        <li><a href="MyServlet">Demo - MyServlet</a></li>
                        <li><a href="add">Demo - Addition Servlet</a></li>
                        <li><a href="temp-converter">Assignment - Temperature Converter</a></li>
                    </ul>
                </li>
                <br/>
                <li><h3>Chapter 4</h3></li>
                <ul>
                    <li><a href="ch04/twilio.jsp">Demo- SMS and Voice Out</a></li>
                    <li><a href="ch04/sms-in">ch04/sms-in</a></li>
                    <li><a href="ch04/voice-in">ch04/voice-in</a></li>
                    <li><a href="TemperatureConverterCh04b">Temperature Converter MVC Style</a></li>
                </ul>
                <br/>
            </ul>
        </div>

    </div>



</div>




<jsp:include page="WEB-INF/include/footer.jsp" />