<jsp:include page="WEB-INF/include/header-homepage.jsp"/>

<div class="container mt-2 mx-auto">

    <h2>Products</h2>
    <form action="tysql.jsp" method="GET">
        Price less than:
        <select name="price" class="form-select">
            <option>4</option>
            <option>7</option>
            <option>10</option>
            <option>20</option>
        </select>
        <input type="submit" value="Go">
    </form>

    <sql:query dataSource="${db}" var="result">
        SELECT *
        FROM products
        WHERE prod_price < ?;
        <sql:param value="${param.price}"/>
    </sql:query>
    <table class="table">
        <thead>
        <tr>
            <th>Product ID</th>
            <th>Vendor ID</th>
            <th>Product Name</th>
            <th>Product Price</th>
            <th>Product Description</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${result.rows}">
            <tr>
                <td>${product.prod_id}</td>
                <td>${product.vend_id}</td>
                <td>${product.prod_name}</td>
                <td>${product.prod_price}</td>
                <td>${product.prod_desc}</td>

            </tr>
        </c:forEach>

        </tbody>
        <tfoot></tfoot>
    </table>

</div>

<jsp:include page="WEB-INF/include/footer.jsp" />