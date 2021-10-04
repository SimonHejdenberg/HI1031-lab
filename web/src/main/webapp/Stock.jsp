<%-- 
    Document   : Stock
    Created on : 4 Oct 2021, 23:15:16
    Author     : jemsann
--%>

<%@page import="java.util.Iterator"%>
<%@page import="Controller.ItemHandler"%>
<%@page import="UI.ItemInfo"%>
<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Stock</title>
    </head>
    <body>

        <table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Price</th>
                </tr>
            </thead>
            <% Collection<ItemInfo> items = ItemHandler.getItems();
                Iterator<ItemInfo> it = items.iterator();
                for (; it.hasNext();) {
                    ItemInfo item = it.next();%>
            <tbody>
                <tr>
                    <td> <%=item.getName()%></td>
                    <td> <%=item.getDescription()%></td>
                    <td> <%=item.getPrice()%></td>
                </tr>
            </tbody>
        </table>

        <br>
        <% }%>

    </body>
</html>
