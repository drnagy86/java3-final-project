<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../include/header-homepage.jsp"/>
<main class="flex-shrink-0 m-3">
    <jsp:include page="../include/error-success-dismissable.jsp"/>
    <div class="container">
        <h1 class="mb-3 text-pink">Current Applicants</h1>

        <table class="table table-responsive table-striped table-bordered">
            <thead class="text-pink">
            <tr>
                <th>
                    Applying for
                </th>
                <th>
                    Full Name
                </th>
                <th>
                    Address
                </th>
                <th>
                    Cover Letter
                </th>
                <th>
                    Resume
                </th>
            </tr>

            </thead>
            <tbody>
            <c:forEach var="application" items="${applications}">
                <tr>
                    <input type="hidden" value="<c:out value="${application.jobApplicationID}" />">
                    <td>
                        <a class="text-pink"
                                href="
                            <c:url value="/final/jobs">
                                <c:param name="go" value="details"/>
                                <c:param name="id" value="${application.jobPosting.id}"/>
                            </c:url>"
                        >
                            <c:out value="${application.jobPosting.title}"/>
                        </a>
                    </td>
                    <td>
                        <p>
                            <c:out value="${application.applicant.firstName}"/> <c:out
                                value="${application.applicant.lastName}"/>
                        </p>
                    </td>
                    <td>
                        <p>
                            <c:out value="${application.address}"/>,
                            <c:out value="${application.city}"/>,
                            <c:out value="${application.state}"/>,
                            <c:out value="${application.postal}"/>
                        </p>
                    </td>
                    <td>
                        <a class="text-pink"
                           href="
                            <c:url value="/final/jobs">
                                <c:param name="go" value="downloadAttachment"/>
                                <c:param name="id" value="${application.jobPosting.id}"/>
                                <c:param name="toDownLoad" value="coverLetter"/>
                            </c:url>"
                        >
                            Cover Letter
                        </a>
                    </td>
                    <td>
                        <a class="text-pink"
                           href="
                            <c:url value="/final/jobs">
                                <c:param name="go" value="downloadAttachment"/>
                                <c:param name="id" value="${application.jobPosting.id}"/>
                                <c:param name="toDownLoad" value="resume"/>
                            </c:url>"
                        >
                            Resume
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>


</main>
<jsp:include page="../include/footer.jsp"/>