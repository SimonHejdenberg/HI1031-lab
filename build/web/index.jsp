<%@page import="Controller.ItemHandler"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.Iterator"%>
<%@page import="Model.Item"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Webb shop</title>
    </head>
    <body>
        <div>Welcome to our shop</div>
        <% Collection<Item> items = ItemHandler.getItems("hej");
            Iterator<Item> it = items.iterator();
                for(; it.hasNext();){
                Item item = it.next(); %>
                <%= item.getName()%>
               <% } %>
    </body>
</html>
