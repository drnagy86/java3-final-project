<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--set the title to something--%>
<jsp:include page="../include/header-homepage.jsp"/>
<main class="flex-shrink-0 m-3">
    <p><a href="shop?page=browse">Browse Products</a></p>
    <h2>Your Shopping Cart</h2>
    <%
        Map<Integer, String> products = (Map<Integer, String>) request.getAttribute("products");
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
    %>

<%--    <c:when test="${cart == null || cart.size() == 0}">--%>
<%--        <p>Your cart is empty</p>--%>
<%--    </c:when>--%>
<%--    <c:otherwise>--%>

<%--    <table class="table table-hover">--%>
<%--        <thead>--%>
<%--        <tr>--%>
<%--            <th>Details</th>--%>
<%--            <td>Quantity</td>--%>
<%--            <td>Remove</td>--%>
<%--        </tr>--%>
<%--        </thead>--%>
<%--        <tbody>--%>

<%--&lt;%&ndash;        <c:forEach var="${cart.keySet()}" items="id">&ndash;%&gt;--%>
<%--&lt;%&ndash;            <tr>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    &lt;%&ndash;                <td><%= products.get(id) %>&ndash;%&gt;&ndash;%&gt;--%>
<%--&lt;%&ndash;                <td>${products.get(id)}</td>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    &lt;%&ndash;                <td><%= cart.get(id)%></td>&ndash;%&gt;&ndash;%&gt;--%>
<%--&lt;%&ndash;                <td>${cart.get(id)}</td>&ndash;%&gt;--%>
<%--&lt;%&ndash;                <td><a class="btn btn-primary m-2" href="shop?page=removeItem&itemId=${id}">Remove</a></td>&ndash;%&gt;--%>
<%--&lt;%&ndash;            </tr>&ndash;%&gt;--%>
<%--&lt;%&ndash;        </c:forEach>&ndash;%&gt;--%>

<%--        </c:otherwise>--%>
<%--        </tbody>--%>
<%--    </table>--%>


        <% if (cart == null || cart.size() == 0) {%>
        <p>Your cart is empty</p>
        <% } else {%>
        <table class="table table-hover">
            <tr>
                <th>Details</th>
                <td>Quantity</td>
                <td>Remove</td>
            </tr>

            </thead>
            <tbody>

            <% for (int id : cart.keySet()) {%>
            <tr>
                <td><%= products.get(id) %>
                </td>
                <td><%= cart.get(id)%>
                </td>
                <td><a class="btn btn-primary m-2" href="shop?page=removeItem&itemId=<%= id %>">Remove</a></td>
            </tr>


            <% }%>

            <% }%>
            </tbody>
        </table>

    <p><a class="btn btn-danger" href="shop?page=emptyCart">Empty Cart</a></p>

</main>


<jsp:include page="../include/footer.jsp"/>