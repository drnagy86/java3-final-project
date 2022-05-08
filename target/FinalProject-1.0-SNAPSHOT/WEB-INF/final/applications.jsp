<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../include/header-homepage.jsp"/>
<main class="flex-shrink-0 m-3">
    <jsp:include page="../include/error-success-dismissable.jsp"/>

    <c:forEach var="application" items="${applications}">
    <div class="row">
        ${application.jobApplicationID}
        ${application.jobPosting.title}
        ${application.applicant.firstName}
        ${application.applicant.lastName}
        ${application.address}
        ${application.city}
        ${application.state}
        ${application.postal}
        ${application.coverLetter}
        ${application.resume}

    </div>

    </c:forEach>

</main>
<jsp:include page="../include/footer.jsp"/>