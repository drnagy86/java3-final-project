<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../include/header-homepage.jsp" />
<main class="flex-shrink-0 m-3" >
    <c:if test="${fn:length(errorMessage) > 0}">
        <div class="alert alert-dismissible alert-info">
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            <strong class="text-white">Results of sending message.</strong>
            <p class="text-white">${errorMessage}</p>
        </div>
    </c:if>


    <c:if test="${!isAdmin}">
        <div class="col-md">
            <h1>No Permissions</h1>
            <p>Only administrators can log on to this page.</p>
        </div>
    </c:if>

            <c:if test="${isAdmin}">


                <div class="col-md">
                    <form method="post" action="${pageContext.request.contextPath}/support/sendMessages">
                        <div class="form-group col-md-4 mx-auto">
                            <h1>SMS Message All Registered Users</h1>
                            <label for="messageToSend" class="form-label">Message to Send all Users:</label>
                            <textarea rows="3" name="messageToSend" id="messageToSend" class="form-control"></textarea>
                        </div>
                        <div class="form-group col-md-4 mx-auto">
                            <input type="submit" class="btn btn-primary mt-2 " value="Send">
                        </div>
                    </form>




                </div>
            </c:if>

</main>


<jsp:include page="../include/footer.jsp" />