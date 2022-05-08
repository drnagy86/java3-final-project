<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../include/header-homepage.jsp"/>
<main class="flex-shrink-0 m-3">
    <jsp:include page="../include/error-success-dismissable.jsp"/>


    <div class="col-md-8 mx-auto">
        <h2 class="mb-3 text-pink">Scratch Cupcakes : Current Open Roles</h2>
        <p class="text-pink">We are currently looking for new members to join our team. Click below to find out more and
            apply!</p>
        <c:forEach var="jobPost" items="${jobPostSet}" begin="${begin}" end="${end}">
            <div class="list-group">
                <a
                        href="
                                <c:url value="/final/jobs">
                                    <c:param name="go" value="details"/>
                                    <c:param name="id" value="${jobPost.id}"/>
                                </c:url>"
                        class="list-group-item list-group-item-action flex-column align-items-start"
                >
                    <div class="d-flex w-100 justify-content-between">
                        <h4 class="mb-1 text-pink">${jobPost.title}</h4>
                        <small>
                            <fmt:formatDate value="${jobPost.dateCreatedAsDate}" type="date" dateStyle="medium"/>
                        </small>
                    </div>
                    <p class="mb-1">${jobPost.jobStatement}</p>
                    <small>Experience: ${jobPost.experience}</small>
                </a>
            </div>
        </c:forEach>
        <nav class="mt-2">
            <ul class="pagination justify-content-center">
                <li class="page-item">
                    <a class="page-link" href="
                                <c:url value="/final/jobs">
                                    <c:param name="page" value="${1}"/>
                                </c:url>
                                ">
                        <span>
                        &laquo;
                    </span>
                    </a>
                </li>
                <c:forEach var="i" begin="1" end="${maxPages}">
                    <li class="page-item <c:if test="${currentPage == i}">active</c:if>">
                        <a class="page-link"
                           href="<c:url value="/final/jobs"><c:param name="page" value="${i}" /></c:url>">${i}</a>
                    </li>
                </c:forEach>
                <li class="page-item">
                    <a class="page-link" href="
                                <c:url value="/final/jobs">
                                    <c:param name="page" value="${maxPages}"/>
                                </c:url>
                                ">
                        <span>
                        &raquo;
                    </span>
                    </a>
                </li>
            </ul>
        </nav>

    </div>


</main>
<jsp:include page="../include/footer.jsp"/>