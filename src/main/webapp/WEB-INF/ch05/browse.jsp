<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--set the title to something--%>
<jsp:include page="../include/header-homepage.jsp" />
<main class="flex-shrink-0 m-3" >
    <p><a href="shop?page=viewCart">View Cart</a></p>
    <h2>Browse our Products</h2>
    <p>Select an item to add to your cart</p>
    <%
        Map<Integer,String> products = (Map<Integer,String>)request.getAttribute("products");
    %>
    <ul>
        <%
        for (int id : products.keySet()){ %>
        <li> <a href="shop?page=addToCart&itemId=<%= id %>"><%= products.get(id) %></a> </li>
    <%}%>
    </ul>


</main>


<jsp:include page="../include/footer.jsp" />