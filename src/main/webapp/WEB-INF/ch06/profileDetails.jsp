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
                <div>
                    <div>
                        <div class="col-md-4 mx-auto">
                            <p class="text-primary">First Name: </p>
                            <p  id="firstName">${user.firstName}</p>
                        </div>
                        <div class="col-md-4 mx-auto">
                            <p class="text-primary" >Last Name: </p>
                            <p id="lastName" >${user.lastName}</p>
                        </div>
                        <div class="col-md-4 mx-auto">
                            <p class="text-primary">User Name / Email: </p>
                            <p id="userName" >${user.username}</p>
                        </div>
                        <div class="col-md-4 mx-auto">
                            <p class="text-primary">Phone Number: </p>
                            <p id="phoneNumber">${user.phoneNumber}</p>
                        </div>
                        <div class="col-md-4 mx-auto">
                            <a class="btn btn-outline-dark mt-2 " href="${pageContext.request.contextPath}/support/register?go=testPhone&phone=${user.phoneNumber}">Test Phone Number</a>
                            <div class="row-height-30">
                            <c:if test="${smsSuccess}">
                                <p class="text-primary">Message successfully sent. If you didn't receive it, please try to edit your number.</p>
                            </c:if>
                            </div>
                        </div>
                        <div class="col-md-4 mx-auto mt-2">
                            <a class="btn btn-primary" href="${pageContext.request.contextPath}/support/register?go=edit">Edit Profile</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>



</main>


<jsp:include page="../include/footer.jsp" />