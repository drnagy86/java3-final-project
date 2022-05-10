<jsp:include page="WEB-INF/include/header-homepage.jsp"/>

<div class="container mt-2 mx-auto">
    <div class="row">
        <div class="row mt-4 p-5 bg-dark text-white rounded">
            <h1>Derrick Nagy's Java III Final Project</h1>
            <p>A site showcasing coursework from Java III at Kirkwood Community College, 2022</p>
        </div>
<div class="col-1"></div>
        <div class="col">
        <div class="row mt-4">
            <h2>Table of Contents</h2>
            <ul class="nav nav-pills">
                <li class="nav-item">
                    <a class="nav-link" href="#ch1" data-bs-toggle="tab">Chapters 1 + 2</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#ch3" data-bs-toggle="tab">Chapter 3</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#ch4" data-bs-toggle="tab">Chapter 4</a>

                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#ch5" data-bs-toggle="tab">Chapter 5</a>

                </li>


                <li class="nav-item">
                    <a class="nav-link" href="#ch6" data-bs-toggle="tab">Chapter 6</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="#ch7" data-bs-toggle="tab">Chapter 7</a>

                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="#final" data-bs-toggle="tab">Final Project</a>
                </li>
            </ul>
            <div class="tab-content" id="tabContent">
                <div id="ch1" class="tab-pane fade">
                    <ul class="mt-3">
                        <li><a href="register.html">Assignment1 - Registration Page</a></li>
                    </ul>
                </div>
                <div class="tab-pane fade" id="ch3">
                    <ul class="mt-3">
                        <li><a href="MyServlet">Demo - MyServlet</a></li>
                        <li><a href="add">Demo - Addition Servlet</a></li>
                        <li><a href="temp-converter">Assignment - Temperature Converter</a></li>
                    </ul>
                </div>
                <div class="tab-pane fade" id="ch4">
                    <ul class="mt-3">
                        <li><a href="ch04/twilio.jsp">Demo- SMS and Voice Out</a></li>
                        <li><a href="ch04/sms-in">ch04/sms-in</a></li>
                        <li><a href="ch04/voice-in">ch04/voice-in</a></li>
                        <li><a href="TemperatureConverterCh04b">Temperature Converter MVC Style</a></li>
                    </ul>
                </div>
                <div class="tab-pane fade" id="ch5">
                    <ul class="mt-3">
                        <li><a href="ch05/shop">Shopping Cart</a></li>
                        <li><a href="support/tickets">Support Tickets</a></li>
                        <li><a href="songs">Ch. 5 Homework</a></li>
                    </ul>
                </div>
                <div class="tab-pane fade" id="ch6">
                    <ul class="mt-3">
                        <li><a href="user/profile">In Class User Profile</a></li>
                        <li><a href="support/register?go=profile">Homework: Profile Page</a></li>
                        <li><a href="support/register?go=edit">Homework: Edit Profile Page</a></li>
                        <li><a href="support/register">Homework: Registration</a></li>
                        <li><a href="support/sendMessages">Homework: Admin send messages to all registered users</a>
                        </li>
                    </ul>
                </div>
                <div class="tab-pane fade" id="ch7">
                    <ul class="mt-3">
                        <li><a href="ch07/list">In Class: List</a></li>
                        <li><a href="ch07/list?lang=fr">In Class: List in French</a></li>
                        <li><a href="tysql.jsp">In Class: Teach Yourself SQL database</a></li>
                    </ul>
                </div>
                <div class="tab-pane fade active show" id="final">
                    <ul class="mt-3">
                        <li><a href="<c:url value="final/jobs"/>">Job Postings</a></li>
                        <li><a
                                href="
                                <c:url value="/final/jobs">
                                    <c:param name="go" value="applications"/>
                                </c:url>"

                        >Job Applications</a></li>
                    </ul>
                </div>
            </div>

        </div>
        </div>
        <div class="col-1"></div>
        <%--        <div class="row mt-4 mx-auto">--%>
        <%--            <h2>Table of Contents</h2>--%>
        <%--            <ul class="mx-auto m-2">--%>
        <%--                <li class="m-2"><h3 class="te">Chapters 1 + 2</h3>--%>
        <%--                    <ul>--%>
        <%--                        <li><a href="register.html">Assignment1 - Registration Page</a></li>--%>
        <%--                    </ul>--%>
        <%--                </li>--%>
        <%--                <li class="m-2"><h3>Chapter 3</h3>--%>
        <%--                    <ul>--%>
        <%--                        <li><a href="MyServlet">Demo - MyServlet</a></li>--%>
        <%--                        <li><a href="add">Demo - Addition Servlet</a></li>--%>
        <%--                        <li><a href="temp-converter">Assignment - Temperature Converter</a></li>--%>
        <%--                    </ul>--%>
        <%--                </li>--%>
        <%--                <li class="m-2"><h3>Chapter 4</h3></li>--%>
        <%--                <ul>--%>
        <%--                    <li><a href="ch04/twilio.jsp">Demo- SMS and Voice Out</a></li>--%>
        <%--                    <li><a href="ch04/sms-in">ch04/sms-in</a></li>--%>
        <%--                    <li><a href="ch04/voice-in">ch04/voice-in</a></li>--%>
        <%--                    <li><a href="TemperatureConverterCh04b">Temperature Converter MVC Style</a></li>--%>
        <%--                </ul>--%>
        <%--                <li class="m-2"><h3>Chapter 5</h3></li>--%>
        <%--                <ul>--%>
        <%--                    <li><a href="ch05/shop">Shopping Cart</a></li>--%>
        <%--                    <li><a href="support/tickets">Support Tickets</a></li>--%>
        <%--                    <li><a href="songs">Ch. 5 Homework</a></li>--%>
        <%--                </ul>--%>

        <%--                <li class="m-2"><h3>Chapter 6</h3></li>--%>
        <%--                <ul>--%>
        <%--                    <li><a href="user/profile">In Class User Profile</a></li>--%>
        <%--                    <li><a href="support/register?go=profile">Homework: Profile Page</a></li>--%>
        <%--                    <li><a href="support/register?go=edit">Homework: Edit Profile Page</a></li>--%>
        <%--                    <li><a href="support/register">Homework: Registration</a></li>--%>
        <%--                    <li><a href="support/sendMessages">Homework: Admin send messages to all registered users</a></li>--%>
        <%--                </ul>--%>
        <%--                <li class="m-2"><h3>Chapter 7</h3></li>--%>
        <%--                <ul>--%>
        <%--                    <li><a href="ch07/list">In Class: List</a></li>--%>
        <%--                    <li><a href="ch07/list?lang=fr">In Class: List in French</a></li>--%>
        <%--                    <li><a href="tysql.jsp">In Class: Teach Yourself SQL database</a></li>--%>
        <%--                </ul>--%>
        <%--                <li class="m-2"><h3>Final Project</h3></li>--%>
        <%--                <ul>--%>
        <%--                    <li><a href="<c:url value="final/jobs"/>">Job Postings</a></li>--%>
        <%--                    <li><a--%>
        <%--                            href="--%>
        <%--                                <c:url value="/final/jobs">--%>
        <%--                                    <c:param name="go" value="applications"/>--%>
        <%--                                </c:url>"--%>

        <%--                    >Job Applications</a></li>--%>
        <%--                </ul>--%>

        <%--                &lt;%&ndash;                list?lang=fr&ndash;%&gt;--%>
        <%--            </ul>--%>
        <%--        </div>--%>


    </div>


</div>


<jsp:include page="WEB-INF/include/footer.jsp"/>