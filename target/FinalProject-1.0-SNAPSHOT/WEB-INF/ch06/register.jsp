<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../include/header-homepage.jsp" />
<main class="flex-shrink-0 m-3" >


    <div class="row">

        <div class="col-md-10 mx-auto">
            <c:if test="${fn:length(errorMessage) > 0}">
                <div class="alert alert-dismissible alert-danger">
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                <strong class="text-white">Oh, no! There was an error.</strong>
                <p class="text-white">${errorMessage}</p>
                </div>
            </c:if>

            <c:if test="${smsError}">
                <div class="alert alert-dismissible alert-danger">
                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                    <strong class="text-white">Oh, no! There was an error.</strong>
                    <p class="text-white">${errorMsg}</p>
                </div>
            </c:if>
            <div class="row">

                <div class="form-group col-md-4 mx-auto">
                    <c:choose>
                        <c:when test="${fn:length(user.firstName) > 0 && fn:length(user.lastName) > 0}">
                            <h2>${user.firstName} ${user.lastName}'s Profile</h2>
                        </c:when>

                        <c:when test="${fn:length(user.firstName) == 0 && fn:length(user.lastName) == 0}">
                            <h2> Create a Profile</h2>
                        </c:when>
                    </c:choose>
                </div>
            </div>
            <div class="row">
                <form method="post" action="${pageContext.request.contextPath}/support/register">
                    <fieldset>
                        <input name="userID" id="userID" class="form-control" type="hidden" value="${user["userId"]}"/>
                        <div class="form-group col-md-4 mx-auto">
                            <label for="firstName" class="form-label">First Name: </label>
                            <input name="firstName" id="firstName" class="form-control" type="text" value="${user.firstName}"/>
                        </div>
                        <div class="form-group col-md-4 mx-auto">
                            <label for="lastName" class="form-label">Last Name: </label>
                            <input name="lastName" id="lastName" class="form-control" type="text" value="${user.lastName}"/>
                        </div>
                        <div class="form-group col-md-4 mx-auto">
                            <label for="userName" class="form-label">User Name / Email: </label>
                            <input name="userName" id="userName" class="form-control" type="text" value="${user.username}"/>
                        </div>
                        <div class="form-group col-md-4 mx-auto">
                            <label for="phoneNumber" class="form-label">Phone Number: </label>
                            <input name="phoneNumber" id="phoneNumber" class="form-control" type="text" value="${user.phoneNumber}"/>
                            <div class="row">
                                <p>Make sure your phone is verified with a text message sent to your phone. Edit number and save before testing.</p>
                            </div>
                            <div class="row">
                                <a class="btn btn-outline-dark mt-2 " href="${pageContext.request.contextPath}/support/register?go=testPhone&phone=${user.phoneNumber}">Test</a>
                                <c:if test="${smsSuccess}">
                                    <p class="text-primary">Message successfully sent. If you didn't receive it, please try to edit your number.</p>
                                </c:if>
                            </div>

                        </div>
                        <div class="form-group col-md-4 mx-auto">
                            <label for="password" class="form-label">Password: </label>
                            <input name="password" id="password" class="form-control" type="text" value="${user.password}"/>
                        </div>
                        <div class="form-group col-md-4 mx-auto">
                            <label for="passwordRetype" class="form-label">Retype Password To Edit: </label>
                            <input name="passwordRetype" id="passwordRetype" class="form-control" type="text"/>
                        </div>
                        <div class="form-group col-md-4 mx-auto">
                            <input type="submit" class="btn btn-primary mt-2 " value="Save">
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>

<%--        <c:if test="${isAdmin}">--%>
<%--            <div class="col-md">--%>
<%--                <form method="get" action="${pageContext.request.contextPath}/support/register?go=messageToSend">--%>
<%--                    <div class="form-group col-md-4 mx-auto">--%>
<%--                        <label for="messageToSend" class="form-label">Message to Send all Users:</label>--%>
<%--                        <input name="messageToSend" id="messageToSend" class="form-control" type="text" value="${user.password}"/>--%>
<%--                    </div>--%>
<%--                    <div class="form-group col-md-4 mx-auto">--%>
<%--                        <input type="submit" class="btn btn-primary mt-2 " value="Send">--%>
<%--                    </div>--%>
<%--                </form>--%>




<%--            </div>--%>
<%--        </c:if>--%>

    </div>



</main>


<jsp:include page="../include/footer.jsp" />