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
        <% if (session.getAttribute("cart") == null) {
                Cart tempCart = new Cart();
                session.setAttribute("cart", tempCart);
            }%>
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
                <form name="formser" class="grid-item">
                    <img width="50" height="50" src="pictures/<%=item.getPictureUrl()%>" />
                    <p><%=item.getName()%></p>
                    <p><%=item.getDescription()%></p>
                    <p><%=item.getPrice()%></p>
                    <p><%=item.getQuantity()%></p>

                    <% String itemName = item.getName();%>
                    <%if (item.getQuantity() <= 0) {%>
                    <button name='addItem' disabled="disabled">Add to cart</button>
                    <%} else {%>
                    <button id="addtocart" name="addtocart" value='<%=item.getName()%>' onclick='javascript:customJSFunction("<%=item.getId()%>", "<%=item.getName()%>");return false;' ><%=item.getName()%></button>
                    <%}%>

                </form>
                <%}%>
            </div>
        </div>




    </body>
    <script>
        function customJSFunction(id, name) {
            proto = window.location.protocol;
            host = window.location.host;
            context = window.location.pathname;
            context = window.location.pathname.substring(0, context.lastIndexOf("/"));
            context = context.replace("", ""); //applicationcontext
            var xmlHttp;
            try
            {
                // Firefox, Opera 8.0+, Safari
                xmlHttp = new XMLHttpRequest();
            } catch (exception)
            { // Internet Explorer
                try
                {
                    xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
                } catch (exception)
                {
                    try
                    {
                        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                    } catch (exception)
                    {
                        alert("Your browser does not support AJAX!");
                        return false;
                    }
                }
            }
            var host = window.location.host;
            url = proto + "//" + host + context + "/addtocart?itemId=" + id + "&itemName=" + name;

            xmlHttp.open("POST", url, true); //the value true is for using asynchronous mode
            xmlHttp.send();
            xmlHttp.onreadystatechange = function ()
            {
                if (xmlHttp.readyState == 4)
                {
                    alert("Added " + name + " to cart");
                }
            }
        }
    </script>
</html>
