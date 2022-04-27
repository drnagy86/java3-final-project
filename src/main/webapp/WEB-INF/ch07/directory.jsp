<!doctype html>
<html lang="en" class="h-100">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Derrick's Java III</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href= "${pageContext.request.contextPath}/styles/bootstrap.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <script src="https://kit.fontawesome.com/a0e61c8ed9.js" crossorigin="anonymous"></script>
    <link href= "${pageContext.request.contextPath}/styles/main.css" rel="stylesheet" type="text/css">
    <link href= "${pageContext.request.contextPath}/styles/pagination.css" rel="stylesheet" type="text/css">

</head>
<body class="d-flex flex-column h-100">



<main class="flex-shrink-0 m-3" >
    <div class="ch7-container">
        <h2>Directory</h2>


        <ul>
            <c:forEach items="${people}" var="person" begin="${begin}" end="${end}" varStatus="status">
                <li>${person} | First: ${status.first} | Last: ${status.last} | Index: ${status.index} | Count: ${status.count} </li>
            </c:forEach>
        </ul>


        <div class="people">
            <c:forEach items="${people}" var="person" begin="${begin}" end="${end}">
                <div class="person">
                    <img src="${person.picture}" alt="<c:out value="${person.firstName}" />&nbsp;<c:out value="${person.lastName}" />">
                    <p><c:out value="${person.firstName}" />&nbsp;<c:out value="${person.lastName}" /></p>
                </div>
            </c:forEach>
        </div>


        <div class="pagination">
            <c:forEach var="i" begin="1" end="${maxPages}">
                <a <c:if test="${currentPage == i}">class="active"</c:if> href="<c:url value="/ch07/directory"><c:param name="page" value="${i}" /></c:url>">${i}</a>
            </c:forEach>
        </div>


    </div>

    <footer class="footer mt-auto py-3 bg-dark">
        <div class="container">
            <span class="text-light">Created by Derrick Nagy for Java III, Kirkwood Community College, 2022</span>
        </div>
    </footer>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script
            src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
            crossorigin="anonymous"></script>


    <script src="${pageContext.request.contextPath}/js/index.js"></script>
</body>
</html>