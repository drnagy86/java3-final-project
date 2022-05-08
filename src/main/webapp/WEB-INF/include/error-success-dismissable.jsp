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
    </div>
</div>

