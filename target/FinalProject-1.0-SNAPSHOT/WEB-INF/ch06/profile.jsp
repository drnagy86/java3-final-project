<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--set the title to something--%>
<jsp:include page="../include/header-homepage.jsp" />
<main class="flex-shrink-0 m-3" >

    <div class="row">
        <h2>${fullName}</h2>
    </div>

    <div class="row">
        <ul>
            <li><strong>userId:</strong> ${user["userId"]}</li>
            <li><strong>userName:</strong> ${user.username}</li>
            <li><strong>firstName:</strong> ${user.firstName}</li>
            <li><strong>lastName:</strong> ${user.lastName}</li>
            <li>
                <ul>
                    <li><strong>active</strong> ${user.permissions.active}</li>
                    <li><strong>admin</strong> ${user["permissions"]["admin"]}</li>
                </ul>
            </li>
        </ul>
    </div>

    <div class="row">
    <%--    <p>${fullName}</p>--%>
    <%--    <p>The attribute 'a' is set on the ${a} object</p>--%>
    <%--    <p>The attribute 'a' is set on the ${sessionScope.a} object</p>--%>

        <h2>Array data</h2>
        <p>The first user is ${usersArr[0].firstName}</p>
        <p>The second user is ${usersArr[1].firstName}</p>

        <p>The second vowel is ${vowels[3]}</p>

    </div>

    <div class="row">
        <p>Copyright &copy; ${currentYear}</p>
    </div>

</main>


<jsp:include page="../include/footer.jsp" />