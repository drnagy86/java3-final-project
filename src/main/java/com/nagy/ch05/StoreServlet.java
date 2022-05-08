package com.nagy.ch05;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

@WebServlet(name = "StoreServlet", value = "/ch05/shop")
public class StoreServlet extends HttpServlet {
    private final Map<Integer, String> products = new Hashtable<>();

    @Override
    public void init() throws ServletException{
        products.put(1, "Butterfinger");
        products.put(2, "Coffee Crisp");
        products.put(3, "Monkey meat");
        products.put(4, "Aero");
        products.put(5, "Snickers");
        products.put(6, "Peeps");

        // routes/ query string parameters
        // /shop
        // /shop?page = browse
        // /shop?page = addItem&productId=1
        // /shop?page = viewCart
        // /shop?page = empty
        // /shop?page = removeItem&productId=1


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        if (page == null){
            page = "browse";
        }
        switch (page){
            case "addToCart":
                addToCart(request, response);
                break;
            case "viewCart":
                viewCart(request,response);
                break;
            case "emptyCart":
                emptyCart(request, response);
                break;
            case "removeItem":
                removeItem(request,response);
                break;
            case "browse":
            default:
                browse(request, response);
                break;

        }
    }

    private void removeItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String itemId = request.getParameter("itemId");
        int item;
        try {
            item = Integer.parseInt(itemId);
        } catch(NumberFormatException e) {
            response.sendRedirect("shop?page=viewCart");
            return;
        }
        HttpSession session = request.getSession();
        if(session.getAttribute("cart") == null) {
            response.sendRedirect("shop?page=viewCart");
            return;
        }
        Map<Integer, Integer> cart = (Map<Integer, Integer>)session.getAttribute("cart");
        if(!cart.containsKey(item)) {
            response.sendRedirect("shop?page=viewCart");
            return;
        } else {
            cart.remove(item);
        }

        response.sendRedirect("shop?page=viewCart");
//
//        String itemId = request.getParameter("itemId");
//        int item;
//        try {
//            item = Integer.parseInt(itemId);
//        } catch (NumberFormatException e){
//            response.sendRedirect("shop?page=viewCart");
//            return;
//        }
//        HttpSession session = request.getSession();
//
//        if (session.getAttribute("cart")== null){
//            response.sendRedirect("shop?page=viewCart");
//            return;
//        }
//        Map<Integer,Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
//
//        if (!products.containsKey(item)){
//            response.sendRedirect("shop?page=viewCart");
//            return;
//        } else {
//            cart.remove(item);
//            response.sendRedirect("shop?page=viewCart");
//
//        }
//        // send the user to the page where they can view the cart
//        response.sendRedirect("shop?page=viewCart");

    }

    private void emptyCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        Map<Integer,Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
        cart.clear();
        //other option would be to remove attribute
//        request.getSession().removeAttribute("cart");

        response.sendRedirect("shop?viewCart");
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //itemId
        String itemId = request.getParameter("itemId");
        int item;
        try {
            item = Integer.parseInt(itemId);
        } catch (NumberFormatException e){
            // can't parse, go home
            response.sendRedirect("shop");
            // return nothing to not run any more code in this method
            return;
        }
        if (!products.containsKey(item)){
            response.sendRedirect("shop");
            return;
        }

        // checks to see if there is a session id, otherwise makes one
        HttpSession session = request.getSession();
        // set attributes on session and follows them around
        if(session.getAttribute("cart") == null){
            // product id, number in cart
            // creates a "cart" if they don't have one
            session.setAttribute("cart", new Hashtable<Integer, Integer>());
        }
        Map<Integer, Integer> cart = (Map<Integer, Integer>)session.getAttribute("cart");
        // check to see if cart has id in it
        if (!cart.containsKey(item)){
            // put first item into
            cart.put(item, 1);
        } else {
            // item is in the cart, adding one
            cart.put(item, cart.get(item) + 1);
        }
        // send the user to the page where they can view the cart
        response.sendRedirect("shop?page=viewCart");

    }

    private void viewCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("products", products);


        // forward to jsp to show a list of all the products
        request.getRequestDispatcher("/WEB-INF/ch05/viewCart.jsp").forward(request,response);


    }

    private void browse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("products", products);


        // forward to jsp to show a list of all the products
        request.getRequestDispatcher("/WEB-INF/ch05/browse.jsp").forward(request,response);



    }


}
