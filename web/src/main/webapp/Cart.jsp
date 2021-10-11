<%@page import="java.util.Iterator"%>
<%@page import="Logic.ItemHandler"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.Collection"%>
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
            Set<Map.Entry<Integer, Integer>> set = null;
            Collection c;
            Iterator<ItemInfo> it=null;%>
        <%if (session.getAttribute("cart") != null) {
                cart = ((Cart) session.getAttribute("cart"));
                set = cart.getContMapCopy().entrySet();
                c = ItemHandler.getItems();
                it = c.iterator();
            } else {
                cart = new Cart();
            }%>
    </head>
    <body>
        <div class="header">
            <%@ include  file="jspheader.jsp" %>
        </div>
        <div class="main">

            <h1>Cart</h1>
            <%if (set != null) {%> 
            <div class='flex-container'>
                <%for (Map.Entry<Integer, Integer> entry : set) {
                        int itemId = entry.getKey();
                        int amount = entry.getValue();
                        while (it.hasNext()&&it!=null) {
                            ItemInfo item = it.next();
                            if (itemId == item.getId()) {
                %>
                <p>Name: <%=item.getName()%></p>
                <p>Price: <%=item.getPrice()%></p>
                <p>Description <%=item.getDescription()%></p>
                <p>Amount <%=amount%></p>
                <%
                            }
                        }
                    }%>
            </div>
            <%}%>

            <button id="buyBtn" name="buy" onclick='javascript:customBuyFunction();return false;'>Buy</button>  

        </div>

        <div class="footer">
            <%@ include  file="jspfooter.jsp" %>
        </div>
    </body>
    <script>
        function customBuyFunction() {
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
            url = proto + "//" + host + context + "/Buy";

            xmlHttp.open("POST", url, true); //the value true is for using asynchronous mode
            xmlHttp.send();
            xmlHttp.onreadystatechange = function ()
            {
                if (xmlHttp.readyState == 4)
                {
                    alert("Order placed!");
                }
            }
        }


    </script>

</html>
