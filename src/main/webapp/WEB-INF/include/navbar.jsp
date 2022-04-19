<header>
    <!-- Fixed navbar -->
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-primary">
        <div class="container-fluid">
            <a class="navbar-brand">Chapter 4b</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav me-auto mb-2 mb-md-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="<c:url value = "/ch04b/average"/>">Average Calculator</a>
                    </li>
                    <li class="nav-item">
<%--                        <a class="nav-link" href="../support/tickets">View Tickets</a>--%>
                        <a class="nav-link" href="<c:url value="/support/tickets"/> ">View Tickets</a>
                    </li>
                    <li class="nav-item">
<%--                        <a class="nav-link" href="../support/tickets?action=create" tabindex="-1" aria-disabled="true">Create Ticket</a>--%>
                        <a class="nav-link" href="<c:url value="/support/tickets"><:c:param name="action" value="create"/></c:url>" tabindex="-1" aria-disabled="true">Create Ticket</a>
                    </li>
                </ul>
<%--                search bar--%>
<%--                <form class="d-flex">--%>
<%--                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">--%>
<%--                    <button class="btn btn-outline-success" type="submit">Search</button>--%>
<%--                </form>--%>
                <ul class="mb-auto navbar-nav me-1">
                    <% if (session.getAttribute("username") != null) {%>
                    <li class="nav-item">
                        <a class="nav-link" href="login?logout" >Logout</a>

                    </li>
                    <% } else {%>
                    <li class="nav-item">
                        <a class="nav-link" href="login" >Login</a>
                    </li>
                    <% } %>



                </ul>
            </div>
        </div>
    </nav>
</header>