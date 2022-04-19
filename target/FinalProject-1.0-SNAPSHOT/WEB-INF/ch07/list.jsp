<jsp:include page="../include/header-homepage.jsp" />
<main class="flex-shrink-0 m-3" >
    <h1>Contact List</h1>
    <c:choose>
        <c:when test="${fn:length(contacts) == 0}">
            <p>There are zero contacts in address book</p>
        </c:when>
        <c:when test="${fn:length(contacts) == 1}">
            <p>There is one contact in address book</p>
        </c:when>

        <c:otherwise>
            <p>There are ${fn:length(contacts)} contacts in the address book </p>
        </c:otherwise>
    </c:choose>

<%--    all or nothing tag--%>
    <c:if test="${fn:length(contacts) > 0}">
        <c:forEach var="user" items="${contacts}" >
            <p>
                <strong>User Id:</strong> <c:out value="${contact.userId}" /><br>
                <strong>User Name:</strong> <c:out value="${user.username}" /><br>
                <strong>First Name:</strong> <c:out value="${user.firstName}" /><br>
                <strong>Last Name:</strong> <c:out value="${user.lastName}" /><br>
                <strong>Phone Number:</strong> <c:out value="${user.phoneNumber}" /><br>

                <c:if test="${user.birthday != null}">
                    <strong>Birthday:</strong> <c:out value="${user.birthday}" /><br>
                </c:if>
                <strong>Last Updated:</strong> <c:out value="${user.lastUpdated}" /><br>
                <strong>Balance:</strong> <c:out value="${user.balance}" />
            </p>

        </c:forEach>
    </c:if>




</main>
<jsp:include page="../include/footer.jsp" />