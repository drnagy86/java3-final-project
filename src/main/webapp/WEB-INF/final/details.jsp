<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../include/header-homepage.jsp"/>
<main class="flex-shrink-0">
    <jsp:include page="../include/error-success-dismissable.jsp"/>
    <div class="row my-3 ms-1">
        <div class="col-md"></div>
        <div class="col-md-10 me-auto">
            <a class="link-primary" href="<c:url value="/final/jobs"/>">Back to Job Postings</a>
        </div>
        <div class="col-md"></div>

    </div>

    <div class="container-fluid bg-secondary my-4 py-4">
        <div class="row">
            <div class="col-md"></div>
            <div class="col-md-10 me-auto">

                <h1 class="text-pink">${job.title}</h1>
                <div class="d-flex flex-row">
                    <span class="pe-2">
                        <i class="bi bi-geo"></i>
                        ${job.city}, ${job.state}
                    </span>
                    <span class="px-2">
                        <i class="bi bi-clock"></i>
                        <c:if test="${job.fullTime}">
                            Full Time
                        </c:if>
                        <c:if test="${!job.fullTime}">
                            Part Time
                        </c:if>
                    </span>
                    <span class="px-2">
                        <i class="bi bi-clipboard"></i>
                        ${job.experience}
                    </span>
                </div>

            </div>
            <div class="col-md"></div>
        </div>
    </div>

    <div class="row m-3">
        <div class="col-md"></div>
        <div class="col-md-4">
            <div class="row">
                <div class="col-md-4 me-auto">
                    <strong>Title:</strong>
                </div>
                <div class="col-md me-auto">
                    <p class="text-pink">${job.title}</p>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4 me-auto">
                    <strong>Date Posted:</strong>
                </div>
                <div class="col-md me-auto">
                    <p class="text-pink"><fmt:formatDate value="${job.dateCreatedAsDate}" type="date"
                                                         dateStyle="medium"/></p>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4 me-auto">
                    <strong>Department:</strong>
                </div>
                <div class="col-md me-auto">
                    <p class="text-pink">${job.department}</p>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4 me-auto">
                    <strong>Wage Category:</strong>
                </div>
                <div class="col-md me-auto">
                    <p class="text-pink">${job.wageCategory}</p>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4 me-auto">
                    <strong>Reports to:</strong>
                </div>
                <div class="col-md me-auto">
                    <p class="text-pink">${job.reportsTo}</p>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4 me-auto">
                    <strong>Location:</strong>
                </div>
                <div class="col-md me-auto">
                    <p class="text-pink">${job.city}, ${job.state}</p>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4 me-auto">
                    <strong>Salary Range:</strong>
                </div>
                <div class="col-md me-auto">
                    <c:if test="${job.fullTime}">
                        <p class="text-pink">$${job.salary} annually</p>
                    </c:if>
                    <c:if test="${!job.fullTime}">
                        <p class="text-pink">$${job.salary} per hour</p>
                    </c:if>
                </div>
            </div>

            <div class="row-height-30"></div>

            <div class="row">
                <div class="row">
                    <strong class="text-decoration-underline mb-2">Job Statement</strong>
                </div>
                <div class="row">
                    <p class="text-pink">${job.jobStatement}</p>
                </div>
            </div>
            <div class="row-height-30"></div>
            <div class="row">
                <div class="row">
                    <strong class="text-decoration-underline mb-2">Job Duties</strong>
                </div>
                <div class="row">
                    <p class="text-pink">${job.jobDuties}</p>
                </div>
            </div>

        </div>
        <div class="col-md-4">
            <form enctype="multipart/form-data" method="POST" action="
                <c:url value="/final/jobs">
                    <c:param name="go" value="apply"/>
                </c:url>">
                <input name="jobID" type="hidden" value="${job.id}">
                <strong>Apply for this position</strong>
                <div class="d-flex flex-row mb-2">
                    <small>REQUIRED </small>
                    <p class="text-pink">*</p>
                </div>
                <div class="my-2">
                    <label for="firstName" class="form-label">First Name <span class="text-pink">*</span></label>
                    <input name="firstName" id="firstName" type="text" class="form-control">
                    <small class="text-danger">${userError}</small>


                </div>
                <div class="my-2">
                    <label for="lastName" class="form-label">Last Name <span class="text-pink">*</span></label>
                    <input name="lastName" id="lastName" type="text" class="form-control">
                    <small class="text-danger">${userError}</small>


                </div>
                <div class="my-2">
                    <label for="emailAddress" class="form-label">Email <span class="text-pink">*</span></label>
                    <input name="emailAddress" id="emailAddress" type="email" class="form-control">
                    <small class="text-danger">
                        ${userError}
                    </small>

                </div>
                <div class="my-2">
                    <label for="address" class="form-label">Address <span class="text-pink">*</span></label>
                    <input name="address" id="address" type="text" class="form-control mb-2" placeholder="Address">
                    <small class="text-danger">
                        ${addressError}
                    </small>

                    <div class="d-flex">
                        <input name="city" id="city" type="text" class="form-control me-2" placeholder="City">
                        <input name="state" id="state" type="text" class="form-control me-2 w-50" placeholder="State">
                        <input name="postal" id="postal" type="text" class="form-control w-50" placeholder="Postal">
                    </div>

                    <div class="row">
                        <small class="mx-1 text-danger">${cityError}</small>
                    </div>
                    <div class="row">
                        <small class="mx-1 text-danger">${stateError}</small>
                    </div>
                    <div class="row">
                        <small class="mx-1 text-danger">${postalError}</small>
                    </div>

                </div>
                <div class="row my-2">
                    <label for="coverLetter" class="form-label col-sm-3 me-auto">Cover Letter</label>
                    <input name="coverLetter" id="coverLetter" type="file"
                           class="btn btn-outline-dark ms-auto me-3 col">

                </div>
                <div class="row my-2">
                    <label for="resume" class="form-label col-sm-3 me-auto">Resume <span
                            class="text-pink">*</span></label>
                    <input name="resume" id="resume" type="file" class="btn btn-outline-dark ms-auto me-3 col">
                    <small class="text-danger">${resumeError}</small>
                </div>
                <div class="my-3">
                    <div class="row">
                        <input type="submit" class="btn btn-pink mx-3 " value="SUBMIT APPLICATION">
                    </div>
                </div>

            </form>
        </div>
        <div class="col-md"></div>
    </div>


</main>
<jsp:include page="../include/footer.jsp"/>