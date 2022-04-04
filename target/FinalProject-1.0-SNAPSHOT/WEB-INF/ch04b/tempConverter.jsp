<%@ page import="com.nagy.ch04b.Temperature" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

    Boolean error = (Boolean)request.getAttribute("error");
    String errMessage = (String)request.getAttribute("errorMsg");

    Temperature temperature = (Temperature)request.getAttribute("temperature");

    String degrees = (String)request.getAttribute("degrees");
    //String degreesFahrenheit = (String)request.getAttribute("degreesFahrenheit");

    if (degrees == null) degrees = "";
    //if (degreesFahrenheit == null) degreesFahrenheit = "";
    if (error == null) error = false;
    if (!error) errMessage = "";


%>
<jsp:include page="../include/header-homepage.jsp" />
<link href= "${pageContext.request.contextPath}/styles/ch03Style.css" rel="stylesheet" type="text/css">
<main class="flex-shrink-0">
    <div class="container-fluid p-5">
        <div class="container-sm p-5 bg-dark text-white text-center border">
            <h1>Convert Temperature</h1>
            <p>Convert from Fahrenheit to Celsius or from Celsius to Fahrenheit</p>
        </div>
        <div class="container-sm p-5 border text-center ">
            <form method="POST" action="TemperatureConverterCh04b">
                <div class="row g-3 align-items-center" >
                    <div class="d-flex justify-content-center">
                        <div class="col-auto form-floating mb-3">
                            <input type="text" class="form-control" name="degrees" id="degreesF" value="<%= degrees %>" >
                            <label for="degreesF" id="lblDegreesF">Temperature:</label>
                        </div>
                        <div class="col-sm-2">
<%--                            <input type="submit" class="btn btn-primary" id="submitButton" value=">>"/>--%>
                        </div>
                        <div class="col-auto form-floating mb-3">
<%--                            <input type="text" class="form-control" name="degreesCelsius" id="degreesC" value="<%= degreesCelsius %>" >--%>
<%--                            <label for="degreesC" id="lblDegreesC">&#8451;</label>--%>
                            <input type="submit" class="btn btn-primary" id="submitButton" value="Convert"/>
                        </div>
                    </div>
                    <% if(error != null && error) { %>
                    <div class="row mx-auto" >
                        <p class="text-danger"><%= errMessage %></p>
                    </div>
                    <% } %>
                        <div class="d-flex justify-content-center">
                        <div class="form-check mx-3">
                            <input type="radio" class="form-check-input" id="radF" name="radConversion" value="f"checked>
                            <label class="form-check-label" for="radF">&#8457</label>

                        </div>
                        <p>|</p>
                        <div class="form-check mx-3">
                            <input type="radio" class="form-check-input" id="radC" name="radConversion" value="c">
                            <label class="form-check-label" for="radC">&#8451;</label>
<%--                            <div class="invalid-feedback">--%>
<%--                                <p id="celValidation">Please enter a number.</p>--%>
<%--                            </div>--%>
                        </div>
                    </div>
                </div>
            </form>

            <%if (temperature != null ) { %>

            <table class="table mx-auto w-50 mt-3">
                <thead>
                <tr>
                    <th scope="col">&#8457;</th>
                    <th scope="col">&#8451;</th>
                </tr>
                </thead>
                <tbody class="table-striped">
                <td><%= temperature.getDegreesFahrenheit().toPlainString() %>&#8457;</td>
                <td><%= temperature.getDegreesCelsius().toPlainString() %>&#8451;</td>
                </tbody>
            </table>

            <% } %>

        </div>
    </div>
</main>

<jsp:include page="../include/footer.jsp" />