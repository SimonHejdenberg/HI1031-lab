<%-- 
    Document   : Stock
    Created on : 4 Oct 2021, 23:15:16
    Author     : jemsann
--%>

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
        <%String username;%>
        <%if(session.getAttribute("username") == null) {
                username = "New User";
            } else {
                username = (String) session.getAttribute("username").toString();
            }%>
        <title> Webshop </title>
    </head>
    <body>
        <div class="header">
            <%@ include  file="html/header.html" %>
        </div>
        <div class="main">
            <p>Welcome <%=username%></p>
            <div class="flex-container">
                <% Collection<ItemInfo> items = ItemHandler.getItems();
                    Iterator<ItemInfo> it = items.iterator();
                    for (; it.hasNext();) {
                        ItemInfo item = it.next();%>
                <div class="grid-item">
                    <img height="50" width="50" src="pictures/ <%=item.getPictureUrl() %>" />
                    <p> <%=item.getName()%></p>
                    <p><%=item.getDescription()%></p>
                    <p> <%=item.getPrice()%></p>
                </div>
                <%}%>
            </div>
        </div>


        <div class="footer">
            <%@ include  file="html/footer.html" %>
        </div>

    </body>
</html>
