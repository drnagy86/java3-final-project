<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../include/header.jsp"/>


<!-- Begin page content -->
<main class="flex-shrink-0">
    <div class="container">
        <h1 class="mt-5">Average Calculator</h1>
        <p class="lead">Enter three numbers and click Calculate to find the average</p>

        <form method="POST" action="average" class="container">
            <div class="row">
             <div class="form-group mb-2 col-sm">
                <label for="num1">Number 1</label>
                <input type="number" id="num1" name="num1" class="form-control">
            </div>
                <div class="form-group mb-2 col-sm" >
                <label for="num2">Number 2</label>
                <input type="number" id="num2" name="num1" class="form-control">
                </div>
            <div class="form-group mb-2 col-sm">
                <label for="num3">Number 3</label>
                <input type="number" id="num3" name="num1" class="form-control">
            </div>
            </div>
            <input type="submit" value="Calculate" class="btn btn-primary mb-2">
        </form>


    </div>
</main>







<jsp:include page="../include/footer.jsp"/>
