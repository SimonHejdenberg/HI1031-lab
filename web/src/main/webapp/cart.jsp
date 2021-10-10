<%@page import="java.util.Set"%>
<%@page import="UI.ItemInfo"%>
<%@page import="java.util.Map"%>
<%@page import="Logic.Cart"%>
<%@page import="UI.UserInfo"%>
<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Cart</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/main.css" type="text/css"/>
        <%Cart cart = null;
            Set<Map.Entry<ItemInfo, Integer>> set = null;%>
        <%if (session.getAttribute("cart") != null) {
                cart = ((Cart) session.getAttribute("cart"));
                set = cart.getContMapCopy().entrySet();
            } else {
                cart = new Cart();
            }%>
    </head>
    <body>
        <div class="header">
            <%@ include  file="html/header.html" %>
        </div>
        <div class="main">
            <h1>Cart</h1>
            <%if (set != null) {%> 
            <div class='flex-container'>
                <%for (Map.Entry<ItemInfo, Integer> entry : set) {
                        ItemInfo k = entry.getKey();
                        int amount = entry.getValue();%>
                <p><%=k.getName()%></p>
                <p><%=k.getPrice()%></p>
                <p><%=amount%></p>
                <%}%>
            </div>
            <%}%>
            <button name="clearCart" onclick="<% session.removeAttribute("cart");
                session.setAttribute("cart", new Cart());%>">Clear Cart</button>

        </div>

        <div class="footer">
            <%@ include  file="html/footer.html" %>
        </div>
    </body>
</html>
