<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/include/header.jsp" />
<h1 class="mt-5">Create a Support Ticket</h1>
<form method="POST" action="tickets" class="form-control m-1">
    <div class="form-group mb-2">
        <label for="name">Your Name</label>
        <input type="text" name="name" id="name" class="form-control">
    </div>
    <div class="form-group mb-2">
        <label for="subject">Subject</label>
        <input type="text" name="subject" id="subject" class="form-control">
    </div>
    <div class="form-group mb-2">
        <label for="message">Message</label>
        <textarea name="message" id="message" rows="5" cols="30" class="form-control"></textarea>
    </div>
    <input type="submit" value="Send" class="btn btn-primary mb-5">
</form>
<jsp:include page="/WEB-INF/include/footer.jsp" />