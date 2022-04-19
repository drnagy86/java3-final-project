<%@ page import="com.nagy.ch06.User" %>
<%@ page import="java.util.Map" %>

<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="${pageContext.request.contextPath}">Derrick's Java III Project</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor02" aria-controls="navbarColor02" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarColor02">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/ch05/shop">Ch05</a>
                    </li>

                </ul>
                <ul class="mb-auto navbar-nav me-1">
                    <% if (session.getAttribute("username") != null) {%>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/support/register">Profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/support/login?logout" >Logout</a>
                    </li>
                    <% } else {%>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/support/login" >Login</a>
                    </li>
                    <% } %>
                </ul>
<%--                <ul class="navbar-nav ms-auto">--%>
<%--                    <li class="nav-item">--%>
<%--                        <a class="btn btn-dark btn-lg" target="_blank" data-toggle="tooltip" title="My GitHub" href="https://github.com/drnagy86"><i class="fab fa-github"></i> My GitHub</a>--%>
<%--                    </li>--%>
<%--                </ul>--%>

            </div>
        </div>
    </nav>
</header>