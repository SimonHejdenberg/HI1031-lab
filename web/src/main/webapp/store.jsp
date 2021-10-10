<%-- 
    Document   : Store
    Created on : 4 Oct 2021, 23:15:16
    Author     : jemsann
--%>

<%@page import="Logic.Item"%>
<%@page import="Logic.Cart"%>
<%@page import="UI.UserInfo"%>
<%@page import="java.util.Iterator"%>
<%@page import="Logic.ItemHandler"%>
<%@page import="UI.ItemInfo"%>
<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/main.css" type="text/css"/>  
        <% Cart tempCart = new Cart();  session.setAttribute("cart", tempCart);%>
        <%if (session.getAttribute("user") != null) {
                UserInfo user = (UserInfo) session.getAttribute("user");
            }%>
        <title> Webshop </title>
    </head>
    <body>
        <div class="header">
            <%@ include  file="jspheader.jsp" %>
        </div>
        <div class="main">
            <p>Welcome ${user.getUsername()} </p>
            <div class="flex-container">
                <% Collection<ItemInfo> items = ItemHandler.getItems();
                    Iterator<ItemInfo> it = items.iterator();
                    for (; it.hasNext();) {
                        ItemInfo item = it.next();%>
                <div class="grid-item">
                    <img width="50" height="50" src="pictures/<%=item.getPictureUrl()%>" />
                    <p><%=item.getName()%></p>
                    <p><%=item.getDescription()%></p>
                    <p><%=item.getPrice()%></p>
                    <p><%=item.getQuantity()%></p>

                    <% String itemName = item.getName();%>
                    <%if (item.getQuantity() <= 0) {%>
                        <button name='addItem' disabled="disabled">Add to cart</button>
                    <%}else{%>
                        <button name='addItem' value='<%=item.getId()%>' onclick='addItemToCart("<%=item.getName()%>")'  <%tempCart.addItem(item, 1); session.setAttribute("cart", tempCart);%> >Add to cart</button>
                    <%}%>
                </div>
                <%}%>
            </div>
        </div>


       

    </body>
    <script language="javascript">
        function addItemToCart(name) {
            alert("You've added " + name + " to cart");
        }
    </script>


</html>
