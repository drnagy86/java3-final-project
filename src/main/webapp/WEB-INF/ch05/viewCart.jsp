<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--set the title to something--%>
<jsp:include page="../include/header-homepage.jsp" />
<main class="flex-shrink-0 m-3" >
  <p><a href="shop?page=browse">Browse Products</a></p>
  <h2>Your Shopping Cart</h2>
<%
  Map<Integer, String> products = (Map<Integer, String>) request.getAttribute("products");
  Map<Integer,Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
%>
  <% if (cart == null || cart.size() == 0){%>
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

      <% for(int id : cart.keySet()){%>
      <tr>
        <td><%= products.get(id) %></td>
        <td><%= cart.get(id)%></td>
        <td><a class="btn btn-primary m-2" href="shop?page=removeItem&itemId=<%= id %>">Remove</a></td>
      </tr>



      <% }%>

    <% }%>
    </tbody>
  </table>

  <p><a class="btn btn-danger" href="shop?page=emptyCart">Empty Cart</a></p>

</main>


<jsp:include page="../include/footer.jsp" />